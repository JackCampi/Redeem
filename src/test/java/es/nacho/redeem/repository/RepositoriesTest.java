package es.nacho.redeem.repository;
/*
import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.internal.util.collections.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RepositoriesTest {

    private final String COMPANY_NAME = "";
    private final Long COMPANY_NIT = new Long(2354252L);
    private final Long COMPANY_BUDGET= new Long(53636L);
    private final String FIRST_AREA_NAME = "Bodega";
    private final String SECOND_AREA_NAME = "Recursos humanos";
    private final String EMPLOYEE_NAME="Juan";
    private final String EMPLOYEE_LASTNAME="Perez";
    private final String EMPLOYEE_EMAIL="juan@hotmail.com";
    private final String EMPLOYEE_PASSWORD="1234567";
    private final String EMPLOYEE_CELLPHONE="3124353390";
    private final Calendar EMPLOYEE_BIRTHDATE= Calendar.getInstance();
    private final Long EMPLOYEE_BALANCE=new Long(50000L);
    private final Boolean EMPLOYEE_ACTIVE=true;
    private final String EMPLOYEE_ROL="Carguero";




    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    EmployeeRepository employeeRepository;


    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void SaveCompany(){
        Company company = new Company(COMPANY_NIT,COMPANY_NAME,COMPANY_BUDGET);
        Company savedCompanyData = this.entityManager.persistAndFlush(company);
        assertThat(savedCompanyData.getId()).isEqualTo(COMPANY_NIT);
    }

    @Test
    public void SaveArea(){
        Company company = new Company(COMPANY_NIT,COMPANY_NAME,COMPANY_BUDGET);
        Area firstArea = new Area(FIRST_AREA_NAME,company);
        Area secondArea = new Area(SECOND_AREA_NAME,company);
        companyRepository.save(company);
        Area firstDataArea = this.entityManager.persistAndFlush(firstArea);
        Area secondDataArea = this.entityManager.persistAndFlush(secondArea);
        assertThat(firstDataArea.getId().getName()).isEqualTo(FIRST_AREA_NAME);
        assertThat(secondDataArea.getId().getName()).isEqualTo(SECOND_AREA_NAME);
    }


    @Test
    public void SaveEmployee(){
        Company company = new Company(COMPANY_NIT,COMPANY_NAME,COMPANY_BUDGET);
        Area firstArea = new Area(FIRST_AREA_NAME,company);
        Employee employee = new Employee(EMPLOYEE_NAME,EMPLOYEE_LASTNAME,
                EMPLOYEE_EMAIL,EMPLOYEE_PASSWORD,EMPLOYEE_CELLPHONE,
                EMPLOYEE_BIRTHDATE,EMPLOYEE_BALANCE,EMPLOYEE_ACTIVE,
                EMPLOYEE_ROL,firstArea);
        companyRepository.save(company);
        areaRepository.save(firstArea);

        Employee dataEmployee = this.entityManager.persistAndFlush(employee);
        assertThat(dataEmployee.getEmail()).isEqualTo(EMPLOYEE_EMAIL);
    }

    @Test
    public void findCompany(){
        Company company = new Company(COMPANY_NIT,COMPANY_NAME,COMPANY_BUDGET);
        companyRepository.save(company);
        Optional<Company> newCompany = companyRepository.findById(COMPANY_NIT);
        assertThat(newCompany.get().getId()).isEqualTo(COMPANY_NIT);
    }

    @Test
    public void findArea(){
        Company company = new Company(COMPANY_NIT,COMPANY_NAME,COMPANY_BUDGET);
        Area firstArea = new Area(FIRST_AREA_NAME,company);
        companyRepository.save(company);
        areaRepository.save(firstArea);
        Optional<Area> newArea = areaRepository.findById(firstArea.getId());
        assertThat(newArea.get().getId().getName()).isEqualTo(FIRST_AREA_NAME);
    }

    @Test
    public void findEmployee(){
        Company company = new Company(COMPANY_NIT,COMPANY_NAME,COMPANY_BUDGET);
        Area firstArea = new Area(FIRST_AREA_NAME,company);
        Employee employee = new Employee(EMPLOYEE_NAME,EMPLOYEE_LASTNAME,
                EMPLOYEE_EMAIL,EMPLOYEE_PASSWORD,EMPLOYEE_CELLPHONE,
                EMPLOYEE_BIRTHDATE,EMPLOYEE_BALANCE,EMPLOYEE_ACTIVE,
                EMPLOYEE_ROL,firstArea);
        companyRepository.save(company);
        areaRepository.save(firstArea);
        employeeRepository.save(employee);
        Employee newEmployee = employeeRepository.findByEmail(EMPLOYEE_EMAIL);
        assertThat(newEmployee.getName()).isEqualTo(EMPLOYEE_NAME);
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createCompanyNullNameException() throws Exception{
        this.thrown.expect(IllegalArgumentException.class);
        this.thrown.expectMessage("Company name should not be empty");
        new Company(2000000L,"",2000000L);
    }


 /*   @Test

    public void removeCompanyArea(){
        company.addAreas(area1);
        company.addAreas(area2);

        Company savedCompany = this.entityManager.persistFlushFind(company);

        savedCompany.removeArea(area2);

        Company updatedData = this.entityManager.persistAndFlush(savedCompany);
        assertThat(updatedData.getAreas().size()).isEqualTo(1);
        //assertThat(Iterables.get(updatedData.getAreas(),0)).isEqualTo("gerencia");
    }*/

    /*@Test
    @Rollback
    public void testRegisterCompany(){
        initTest();
        Company savedCompany = companyRepository.save(companyExample);
        assertNotNull(savedCompany);
    }

    @Test
    public void testFindCompany(){
        initTest();
        Long id = companyExample.getId();

    }

}
*/