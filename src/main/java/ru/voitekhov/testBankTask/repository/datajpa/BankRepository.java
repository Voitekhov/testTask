package ru.voitekhov.testBankTask.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.voitekhov.testBankTask.model.Bank;

import java.util.List;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Bank b WHERE b.id=:id")
    int delete(@Param("id") int id);

    @Query("SELECT COUNT (b) FROM Bank b where b.bik=:bik")
    int countBik(@Param("bik") String bik);

    @Query("SELECT b FROM Bank b ORDER BY b.name")
    List<Bank> getAllSorted();

    @Query("SELECT b FROM Bank b WHERE b.bik=:bik")
    Bank findByBik(@Param("bik") String bik);
}
