package es.nacho.redeem.service;

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

import java.util.Calendar;
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
                120L,
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
                0L,
                true,
                "administrador",
                area
        ));
    }

    @AfterAll
    void after(){
        Employee employee = employeeRepository.findByEmail("correosupervalido@gmail.com");
        employeeRepository.delete(employee);

        Optional<Company> company = companyRepository.findById(120L);

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

        Long nit = 12L;
        EmployeeRegistrationDto employeeRegistrationDto = new EmployeeRegistrationDto("Santiago", "Sanchez", "nuevo@unal.edu.co", "hola", "3232332234", "12-12-2333", "gerencia", "hola");

        Employee employee = userService.registerEmployee(employeeRegistrationDto, nit);
        assertEquals(employeeRegistrationDto.getEmail(), employee.getEmail());

    }

    @Test
    @Rollback
    void registerEmployeeTest2() {

        Long nit = 12L;
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
        assertEquals(0L, employeeDashboardInfoDto.getBalance());

    }

    @Test
    @Rollback
    void fillEmployeeDashboardInfoDtoTest2() {

        String email = "nuevo@gmail.com";
        EmployeeDashboardInfoDto employeeDashboardInfoDto = new EmployeeDashboardInfoDto();

        assertThrows(Exception.class, () -> userService.fillEmployeeDashboardInfoDto(email, employeeDashboardInfoDto));

    }

}