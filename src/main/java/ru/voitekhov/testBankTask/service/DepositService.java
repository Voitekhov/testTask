package ru.voitekhov.testBankTask.service;

import ru.voitekhov.testBankTask.model.Deposit;

import java.util.List;

public interface DepositService {

    Deposit save(int bankId, int clientId, Deposit deposit);

    Deposit get(int bankId);

    boolean delete(int bankId);

    List<Deposit> getAll();

}
