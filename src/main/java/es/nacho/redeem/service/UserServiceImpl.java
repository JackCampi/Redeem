package es.nacho.redeem.service;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.model.compositeKeys.AreaKey;
import es.nacho.redeem.repository.AreaRepository;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.repository.EmployeeRepository;
import es.nacho.redeem.web.dto.AdminDashboardInfoDto;
import es.nacho.redeem.web.dto.AdminRegistrationDto;
import es.nacho.redeem.web.dto.EmployeeDashboardInfoDto;
import es.nacho.redeem.web.dto.EmployeeRegistrationDto;
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
    private CompanyRepository companyRepository;

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
    public long getIdByEmail(String email) throws UserNotFoundException {

        Employee employee = employeeRepository.findByEmail(email);
        if(employee == null) throw new UserNotFoundException();

        return employee.getId();
    }

    private Calendar getCalendarFromString(String string){

        /*
        * Only works for date string with the format "yyyy-mm-dd"
         */

        String[] dateArray = string.split("-");

        int year = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]) - 1;
        int day = Integer.parseInt(dateArray[2]);

        return new GregorianCalendar(year, month, day);

    }



}
