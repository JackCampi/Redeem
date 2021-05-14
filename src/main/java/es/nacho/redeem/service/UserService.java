package es.nacho.redeem.service;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.web.dto.AdminDashboardInfoDto;
import es.nacho.redeem.web.dto.AdminRegistrationDto;
import es.nacho.redeem.web.dto.EmployeeDashboardInfoDto;
import es.nacho.redeem.web.dto.EmployeeRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Employee registerAdmin(AdminRegistrationDto adminRegistrationDto, Long companyNIT) throws Exception;

    Employee registerEmployee(EmployeeRegistrationDto employeeRegistrationDto, Long companyNIT) throws Exception;

    AdminDashboardInfoDto fillAdminDashboardInfoDto(String email, AdminDashboardInfoDto adminDashboardInfoDto) throws Exception;

    EmployeeDashboardInfoDto fillEmployeeDashboardInfoDto(String email, EmployeeDashboardInfoDto employeeDashboardInfoDto) throws Exception;

    Boolean checkIfEmailExists(String email);

    void discountToUserBalance(long id, long amount) throws InsufficientBalanceException;

    void incrementToUserBalanceById(long id, long amount) throws UserNotFoundException;

    void incrementToUserBalanceByEmail(String email , long amount) throws UserNotFoundException;

    long getIdByEmail(String email) throws UserNotFoundException;

}
