package es.nacho.redeem.controller;

import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.repository.AreaRepository;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.repository.EmployeeRepository;
import es.nacho.redeem.web.dto.AdminRegistrationDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.http.HttpSession;
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
class RegistrationControllerTest {

    @Autowired
    RegistrationController registrationController;

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
    void registerAdminAccountTest1() {

        HttpSession session = new MockHttpSession();

        session.setAttribute("nit", 120L);

        AdminRegistrationDto adminRegistrationDto = new AdminRegistrationDto("Juan", "Ramiro", "nuevo@n.n", "Holahola#", "3123232345", "07-07-2000");
        String page = registrationController.registerAdminAccount(adminRegistrationDto, session);

        assertEquals("redirect:/admin?success", page);

    }

    @Test
    @Rollback
    void registerAdminAccountTest2() {

        HttpSession session = new MockHttpSession();

        session.setAttribute("nit", 120L);

        AdminRegistrationDto adminRegistrationDto = new AdminRegistrationDto("Juan", "Ramiro", "correosupervalido@gmail.com", "Holahola#", "3123232345", "07-07-2000");
        String page = registrationController.registerAdminAccount(adminRegistrationDto, session);

        assertEquals("redirect:/reg/admin?error", page);

    }

    @Test
    @Rollback
    void registerAdminAccountTest3() {

        HttpSession session = new MockHttpSession();

        session.setAttribute("nit", 1200076768L);

        AdminRegistrationDto adminRegistrationDto = new AdminRegistrationDto("Juan", "Ramiro", "nuevo@unal.edu.co", "Holahola#", "3123232345", "07-07-2000");
        String page = registrationController.registerAdminAccount(adminRegistrationDto, session);

        assertEquals("error", page);

    }

}