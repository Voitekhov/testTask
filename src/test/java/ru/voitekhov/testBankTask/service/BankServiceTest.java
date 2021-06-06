package ru.voitekhov.testBankTask.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import ru.voitekhov.testBankTask.model.Bank;

import static org.junit.jupiter.api.Assertions.*;
import static ru.voitekhov.testBankTask.BankTestData.*;

@SpringBootTest
/*@Sql(scripts = "classpath:/database/populateDB.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)*/
class BankServiceTest {

    @Autowired
    BankService service;

    @Test
    void save() {
        Bank savedBank = service.save(BANK_NOT_IN_DB);
        assertEquals(savedBank, service.get(savedBank.getId()));
    }

    @Test
    void shouldSaveBankWhenIdNull() {
        /*
        //given
        final Bank bank = new Bank();

        //when
        Bank savedBank = service.save(bank);

        //then
        assertEquals(bank.getBik(), savedBank.getBik());

         */
    }

    @Test
    void delete() {
        assertTrue(service.delete(BANK_IN_DB.getId()));
    }

    @Test
    void deleteNotFound() {
        assertFalse(service.delete(BANK_NOT_IN_DB.getId()));
    }

    @Test
    void get() {
        assertEquals(BANK_IN_DB, service.get(BANK_IN_DB.getId()));
    }

    @Test
    void getNotFound() {
        assertNull(service.get(BANK_NOT_IN_DB.getId()));
    }

    @Test
    void getAll() {
        assertIterableEquals(BANKS_IN_DB, service.getAll());
    }

    @Test
    void getAllSorted() {
        assertIterableEquals(SORTED_BANKS_IN_DB, service.getAllSorted());
    }
}