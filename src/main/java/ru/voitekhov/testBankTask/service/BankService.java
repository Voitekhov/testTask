package ru.voitekhov.testBankTask.service;

import org.springframework.data.repository.query.Param;
import ru.voitekhov.testBankTask.model.Bank;

import java.util.List;

public interface BankService {

    Bank save(Bank bank);

    boolean delete(int id);

    Bank get(int id);

    List<Bank> getAll();

    // sort by name
    List<Bank> getAllSorted();

    Bank getByBik(String bik);
}
