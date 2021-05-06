package es.nacho.redeem.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebAppConfiguration
@SpringBootTest
@Transactional
class AreaServiceImplTest {

    @Autowired
    AreaService areaService;

    @Test
    void capitalizeAreaNamesTest() {

        ArrayList<String> areaNames = new ArrayList<>(Arrays.asList("gerencia", "talento humano", "servicio al cliente", "contabilidad"));
        Collection<String> areaNamesExpected = new ArrayList<>(Arrays.asList("Gerencia (Admin)", "Talento Humano", "Servicio Al Cliente", "Contabilidad"));
        Collection<String> areaNamesObtained = areaService.capitalizeAreaNames(areaNames);

        assertIterableEquals(areaNamesExpected, areaNamesObtained);


    }

    @Test
    void lowercaseAreaNamesTest() {

        ArrayList<String> areaNames = new ArrayList<>(Arrays.asList("Gerencia (Admin)", " Talento Humano ", "  Servicio al Cliente", "contabilidad"));
        Collection<String> areaNamesExpected = new ArrayList<>(Arrays.asList("gerencia", "talento humano", "servicio al cliente", "contabilidad"));
        Collection<String> areaNamesObtained = areaService.lowercaseAreaNames(areaNames);

        assertIterableEquals(areaNamesExpected, areaNamesObtained);

    }
}