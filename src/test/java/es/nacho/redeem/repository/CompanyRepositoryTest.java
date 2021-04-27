package es.nacho.redeem.repository;

import es.nacho.redeem.model.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CompanyRepositoryTest {

    @Autowired
    CompanyRepository companyRepository;
    Company companyExample;

    public void initTest(){
        companyExample = new Company(1234L,"Company",10000L);
    }

    @Test
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