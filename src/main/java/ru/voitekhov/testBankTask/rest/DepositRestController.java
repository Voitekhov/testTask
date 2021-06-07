package ru.voitekhov.testBankTask.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.voitekhov.testBankTask.exception.NotFoundException;
import ru.voitekhov.testBankTask.model.Deposit;
import ru.voitekhov.testBankTask.service.DepositService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/deposits/")
public class DepositRestController {

    private final DepositService service;

    @Autowired
    public DepositRestController(DepositService service) {
        this.service = service;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Deposit get(@PathVariable("id") int id) {
        Deposit deposit = service.get(id);
        if (deposit == null) {
            throw new NotFoundException(String.format("Not found deposit with id %s", id));
        }
        return deposit;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Deposit> getAll() {
        List<Deposit> deposits = service.getAll();
        if (deposits == null) {
            throw new NotFoundException("Deposits not found");
        }
        return service.getAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Deposit save(@RequestBody @Valid Deposit deposit, @RequestParam int bankId,
                        @RequestParam int clientId) {
        Deposit savedDeposit = service.save(bankId, clientId, deposit);
        if (savedDeposit == null) {
            throw new NotFoundException(String.format("Not found bank with id: %s or client " +
                    "with id %s", bankId, clientId));
        }
        return savedDeposit;
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Deposit update(@RequestBody @Valid Deposit deposit, @RequestParam int bankId,
                          @RequestParam int clientId) {
        Deposit updatedDeposit = service.save(bankId, clientId, deposit);
        if (updatedDeposit == null) {
            throw new NotFoundException(String.format("Not found bank with id: %s or client " +
                    "with id %s", bankId, clientId));
        }
        return updatedDeposit;
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean delete(@PathVariable("id") int id) {
        if (!service.delete(id)) {
            throw new NotFoundException(String.format("Not found deposit with id %s", id));
        }
        return true;
    }
}
