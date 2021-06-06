package ru.voitekhov.testBankTask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voitekhov.testBankTask.exception.NotFoundException;
import ru.voitekhov.testBankTask.exception.BadRequestException;
import ru.voitekhov.testBankTask.model.Bank;
import ru.voitekhov.testBankTask.service.BankService;
import ru.voitekhov.testBankTask.repository.datajpa.BankRepository;

import java.util.List;

@Service
public class BankServiceImpl implements BankService {

    final BankRepository crudRepository;

    @Autowired
    public BankServiceImpl(BankRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    @Transactional
    public Bank save(Bank bank) {
        if (this.countBanksWithBik(bank.getBik()) > 0 && bank.isNew()) {
            throw new BadRequestException("Bik is not unique");
        }
        if (this.countBanksWithBik(bank.getBik()) > 0 && !bank.isNew() && getByBik(bank.getBik()).getId() != bank.getId()) {
            throw new BadRequestException("Bik is not unique");
        }
        return crudRepository.save(bank);
    }

    @Override
    public boolean delete(int id) {
        if (crudRepository.delete(id) != 0) {
            return true;
        }
        throw new NotFoundException(String.format("Bank with id: %s not found", id));
    }

    @Override
    public Bank get(int id) {
        return crudRepository.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Bank with id: %s not found", id)));
    }

    @Override
    public List<Bank> getAll() {
        List<Bank> banks = crudRepository.findAll();
        if (!banks.isEmpty()) {
            return banks;
        }
        throw new NotFoundException("Banks not found");
    }

    @Override
    public List<Bank> getAllSorted() {
        List<Bank> banks = crudRepository.getAllSorted();
        if (!banks.isEmpty()) {
            return banks;
        }
        throw new NotFoundException("Banks not found");
    }

    @Override
    public Bank getByBik(String bik) {
        Bank bank = crudRepository.findByBik(bik);
        if (bank != null) {
            return bank;
        }
        throw new NotFoundException(String.format("Bank with bik: %s not found", bik));
    }

    private int countBanksWithBik(String bik) {
        return crudRepository.countBik(bik);
    }


}
