package ru.voitekhov.testBankTask.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.voitekhov.testBankTask.model.Deposit;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer> {


   /* @Query("SELECT d FROM Deposit d WHERE d.bank.id=:bankId AND d.client.id=:clientId AND d.id=:depositId")
    Deposit get(@Param("bankId") int bankId,
                @Param("clientId") int clientId,
                @Param("depositId") int depositId);*/

    @Transactional
    @Modifying
    @Query("DELETE FROM Deposit d WHERE d.id=:id")
    int delete(@Param("id") int id);

    /*@Query("SELECT d FROM Deposit d WHERE d.bank.id=:bankId AND d.client.id=:clientId")
    List<Deposit> getAll(@Param("bankId") int bankId,
                         @Param("clientId") int clientId);*/

}
