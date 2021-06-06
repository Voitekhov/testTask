package ru.voitekhov.testBankTask.repository.datajpa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.voitekhov.testBankTask.model.Deposit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class DepositRepositoryTest {

    @Autowired
    DepositRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void deleteExistedDeposit() {
        //given
        Deposit deposit = repository.save(new Deposit(12, 11));

        //when
        int expected = repository.delete(deposit.getId());

        //than
        assertThat(1).isEqualTo(expected);
    }

    @Test
    void deleteNotExistedDeposit() {
        //given
        int notExistedId = 1;

        //when
        int expected = repository.delete(notExistedId);

        //than
        assertThat(0).isEqualTo(expected);
    }
}