package es.nacho.redeem.transaction;

import es.nacho.redeem.exception.InsufficientBalanceException;
import es.nacho.redeem.model.Employee;
import es.nacho.redeem.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebAppConfiguration
@SpringBootTest
@Transactional
class BalanceTransactionImplTest {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    BalanceTransaction balanceTransaction;

    @Test
    void userToUserBalanceTransaction() throws InsufficientBalanceException {

        Employee employee = employeeRepository.findByEmail("brayandromeroa94@gmail.com");
        Employee employee1 = employeeRepository.findByEmail("a@gmail.com");

        try{
            balanceTransaction.userToUserBalanceTransaction(false, employee.getId(), "987676766", 2000L);
        }catch (Exception e){
            System.out.println("hola");
            System.out.println("hola");
        }

    }
}