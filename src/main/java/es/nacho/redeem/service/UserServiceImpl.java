package es.nacho.redeem.service;

import es.nacho.redeem.config.dto.AuthDto;
import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.InvalidCalendarFormatException;
import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.model.compositeKeys.AreaKey;
import es.nacho.redeem.repository.AreaRepository;
import es.nacho.redeem.repository.EmployeeRepository;
import es.nacho.redeem.web.dto.AdminDashboardInfoDto;
import es.nacho.redeem.web.dto.AdminRegistrationDto;
import es.nacho.redeem.web.dto.EmployeeDashboardInfoDto;
import es.nacho.redeem.web.dto.EmployeeRegistrationDto;
import es.nacho.redeem.web.dto.employee.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AreaRepository areaRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AreaService areaService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email);

        if (employee == null) throw new UsernameNotFoundException("Usuario y/o contraseña incorrecta");
        if(!employee.getActive()) throw new UsernameNotFoundException("Usuario no activo");

        Collection<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(employee.getRol()));

        return new User(employee.getEmail(), employee.getPassword(), roles);
    }

    @Override
    public Employee registerAdmin(AdminRegistrationDto adminRegistrationDto, Long companyNIT) throws Exception {

        AreaKey key = new AreaKey("gerencia",companyNIT);
        Optional<Area> area = areaRepository.findById(key);

        if( !area.isPresent()) throw new Exception("Area not found");
        Employee employee = new Employee(
                adminRegistrationDto.getName(),
                adminRegistrationDto.getLastName(),
                adminRegistrationDto.getEmail(),
                passwordEncoder.encode(adminRegistrationDto.getPassword()),
                adminRegistrationDto.getCellphone(),
                getCalendarFromString(adminRegistrationDto.getBirthday()),
                0L,
                true,
                "administrador",
                area.get()
        );
        return  employeeRepository.save(employee);

    }

    @Override
    public Employee registerEmployee(EmployeeRegistrationDto employeeRegistrationDto, Long companyNIT) throws Exception {

        String areaName = areaService.lowercaseAreaName(employeeRegistrationDto.getArea());

        if(areaName.equals("gerencia")){
            return registerAdmin(new AdminRegistrationDto(
                    employeeRegistrationDto.getName(),
                    employeeRegistrationDto.getLastName(),
                    employeeRegistrationDto.getEmail(),
                    employeeRegistrationDto.getPassword(),
                    employeeRegistrationDto.getCellphone(),
                    employeeRegistrationDto.getBirthday()
            ), companyNIT);

        }

        AreaKey key = new AreaKey(areaName, companyNIT);
        Optional<Area> area = areaRepository.findById(key);

        if( !area.isPresent()) throw new Exception("Area not found");

        Area areaObject = area.get();

        Employee employee = new Employee(
                employeeRegistrationDto.getName(),
                employeeRegistrationDto.getLastName(),
                employeeRegistrationDto.getEmail(),
                passwordEncoder.encode(employeeRegistrationDto.getPassword()),
                employeeRegistrationDto.getCellphone(),
                getCalendarFromString(employeeRegistrationDto.getBirthday()),
                0L,
                true,
                "empleado",
                areaObject
        );

        return employeeRepository.save(employee);
    }

    @Override
    public AdminDashboardInfoDto fillAdminDashboardInfoDto(String email, AdminDashboardInfoDto adminDashboardInfoDto) throws Exception {

        Employee employee = employeeRepository.findByEmail(email);
        if(employee == null) throw new Exception("User not found");

        adminDashboardInfoDto.setName(employee.getName());

        return adminDashboardInfoDto;
    }

    @Override
    public EmployeeDashboardInfoDto fillEmployeeDashboardInfoDto(String email, EmployeeDashboardInfoDto employeeDashboardInfoDto) throws Exception {

        Employee employee = employeeRepository.findByEmail(email);
        if(employee == null) throw new Exception("User not found");

        employeeDashboardInfoDto.setName(employee.getName());
        employeeDashboardInfoDto.setBalance(employee.getBalance());

        return employeeDashboardInfoDto;
    }

    @Override
    public Boolean checkIfEmailExists(String email) {

        Employee employee = employeeRepository.findByEmail(email);

        return !(employee==null);
    }

    @Override
    public Employee discountToUserBalance(long id, long amount) throws InsufficientBalanceException {

        Optional<Employee> employee = employeeRepository.findById(id);

        if(!employee.isPresent()) throw new UserNotFoundException();
        Employee employeeObject = employee.get();

        long balance = employeeObject.getBalance();
        if(amount > balance) throw new InsufficientBalanceException();

        employeeObject.setBalance(balance-amount);
        return employeeRepository.save(employeeObject);

    }

    @Override
    public Employee incrementToUserBalanceById(long id, long amount) throws UserNotFoundException {

        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent()) throw new UserNotFoundException();

        return incrementToUserBalance(employee.get(), amount);

    }

    @Override
    public Employee incrementToUserBalanceByEmail(String email, long amount) throws UserNotFoundException {

        Employee employee = employeeRepository.findByEmail(email);
        if(employee == null) throw new UserNotFoundException();

        return incrementToUserBalance(employee, amount);

    }

    private Employee incrementToUserBalance(Employee employee, long amount) throws UserNotFoundException {

        if(!employee.getActive()) throw new UserNotFoundException("The user is not active");

        employee.setBalance(employee.getBalance() + amount);
        return employeeRepository.save(employee);

    }

    @Override
    public AuthDto fillAuthDto(String email) {

        Employee employee = employeeRepository.findByEmail(email);
        if(employee == null) throw new UserNotFoundException();

        return new AuthDto(email, employee.getName(), employee.getId());
    }

    @Override
    public void editUserInformation(long nit, MemberDto memberDto) {

        Optional<Employee> employee = employeeRepository.findById(memberDto.getId());
        if(!employee.isPresent()) throw new UserNotFoundException();

        Employee employeeObject = employee.get();
        employeeObject.setEmail(memberDto.getEmail());
        employeeObject.setName(memberDto.getName());
        employeeObject.setLastName(memberDto.getLastName());
        employeeObject.setCellphone(memberDto.getCellphone());
        try {
            employeeObject.setBirthday(getCalendarFromString(memberDto.getBirthday()));
        } catch (InvalidCalendarFormatException e) {
            e.printStackTrace();
        }

        AreaKey key = new AreaKey(areaService.lowercaseAreaName(memberDto.getArea()), nit);
        Optional<Area> area = areaRepository.findById(key);

        if( !area.isPresent()) throw new RuntimeException("Area not found");

        employeeObject.setArea(area.get());

        employeeRepository.save(employeeObject);


    }

    private Calendar getCalendarFromString(String string) throws InvalidCalendarFormatException {

        /*
        * Only works for date string with the format "yyyy-mm-dd"
         */
        if(!calendarAsStringIsValid(string)) throw new InvalidCalendarFormatException(); //TODO test
        String[] dateArray = string.split("-");

        int year = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]) - 1;
        int day = Integer.parseInt(dateArray[2]);

        return new GregorianCalendar(year, month, day);

    }

    private boolean calendarAsStringIsValid(String date){
        return date.matches("^\\d{4}-(?:0[1-9]|1[0-2])-(?:0[1-9]|[1-2][0-9]|3[0-1])$");
    }

    public void changePassword(long id, String currentPassword, String newPassword) throws Exception{
        Optional<Employee> possibleEmployee = employeeRepository.findById(id);
        if(!possibleEmployee.isPresent()) throw new UserNotFoundException();
        Employee employee = possibleEmployee.get();
        if(!passwordIsCorrect(id,currentPassword)) throw new Exception();
        employee.setPassword(passwordEncoder.encode(newPassword));
        employeeRepository.save(employee);
    }

    @Override
    public boolean passwordIsCorrect(long id, String passwordToTest) throws UserNotFoundException {
        Optional<Employee> possibleEmployee = employeeRepository.findById(id);
        if(!possibleEmployee.isPresent()) throw new UserNotFoundException();
        Employee employee = possibleEmployee.get();
        String passwordInDatabase = employee.getPassword(),
               passwordToConfirm = passwordEncoder.encode(passwordToTest);
        return passwordInDatabase.equals(passwordToConfirm);
    }


}
