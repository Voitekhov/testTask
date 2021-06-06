package ru.voitekhov.testBankTask.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.voitekhov.testBankTask.model.Client;
import ru.voitekhov.testBankTask.model.LegalStatus;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query("SELECT c FROM Client c WHERE c.legalStatus=:legalStatus")
    List<Client> sortByLegalStatus(@Param("legalStatus") LegalStatus legalStatus);

    @Transactional
    @Modifying
    @Query("DELETE FROM Client c WHERE c.id=:id")
    int delete(@Param("id") int id);
}
