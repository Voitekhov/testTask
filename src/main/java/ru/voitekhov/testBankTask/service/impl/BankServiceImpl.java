package ru.voitekhov.testBankTask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voitekhov.testBankTask.model.Bank;
import ru.voitekhov.testBankTask.repository.datajpa.BankRepository;
import ru.voitekhov.testBankTask.service.BankService;

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
        //save
        if (this.countBanksWithBik(bank.getBik()) == 0 && bank.isNew()) {
            return crudRepository.save(bank);
        }
        //update name
        if (this.countBanksWithBik(bank.getBik()) == 1 && getByBik(bank.getBik()).getId().equals(bank.getId())) {
            return crudRepository.save(bank);
        }
        //update bik
        if (this.countBanksWithBik(bank.getBik()) == 0 && !bank.isNew()) {
            return crudRepository.save(bank);
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Bank get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public List<Bank> getAll() {
        List<Bank> banks = crudRepository.findAll();
        return banks.isEmpty() ? null : banks;
    }

    @Override
    public List<Bank> getAllSorted() {
        List<Bank> banks = crudRepository.getAllSorted();
        return banks.isEmpty() ? null : banks;
    }

    @Override
    public Bank getByBik(String bik) {
        return crudRepository.findByBik(bik);
    }

    private int countBanksWithBik(String bik) {
        return crudRepository.countBik(bik);
    }

}
