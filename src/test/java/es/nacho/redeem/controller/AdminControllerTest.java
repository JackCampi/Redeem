package es.nacho.redeem.controller;

import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.repository.AreaRepository;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.repository.EmployeeRepository;
import es.nacho.redeem.web.dto.AllocationDto;
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

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Collection;
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
        Area area2 = new Area("bodega", company);
        areaRepository.save(area2);
        Area area3 = new Area("compras", company);
        areaRepository.save(area3);

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
        employeeRepository.save(new Employee(
                "emp",
                "test1",
                "emptest1@gmail.com",
                "1234",
                "1111",
                new GregorianCalendar(2002,02,02),
                0L,
                true,
                "administrador",
                area
        ));
        employeeRepository.save(new Employee(
                "emp",
                "test2",
                "emptest2@gmail.com",
                "1234",
                "1111",
                new GregorianCalendar(2002,02,02),
                0L,
                true,
                "empleado",
                area2
        ));
        employeeRepository.save(new Employee(
                "emp",
                "test3",
                "emptest3@gmail.com",
                "1234",
                "1111",
                new GregorianCalendar(2002,02,02),
                0L,
                true,
                "empleado",
                area3
        ));
    }

    @AfterAll
    void after(){
        Employee employee = employeeRepository.findByEmail("correosupervalido@gmail.com");
        employeeRepository.delete(employee);

        Employee employeeTest1 = employeeRepository.findByEmail("emptest1@gmail.com");
        employeeRepository.delete(employeeTest1);

        Employee employeeTest2 = employeeRepository.findByEmail("emptest2@gmail.com");
        employeeRepository.delete(employeeTest2);

        Optional<Company> company = companyRepository.findById(120L);

        Collection<Area> area = areaRepository.findByCompany(company.get());

        area.forEach(area1 -> areaRepository.delete(area1));

        companyRepository.delete(company.get());

    }

    @Test
    @Rollback
    void registerEmployeeTest1() {

        HttpSession session = new MockHttpSession();

        session.setAttribute("nit", 120L);

        EmployeeRegistrationDto  employeeRegistrationDto= new EmployeeRegistrationDto("hola", "hola", "nuevo@gmail.com", "hola", "23423", "12-12-2333", "gerencia", "hola");
        String page = controller.registerEmployee(employeeRegistrationDto, session);

        assertEquals("error", page);

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

    @Test
    @Rollback
    void processEmployeeAllocationTest() {

        HttpSession session = new MockHttpSession();
        Employee employee = employeeRepository.findByEmail("emptest1@gmail.com");
        session.setAttribute("nit", 120L);
        session.setAttribute("id", employee.getId());

        AllocationDto allocationDto = new AllocationDto("emptest2@gmail.com",5000L);
        String page = controller.processEmployeeAllocation(allocationDto, session);

        assertEquals("redirect:/admin/allocation/emp?success", page);
        employee.getOutgoinAllocations().forEach(allocation -> System.out.println(allocation.getDescription()));

    }

    @Test
    @Rollback
    void processEmployeeAllocationTest2() {

        HttpSession session = new MockHttpSession();
        Employee employee = employeeRepository.findByEmail("emptest1@gmail.com");
        session.setAttribute("nit", 120L);
        session.setAttribute("id", employee.getId());

        AllocationDto allocationDto = new AllocationDto("emptest1@gmail.com",5000L);
        String page = controller.processEmployeeAllocation(allocationDto, session);

        assertEquals("redirect:/admin/allocation/emp?userNotFound", page);

    }

    @Test
    @Rollback
    void processEmployeeAllocationTest3() {

        HttpSession session = new MockHttpSession();
        Employee employee = employeeRepository.findByEmail("emptest1@gmail.com");
        session.setAttribute("nit", 120L);
        session.setAttribute("id", employee.getId());

        AllocationDto allocationDto = new AllocationDto("emptest2@gmail.com",10000001L);
        String page = controller.processEmployeeAllocation(allocationDto, session);

        assertEquals("redirect:/admin/allocation/emp?insufficient", page);

    }

    @Test
    @Rollback
    void processCompanyAllocationTest() {

        HttpSession session = new MockHttpSession();
        Employee employee = employeeRepository.findByEmail("emptest1@gmail.com");
        session.setAttribute("nit", 120L);
        session.setAttribute("id", employee.getId());

        AllocationDto allocationDto = new AllocationDto(10000L);
        String page = controller.processCompanyAllocation(allocationDto, session);

        assertEquals("redirect:/admin/allocation/comp?success", page);
        employee.getOutgoinAllocations().forEach(allocation -> System.out.println(allocation.getDescription()));
    }

    @Test
    @Rollback
    void processCompanyAllocationTest2() {

        HttpSession session = new MockHttpSession();
        Employee employee = employeeRepository.findByEmail("emptest1@gmail.com");
        session.setAttribute("nit", 12L);
        session.setAttribute("id", employee.getId());

        AllocationDto allocationDto = new AllocationDto(1000L);
        String page = controller.processCompanyAllocation(allocationDto, session);

        assertEquals("redirect:/admin/allocation/comp?companyNotFound", page);

    }

    @Test
    @Rollback
    void processCompanyAllocationTest3() {

        HttpSession session = new MockHttpSession();
        Employee employee = employeeRepository.findByEmail("emptest1@gmail.com");
        session.setAttribute("nit", 120L);
        session.setAttribute("id", employee.getId());

        AllocationDto allocationDto = new AllocationDto(9999999L);
        String page = controller.processCompanyAllocation(allocationDto, session);

        assertEquals("redirect:/admin/allocation/comp?insufficient", page);

    }

    @Test
    @Rollback
    void processAreaAllocationTest() {

        HttpSession session = new MockHttpSession();
        Employee employee = employeeRepository.findByEmail("emptest1@gmail.com");
        session.setAttribute("nit", 120L);
        session.setAttribute("id", employee.getId());

        Collection<String> areas = new ArrayList<>();
        areas.add("bodega");
        areas.add("compras");

        AllocationDto allocationDto = new AllocationDto(areas,10000L);
        String page = controller.processAreaAllocation(allocationDto, session);

        assertEquals("redirect:/admin/allocation/area?success", page);

    }

    @Test
    @Rollback
    void processAreaAllocationTest2() {

        HttpSession session = new MockHttpSession();
        Employee employee = employeeRepository.findByEmail("emptest1@gmail.com");
        session.setAttribute("nit", 120L);
        session.setAttribute("id", employee.getId());

        Collection<String> areas = new ArrayList<>();
        areas.add("bodega");
        areas.add("comunicaciones");

        AllocationDto allocationDto = new AllocationDto(areas,10000L);
        String page = controller.processAreaAllocation(allocationDto, session);

        assertEquals("redirect:/admin/allocation/area?areaNotFound", page);

    }

    @Test
    @Rollback
    void processAreaAllocationTest3() {

        HttpSession session = new MockHttpSession();
        Employee employee = employeeRepository.findByEmail("emptest1@gmail.com");
        session.setAttribute("nit", 120L);
        session.setAttribute("id", employee.getId());

        Collection<String> areas = new ArrayList<>();
        areas.add("bodega");
        areas.add("compras");

        AllocationDto allocationDto = new AllocationDto(areas,10000000L);
        String page = controller.processAreaAllocation(allocationDto, session);

        assertEquals("redirect:/admin/allocation/area?insufficient", page);

    }
}