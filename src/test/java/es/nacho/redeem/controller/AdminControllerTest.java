package es.nacho.redeem.controller;

import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.repository.AreaRepository;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.repository.EmployeeRepository;
import es.nacho.redeem.service.UserService;
import es.nacho.redeem.web.dto.EmployeeRegistrationDto;
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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.transaction.Transactional;

import java.util.Collection;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebAppConfiguration
@SpringBootTest
@Transactional
class AdminControllerTest {

    //@Autowired
    //MockHttpSession session;
    @Autowired
    AdminController controller;

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
    void registerEmployeeTest1() {

        HttpSession session = new MockHttpSession();

        session.setAttribute("nit", 12L);

        EmployeeRegistrationDto  employeeRegistrationDto= new EmployeeRegistrationDto("hola", "hola", "nuevo@nuevo.com", "hola", "23423", "12-12-2333", "gerencia", "hola");
        String page = controller.registerEmployee(employeeRegistrationDto, session);

        assertEquals("redirect:/admin/addemp?success", page);

    }

    @Test
    @Rollback
    void registerEmployeeTest2() {

        HttpSession session = new MockHttpSession();

        session.setAttribute("nit", 120L);

        EmployeeRegistrationDto  employeeRegistrationDto= new EmployeeRegistrationDto("hola", "hola", "correosupervalido@gmail.com", "hola", "23423", "12-12-2333", "gerencia", "hola");
        String page = controller.registerEmployee(employeeRegistrationDto, session);

        assertEquals("redirect:/admin/addemp?error", page);

    }

    @Test
    @Rollback
    void registerEmployeeTest3() {

        HttpSession session = new MockHttpSession();

        session.setAttribute("nit", 120L);

        EmployeeRegistrationDto  employeeRegistrationDto= new EmployeeRegistrationDto("hola", "hola", "hola", "hola", "23423", "12-12-2333", "gerencia", "hola3");
        String page = controller.registerEmployee(employeeRegistrationDto, session);

        assertEquals("error", page);

    }

    @Test
    @Rollback
    void registerEmployeeTest4() {

        HttpSession session = new MockHttpSession();

        session.setAttribute("nit", 1200989879L);

        EmployeeRegistrationDto  employeeRegistrationDto= new EmployeeRegistrationDto("hola", "hola", "hola", "hola", "23423", "12-12-2333", "gerencia", "hola");
        String page = controller.registerEmployee(employeeRegistrationDto, session);

        assertEquals("error", page);

    }


}