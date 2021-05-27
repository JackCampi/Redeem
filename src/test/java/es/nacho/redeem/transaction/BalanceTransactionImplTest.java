package es.nacho.redeem.transaction;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.model.Company;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.repository.CompanyRepository;
import es.nacho.redeem.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebAppConfiguration
@SpringBootTest
@Transactional
class BalanceTransactionImplTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    BalanceTransaction balanceTransaction;

    @Test
    void userToUserBalanceTransaction() throws InsufficientBalanceException {

        Employee employee = employeeRepository.findByEmail("brayandromeroa94@gmail.com");
        Employee employee1 = employeeRepository.findByEmail("a@gmail.com");

        try{
            balanceTransaction.userToUserBalanceTransaction(employee.getId(), "987676766", 2000L);
        }catch (Exception e){
            System.out.println("hola");
            System.out.println("hola");
        }

    }

    @Test
    void userToUsersBalanceTransaction() throws InsufficientBalanceException {

        long nit = 1;
        Optional<Company> company = companyRepository.findById(nit);
        Employee admin = employeeRepository.findByEmail("admin1@gmail.com");
        Employee employee1 = employeeRepository.findByEmail("emp1@gmail.com");
        Employee employee2 = employeeRepository.findByEmail("emp2@gmail.com");

        Collection<Long> employeesIds =  new ArrayList<>();
        employeesIds.add(employee1.getId());
        employeesIds.add(employee2.getId());

        System.out.println("company budget");
        System.out.println(company.get().getBudget());
        System.out.println("emp1 balance");
        System.out.println(employee1.getBalance());
        System.out.println("emp2 balance");
        System.out.println(employee2.getBalance());



        try{
            balanceTransaction.userToUsersBalanceTransaction(nit, admin.getId(), employeesIds, 2000L);

            System.out.println("company budget");
            System.out.println(company.get().getBudget());
            System.out.println("emp1 balance");
            System.out.println(employee1.getBalance());
            System.out.println("emp2 balance");
            System.out.println(employee2.getBalance());
        }catch (Exception e){
            System.out.println("fallo");
        }
    }
}