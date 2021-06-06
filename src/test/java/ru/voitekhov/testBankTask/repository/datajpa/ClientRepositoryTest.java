package ru.voitekhov.testBankTask.repository.datajpa;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.voitekhov.testBankTask.model.Client;
import ru.voitekhov.testBankTask.model.LegalStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;

@DataJpaTest
class ClientRepositoryTest {

    @Autowired
    ClientRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }


    @Test
    void sortByLegalStatus() {
        // given
        Client bank1 = repository.save(new Client("TEST1", "TEST1",
                LegalStatus.LIMITED_LIABILITY_COMPANY));
        Client bank2 = repository.save(new Client("TEST2", "TEST2",
                LegalStatus.LIMITED_LIABILITY_COMPANY));
        Client bank3 = repository.save(new Client("TEST3", "TEST3",
                LegalStatus.PUBLIC_JOINT_STOCK_COMPANY));
        List<Client> clients = List.of(bank1, bank2);

        // when
        List<Client> expected = repository.sortByLegalStatus(LegalStatus.LIMITED_LIABILITY_COMPANY);

        //than
        assertThat(clients).isEqualTo(expected);

    }

    @Test
    void deleteExistedClient() {
        //given
        Client bank1 = repository.save(new Client("TEST1", "TEST1",
                LegalStatus.LIMITED_LIABILITY_COMPANY));

        //when
        int expected = repository.delete(bank1.getId());

        //than
        assertThat(1).isEqualTo(expected);
    }

    @Test
    void deleteNotExistedClient() {
        //given
        int notExistedId = 1;

        //when
        int expected = repository.delete(notExistedId);

        //than
        assertThat(0).isEqualTo(expected);
    }
}