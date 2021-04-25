package es.nacho.redeem.service;

import es.nacho.redeem.model.Employee;
import es.nacho.redeem.web.dto.EmployeeRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Employee registerAdmin(EmployeeRegistrationDto employeeRegistrationDto);

}
