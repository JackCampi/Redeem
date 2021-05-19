package es.nacho.redeem.service;

import es.nacho.redeem.config.dto.AuthDto;
import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.web.dto.AdminDashboardInfoDto;
import es.nacho.redeem.web.dto.AdminRegistrationDto;
import es.nacho.redeem.web.dto.EmployeeDashboardInfoDto;
import es.nacho.redeem.web.dto.EmployeeRegistrationDto;
import es.nacho.redeem.web.dto.employee.MemberDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    Employee registerAdmin(AdminRegistrationDto adminRegistrationDto, Long companyNIT) throws Exception;

    Employee registerEmployee(EmployeeRegistrationDto employeeRegistrationDto, Long companyNIT) throws Exception;

    AdminDashboardInfoDto fillAdminDashboardInfoDto(String email, AdminDashboardInfoDto adminDashboardInfoDto) throws Exception;

    EmployeeDashboardInfoDto fillEmployeeDashboardInfoDto(String email, EmployeeDashboardInfoDto employeeDashboardInfoDto) throws Exception;

    Boolean checkIfEmailExists(String email);

    Employee discountToUserBalance(long id, long amount) throws InsufficientBalanceException;

    Employee incrementToUserBalanceById(long id, long amount) throws UserNotFoundException;

    Employee incrementToUserBalanceByEmail(String email , long amount) throws UserNotFoundException;

    AuthDto fillAuthDto(String email);

    void editUserInformation(long nit, MemberDto memberDto);

    void changePassword(long id, String currentPassword, String newPassword) throws Exception;

    boolean passwordIsCorrect(long id, String passwordToTest)throws UserNotFoundException ;

}
