package es.nacho.redeem.service;

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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByEmail(email);

        if (employee == null) throw new UsernameNotFoundException("Usuario y/o contraseña incorrecta");

        Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(employee.getRol()));
        User user = new User(employee.getEmail(), employee.getPassword(), roles);

        return user;
    }

    @Override
    public void registerAdmin(AdminRegistrationDto adminRegistrationDto, Long companyNIT) throws Exception {
        //TODO: test
        //Optional<Company> company =  companyRepository.findById(companyNIT);
        //if( !company.isPresent()) throw new Exception("Company not found");

        AreaKey key = new AreaKey("gerencia",companyNIT);
        Optional<Area> area = areaRepository.findById(key);

        if( !area.isPresent()) throw new Exception("Area not found");

        //company.get().getAreas().stream().findAny()

        Employee employee = new Employee(
                adminRegistrationDto.getName(),
                adminRegistrationDto.getLastName(),
                adminRegistrationDto.getEmail(),
                passwordEncoder.encode(adminRegistrationDto.getPassword()),
                //adminRegistrationDto.getPassword(),
                adminRegistrationDto.getCellphone(),
                getCalendarFromString(adminRegistrationDto.getBirthday()),
                0L,
                true,
                "administrador",
                area.get()
        );
        try {
            employeeRepository.save(employee);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        int x = 1;
        x++;
    }

    @Override
    public Employee registerEmployee(EmployeeRegistrationDto employeeRegistrationDto, Long companyNIT) throws Exception {

        //TODO: test
        AreaKey key = new AreaKey(employeeRegistrationDto.getArea(),companyNIT);
        Optional<Area> area = areaRepository.findById(key);

        if( !area.isPresent()) throw new Exception("Area not found");

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
                null
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

    private Calendar getCalendarFromString(String string){

        /*
        * Only works for date string with the format "yyyy-mm-dd"
         */

        String[] dateArray = string.split("-");

        int year = Integer.valueOf(dateArray[0]);
        int month = Integer.valueOf(dateArray[1]) - 1;
        int day = Integer.valueOf(dateArray[2]);

        Calendar calendar = new GregorianCalendar(year, month, day);
        return  calendar;

    }



}
