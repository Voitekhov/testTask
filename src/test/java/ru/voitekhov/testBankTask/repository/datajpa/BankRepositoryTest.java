package ru.voitekhov.testBankTask.repository.datajpa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.voitekhov.testBankTask.model.Bank;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class BankRepositoryTest {

    @Autowired
    BankRepository bankRepository;

    @AfterEach
    void tearDown() {
        bankRepository.deleteAll();
    }

    @Test
    void deleteExistedBank() {
        //given
        Bank bank = bankRepository.save(new Bank("test bank", "12345678"));

        //when
        int expected = bankRepository.delete(bank.getId());

        //then
        assertThat(expected).isEqualTo(1);

    }

    @Test
    void deleteNotExistedBank() {
        //given
        int notExistedId = 1;

        //when
        int expected = bankRepository.delete(notExistedId);

        //then
        assertThat(expected).isEqualTo(0);

    }

    @Test
    void countExistedBik() {
        //given
        Bank bank = bankRepository.save(new Bank("test bank", "12345678"));

        //when
        int expected = bankRepository.countBik(bank.getBik());

        //then
        assertThat(expected).isEqualTo(1);

    }

    @Test
    void countNotExistedBik() {
        //given
        String someBik = "12312311";

        //when
        int expected = bankRepository.countBik(someBik);

        //then
        assertThat(expected).isEqualTo(0);

    }

    @Test
    void getAllSorted() {
        //given
        Bank bank1 = new Bank("b", "12345678");
        Bank bank2 = new Bank("a", "12111111");
        List<Bank> banks = new ArrayList<>();
        banks.add(bank2);
        banks.add(bank1);
        bankRepository.save(bank1);
        bankRepository.save(bank2);

        //when
        List<Bank> expected = bankRepository.getAllSorted();

        //that
        assertThat(banks).isEqualTo(expected);
    }

    @Test
    void findByBikExistedBank() {
        //given
        Bank bank = bankRepository.save(new Bank("TEST", "12345678"));

        //when
        Bank expected = bankRepository.findByBik(bank.getBik());

        //than
        assertThat(bank).isEqualTo(expected);


    }

    @Test
    void findByBikNotExistedBank() {
        //given
        Bank bank = null;
        String someBik = "12312311";

        //when
        Bank expected = bankRepository.findByBik(someBik);

        //than
        assertThat(bank).isEqualTo(expected);
    }
}