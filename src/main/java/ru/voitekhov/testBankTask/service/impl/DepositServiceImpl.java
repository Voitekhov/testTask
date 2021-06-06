package ru.voitekhov.testBankTask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voitekhov.testBankTask.exception.BadRequestException;
import ru.voitekhov.testBankTask.exception.NotFoundException;
import ru.voitekhov.testBankTask.model.Bank;
import ru.voitekhov.testBankTask.model.Client;
import ru.voitekhov.testBankTask.model.Deposit;
import ru.voitekhov.testBankTask.service.DepositService;
import ru.voitekhov.testBankTask.repository.datajpa.BankRepository;
import ru.voitekhov.testBankTask.repository.datajpa.ClientRepository;
import ru.voitekhov.testBankTask.repository.datajpa.DepositRepository;

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
            throw new NotFoundException(String.format("Not found bank with id: %s or client " +
                    "with id %s", bankId, clientId));
        }
        deposit.setBank(bank);
        deposit.setClient(client);
        return depositRepository.save(deposit);
    }

    @Override
    public Deposit get(int id) {
        return depositRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Not found deposit " +
                        "with id %s", id)));
    }

    @Override
    public boolean delete(int id) {
        if (depositRepository.delete(id) != 0) {
            return true;
        }
        throw new NotFoundException(String.format("Not found deposit with id %s", id));
    }

    @Override
    public List<Deposit> getAll() {
        return depositRepository.findAll();
    }
}
