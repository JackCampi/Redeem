package es.nacho.redeem.service;

import es.nacho.redeem.model.Area;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.model.Transfer;
import es.nacho.redeem.repository.AreaRepository;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
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
class TransferServiceImplTest {

    @Autowired
    TransferService transferService;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    AreaRepository areaRepository;

    @Autowired
    EmployeeRepository employeeRepository;

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
                "correosupervalido@gmail.com2",
                "hola",
                "323323232323",
                new GregorianCalendar(2002,02,02),
                50000L,
                true,
                "administrador",
                area
        ));

    }

    @AfterAll
    void after(){
        Employee employee = employeeRepository.findByEmail("correosupervalido@gmail.com");
        employeeRepository.delete(employee);

        Employee employee2 = employeeRepository.findByEmail("correosupervalidoperonoactivo@gmail.com");
        employeeRepository.delete(employee);

        Optional<Company> company = companyRepository.findById(Long.MAX_VALUE);

        Collection<Area> area = areaRepository.findByCompany(company.get());

        area.forEach(area1 -> areaRepository.delete(area1));

        companyRepository.delete(company.get());

    }

    @Test
    @Rollback
    void saveTransferTest(){

        Employee employeeFrom = employeeRepository.findByEmail("correosupervalido@gmail.com");
        Employee employeeTo = employeeRepository.findByEmail("correosupervalido@gmail.com2");

        Transfer transfer = transferService.saveTransfer(employeeFrom, employeeTo, 2000L);

        assertEquals(transfer.getAmount(), 2000L);
        assertEquals(transfer.getEmployeeFrom(), employeeFrom);
        assertEquals(transfer.getEmployeeTo(), employeeTo);

    }

}