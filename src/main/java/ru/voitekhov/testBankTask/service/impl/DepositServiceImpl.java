package ru.voitekhov.testBankTask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voitekhov.testBankTask.model.Bank;
import ru.voitekhov.testBankTask.model.Client;
import ru.voitekhov.testBankTask.model.Deposit;
import ru.voitekhov.testBankTask.repository.datajpa.BankRepository;
import ru.voitekhov.testBankTask.repository.datajpa.ClientRepository;
import ru.voitekhov.testBankTask.repository.datajpa.DepositRepository;
import ru.voitekhov.testBankTask.service.DepositService;

import java.util.List;

@Service
public class DepositServiceImpl implements DepositService {

    final ClientRepository clientRepository;
    final BankRepository bankRepository;
    final DepositRepository depositRepository;

    @Autowired
    public DepositServiceImpl(ClientRepository clientRepository, BankRepository bankRepository, DepositRepository depositRepository) {
        this.clientRepository = clientRepository;
        this.bankRepository = bankRepository;
        this.depositRepository = depositRepository;
    }

    @Override
    @Transactional
    public Deposit save(int bankId, int clientId, Deposit deposit) {
        Client client = clientRepository.findById(clientId).orElse(null);
        Bank bank = bankRepository.findById(bankId).orElse(null);
        if (client == null || bank == null) {
            return null;
        }
        deposit.setBank(bank);
        deposit.setClient(client);
        return depositRepository.save(deposit);
    }

    @Override
    public Deposit get(int id) {
        return depositRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return depositRepository.delete(id) != 0;
    }

    @Override
    public List<Deposit> getAll() {
        return depositRepository.findAll();
    }
}
