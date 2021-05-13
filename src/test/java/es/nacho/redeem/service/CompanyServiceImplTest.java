package es.nacho.redeem.service;

import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.repository.AreaRepository;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.repository.EmployeeRepository;
import es.nacho.redeem.web.dto.AdminDashboardInfoDto;
import es.nacho.redeem.web.dto.CompanyRegistrationDto;
import es.nacho.redeem.web.dto.EmployeeDashboardInfoDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.web.WebAppConfiguration;
import javax.transaction.Transactional;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebAppConfiguration
@SpringBootTest
@Transactional
class CompanyServiceImplTest {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private AreaRepository areaRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompanyServiceImpl companyService;

    private String allAreas;
    private Company company;
    private Employee employee;

    @BeforeAll
    void setUp() {
        this.allAreas = "recursos humanos,seguridad,contabilidad,servicios generales,ventas";
        this.company = new Company(-123456789L,"Test",1L);
        companyRepository.save(company);
        Area area = new Area("gerencia", company);
        areaRepository.save(area);
        this.employee = new Employee(
                "juan",
                "veloza",
                "jveloza@gmail.com",
                "supapa",
                "123456789876",
                new GregorianCalendar(1970, Calendar.JUNE,20),
                0L,
                true,
                "administrador",
                area
        );
    }

    @Test
    void registerCompany() {
        CompanyRegistrationDto companyDto = new CompanyRegistrationDto();
        companyDto.setId(-12L);
        companyDto.setAreas(allAreas);
        companyDto.setName("CompanyX");
        assertDoesNotThrow(() -> {
            Company registeredCompany = companyService.registerCompany(companyDto);
            assertEquals(registeredCompany.getId(),-12L);
            assertEquals(registeredCompany.getName(),"CompanyX");
        });
    }

    @Test
    @Rollback
    void checkIfNitExistsWhenItExists() {
        assertThrows(Exception.class, () -> companyService.checkIfNitExists(company));
    }

    @Test
    @Rollback
    void checkIfNitExistsWhenItDoesNotExists() {
        assertDoesNotThrow(() -> companyService.checkIfNitExists(new Company(-12L,"TestComp",1L)));
    }

    @Test
    @Rollback
    void processAllAreas() {
        assertDoesNotThrow(() -> {
            companyService.processAllAreas(allAreas, company);
            Collection<String> areas = Arrays.asList(allAreas.split(","));
            Collection<Area> areasInDatabase = areaRepository.findByCompany(company);
            areasInDatabase.forEach(area -> {
                if(!area.getId().getName().equals("gerencia"))
                    assertTrue(areas.contains(area.getId().getName()));
            });
        });
    }

    @Test
    @Rollback
    void registerArea() {
        String[] areasToRegister = allAreas.split(",");
        HashMap<String, String> insertedAreas = new HashMap<>();
        assertDoesNotThrow(() -> {
            for (String area : areasToRegister) {
                companyService.registerArea(area, company);
                insertedAreas.put(area, area);
            }
        });
        Collection<Area> areasInDatabase = areaRepository.findByCompany(company);
        areasInDatabase.forEach(area -> {
            if(!area.getId().getName().equals("gerencia"))
                assertTrue(insertedAreas.containsKey(area.getId().getName()));
        });
    }

    @Test
    @Rollback
    void getAreasNames() {
        assertThrows(Exception.class,() -> companyService.getAreasNames(-1L), "Company not found");
        areaRepository.save(new Area("recursos humanos",company));
        areaRepository.save(new Area("servicios generales",company));
        areaRepository.save(new Area("seguridad",company));
        assertDoesNotThrow(() -> {
            Collection<String> areasInDataBase = companyService.getAreasNames(company.getId());
            assertTrue(areasInDataBase.contains("Recursos Humanos"));
            assertTrue(areasInDataBase.contains("Servicios Generales"));
            assertTrue(areasInDataBase.contains("Seguridad"));
        });
    }

    @Test
    @Rollback
    void getCompanyNitByUser() {
        assertThrows(Exception.class, () -> companyService.getCompanyNitByUser(employee.getEmail()));
        employeeRepository.save(employee);
        assertDoesNotThrow(() -> assertEquals(company.getId(),companyService.getCompanyNitByUser(employee.getEmail())));
    }

    @Test
    void fillAdminDashboardInfoDto() {
        AdminDashboardInfoDto adminDashboardInfoDto = new AdminDashboardInfoDto();
        adminDashboardInfoDto.setCompanyNit(company.getId());
        adminDashboardInfoDto.setName(company.getName());
        adminDashboardInfoDto.setCompanyBudget(company.getBudget());
        assertDoesNotThrow(() -> {
            AdminDashboardInfoDto dtoToTest = companyService.fillAdminDashboardInfoDto(company.getId(),new AdminDashboardInfoDto());
            assertEquals(adminDashboardInfoDto.getCompanyNit(), dtoToTest.getCompanyNit());
            assertEquals(adminDashboardInfoDto.getCompanyBudget(), dtoToTest.getCompanyBudget());
            assertEquals(adminDashboardInfoDto.getName(), dtoToTest.getCompanyName());
        });
    }

    @Test
    void fillEmployeeDashboardInfoDto() {
        EmployeeDashboardInfoDto employeeDashboardInfoDto = new EmployeeDashboardInfoDto();
        employeeDashboardInfoDto.setCompanyNit(company.getId());
        employeeDashboardInfoDto.setName(company.getName());
        assertDoesNotThrow(() -> {
            EmployeeDashboardInfoDto dtoToTest = companyService.fillEmployeeDashboardInfoDto(company.getId(), new EmployeeDashboardInfoDto());
            assertEquals(employeeDashboardInfoDto.getCompanyNit(), dtoToTest.getCompanyNit());
            assertEquals(employeeDashboardInfoDto.getName(), dtoToTest.getCompanyName());
        });
    }
}