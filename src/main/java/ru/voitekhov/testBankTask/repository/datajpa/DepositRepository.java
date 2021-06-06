package ru.voitekhov.testBankTask.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.voitekhov.testBankTask.model.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Deposit d WHERE d.id=:id")
    int delete(@Param("id") int id);
}
