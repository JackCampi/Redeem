package es.nacho.redeem.service;

import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.model.compositeKeys.AreaKey;
import es.nacho.redeem.repository.AreaRepository;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.repository.EmployeeRepository;
import es.nacho.redeem.web.dto.AdminRegistrationDto;
import es.nacho.redeem.web.dto.EmployeeRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

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
        User user = new User(employee.getName(), employee.getPassword(), roles);

        return user;
    }

    @Override
    public Employee registerAdmin(AdminRegistrationDto adminRegistrationDto, Long companyNIT) throws Exception {
        //TODO: set default area to the employee
        Optional<Company> company =  companyRepository.findById(companyNIT);
        if( !company.isPresent()) throw new Exception("Company not found");

        //AreaKey key = new AreaKey(company.get().getId(), )

        Employee employee = new Employee(
                adminRegistrationDto.getName(),
                adminRegistrationDto.getLastName(),
                adminRegistrationDto.getEmail(),
                passwordEncoder.encode(adminRegistrationDto.getPassword()),
                adminRegistrationDto.getCellphone(),
                adminRegistrationDto.getBirthday(),
                adminRegistrationDto.getBalance(),
                adminRegistrationDto.getActive(),
                "administrador",
                null
        );

        return employeeRepository.save(employee);
    }

    @Override
    public Employee registerEmployee(EmployeeRegistrationDto employeeRegistrationDto, Long companyNIT) throws Exception {

        //TODO: search area
        Optional<Company> company =  companyRepository.findById(companyNIT);
        if( !company.isPresent()) throw new Exception("Company not found");

        Employee employee = new Employee(
                employeeRegistrationDto.getName(),
                employeeRegistrationDto.getLastName(),
                employeeRegistrationDto.getEmail(),
                passwordEncoder.encode(employeeRegistrationDto.getPassword()),
                employeeRegistrationDto.getCellphone(),
                employeeRegistrationDto.getBirthday(),
                employeeRegistrationDto.getBalance(),
                employeeRegistrationDto.getActive(),
                "empleado",
                null
        );

        return employeeRepository.save(employee);
    }


}
