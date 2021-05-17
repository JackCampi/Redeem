package es.nacho.redeem.service;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.exception.UserNotFoundException;
import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.repository.AreaRepository;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.repository.EmployeeRepository;
import es.nacho.redeem.web.dto.AdminDashboardInfoDto;
import es.nacho.redeem.web.dto.EmployeeDashboardInfoDto;
import es.nacho.redeem.web.dto.EmployeeRegistrationDto;
import org.junit.After;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebAppConfiguration
@SpringBootTest
@Transactional
class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AreaRepository areaRepository;

    @BeforeAll
    void before(){

        Company company = new Company(
                Long.MAX_VALUE,
                "gulugulu",
                10000000L
        );

        companyRepository.save(company);

        Area area = new Area("gerencia", company);
        areaRepository.save(area);

        employeeRepository.save(new Employee(
                "brayan",
                "quintero",
                "correosupervalido@gmail.com",
                "hola",
                "323323232323",
                new GregorianCalendar(2002,02,02),
                50000L,
                true,
                "administrador",
                area
        ));

        employeeRepository.save(new Employee(
                "brayan",
                "quintero",
                "correosupervalidoperonoactivo@gmail.com",
                "hola",
                "323323232323",
                new GregorianCalendar(2002,02,02),
                50000L,
                false,
                "administrador",
                area
        ));

    }

    @AfterAll
    void after(){
        Employee employee = employeeRepository.findByEmail("correosupervalido@gmail.com");
        employeeRepository.delete(employee);

        Employee employee2 = employeeRepository.findByEmail("correosupervalidoperonoactivo@gmail.com");
        employeeRepository.delete(employee2);

        Optional<Company> company = companyRepository.findById(Long.MAX_VALUE);

        Collection<Area> area = areaRepository.findByCompany(company.get());

        area.forEach(area1 -> areaRepository.delete(area1));

        companyRepository.delete(company.get());

    }

    @Test
    @Rollback
    void loadUserByUsernameTest1() {

        String email = "correosupervalido@gmail.com";
        UserDetails userDetails = userService.loadUserByUsername(email);

        assertEquals(email, userDetails.getUsername());


    }

    @Test
    @Rollback
    void loadUserByUsernameTest2() {

        String email = "noexiste@unal.edu.co";
        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername(email));

    }

    @Test
    @Rollback
    void registerEmployeeTest1() throws Exception {

        Long nit = Long.MAX_VALUE;
        EmployeeRegistrationDto employeeRegistrationDto = new EmployeeRegistrationDto("Santiago", "Sanchez", "nuevo@unal.edu.co", "hola", "3232332234", "12-12-2333", "gerencia", "hola");

        Employee employee = userService.registerEmployee(employeeRegistrationDto, nit);
        assertEquals(employeeRegistrationDto.getEmail(), employee.getEmail());

    }

    @Test
    @Rollback
    void registerEmployeeTest2() {

        Long nit = Long.MAX_VALUE;
        EmployeeRegistrationDto employeeRegistrationDto = new EmployeeRegistrationDto("Santiago", "Sanchez", "nuevo@unal.edu.co", "hola", "3232332234", "12-12-2333", "video", "hola");

        assertThrows(Exception.class, () -> userService.registerEmployee(employeeRegistrationDto, nit));
    }

    @Test
    @Rollback
    void fillAdminDashboardInfoTest1() throws Exception {

        String email = "correosupervalido@gmail.com";
        AdminDashboardInfoDto adminDashboardInfoDto = new AdminDashboardInfoDto();

        adminDashboardInfoDto = userService.fillAdminDashboardInfoDto(email, adminDashboardInfoDto);

        assertEquals("brayan", adminDashboardInfoDto.getName());

    }

    @Test
    @Rollback
    void fillAdminDashboardInfoTest2() {

        String email = "nuevo@gmail.com";
        AdminDashboardInfoDto adminDashboardInfoDto = new AdminDashboardInfoDto();

        assertThrows(Exception.class, () -> userService.fillAdminDashboardInfoDto(email, adminDashboardInfoDto));
    }

    @Test
    @Rollback
    void fillEmployeeDashboardInfoDtoTest1() throws Exception {

        String email = "correosupervalido@gmail.com";
        EmployeeDashboardInfoDto employeeDashboardInfoDto = new EmployeeDashboardInfoDto();

        employeeDashboardInfoDto = userService.fillEmployeeDashboardInfoDto(email, employeeDashboardInfoDto);

        assertEquals("brayan", employeeDashboardInfoDto.getName());
        assertEquals(50000L, employeeDashboardInfoDto.getBalance());

    }

    @Test
    @Rollback
    void fillEmployeeDashboardInfoDtoTest2() {

        String email = "nuevo@gmail.com";
        EmployeeDashboardInfoDto employeeDashboardInfoDto = new EmployeeDashboardInfoDto();

        assertThrows(Exception.class, () -> userService.fillEmployeeDashboardInfoDto(email, employeeDashboardInfoDto));

    }
    @Test
    @Rollback
    void discountToUserBalanceTest1() throws InsufficientBalanceException {

        Employee employee = employeeRepository.findByEmail("correosupervalido@gmail.com");
        userService.discountToUserBalance(employee.getId(), 30000L);
        assertEquals(employee.getBalance(), 20000L);

    }
    @Test
    @Rollback
    void discountToUserBalanceTest2() {

        Employee employee = employeeRepository.findByEmail("correosupervalido@gmail.com");

        assertThrows(InsufficientBalanceException.class, () -> userService.discountToUserBalance(employee.getId(), Long.MAX_VALUE));

    }

    @Test
    @Rollback
    void discountToUserBalanceTest3() {

        Employee employee = employeeRepository.findByEmail("correosupervalido@gmail.com");

        assertThrows(UserNotFoundException.class, () -> userService.discountToUserBalance(Long.MAX_VALUE, Long.MAX_VALUE));

    }

    @Test
    @Rollback
    void incrementToUserBalanceByIdTest1(){

        Employee employee = employeeRepository.findByEmail("correosupervalido@gmail.com");

        userService.incrementToUserBalanceById(employee.getId(), 20000L);
        assertEquals(employee.getBalance(), 70000L);

    }

    @Test
    @Rollback
    void incrementToUserBalanceByIdTest2(){
        assertThrows(UserNotFoundException.class, () -> userService.incrementToUserBalanceById(Long.MAX_VALUE, 20000L));

    }

    @Test
    @Rollback
    void incrementToUserBalanceByIdTest3(){
        Employee employee = employeeRepository.findByEmail("correosupervalidoperonoactivo@gmail.com");
        assertThrows(UserNotFoundException.class, () -> userService.incrementToUserBalanceById(employee.getId(), 20000L));

    }

    @Test
    @Rollback
    void incrementToUserBalanceByEmailTest1(){

        Employee employee = employeeRepository.findByEmail("correosupervalido@gmail.com");

        userService.incrementToUserBalanceByEmail("correosupervalido@gmail.com", 20000L);
        assertEquals(employee.getBalance(), 70000L);

    }

    @Test
    @Rollback
    void incrementToUserBalanceByEmailTest2(){
        assertThrows(UserNotFoundException.class, () -> userService.incrementToUserBalanceByEmail("noexiste@unal.edu.co", 20000L));

    }

    @Test
    @Rollback
    void incrementToUserBalanceByEmailTest3(){

        assertThrows(UserNotFoundException.class, () -> userService.incrementToUserBalanceByEmail("correosupervalidoperonoactivo@gmail.com", 20000L));

    }

}