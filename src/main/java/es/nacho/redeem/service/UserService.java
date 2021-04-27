package es.nacho.redeem.service;

import es.nacho.redeem.model.Employee;
import es.nacho.redeem.web.dto.AdminRegistrationDto;
import es.nacho.redeem.web.dto.EmployeeRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void registerAdmin(AdminRegistrationDto adminRegistrationDto, Long companyNIT) throws Exception;

    Employee registerEmployee(EmployeeRegistrationDto employeeRegistrationDto, Long companyNIT) throws Exception;

}
