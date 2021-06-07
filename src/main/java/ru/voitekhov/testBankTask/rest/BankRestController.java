package ru.voitekhov.testBankTask.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.voitekhov.testBankTask.exception.BadRequestException;
import ru.voitekhov.testBankTask.exception.NotFoundException;
import ru.voitekhov.testBankTask.model.Bank;
import ru.voitekhov.testBankTask.service.BankService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/banks/")
public class BankRestController {
    private final BankService service;

    @Autowired
    public BankRestController(BankService service) {
        this.service = service;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Bank get(@PathVariable("id") int id) {
        Bank bank = service.get(id);
        if (bank == null) {
            throw new NotFoundException(String.format("Bank with id: %s not found", id));
        }
        return bank;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Bank save(@RequestBody @Valid Bank bank) {
        Bank savedBank = service.save(bank);
        if (savedBank == null) {
            throw new BadRequestException("Bik is not unique");
        }
        return savedBank;
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Bank update(@RequestBody @Valid Bank bank) {
        Bank updatedBank = service.save(bank);
        if (updatedBank == null) {
            throw new BadRequestException("Bik is not unique");
        }
        return updatedBank;
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean delete(@PathVariable("id") int id) {
        if (!service.delete(id)) {
            throw new NotFoundException(String.format("Bank with id: %s not found", id));
        }
        return true;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bank> getAll() {
        List<Bank> banks = service.getAll();
        if (banks == null) {
            throw new NotFoundException("Banks not found");
        }
        return banks;
    }

    @GetMapping(value = "getAllSorted", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bank> getAllSorted() {
        List<Bank> banks = service.getAllSorted();
        if (banks == null) {
            throw new NotFoundException("Banks not found");
        }
        return banks;
    }

    @GetMapping(value = "findByBik/{bik}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Bank getByBik(@PathVariable("bik") String bik) {
        Bank bank = service.getByBik(bik);
        if (bank == null) {
            throw new NotFoundException(String.format("Bank with bik: %s not found", bik));
        }
        return bank;
    }

}
