package ru.voitekhov.testBankTask.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
        return service.get(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Deposit> getAll() {
        return service.getAll();
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Deposit save(@RequestBody @Valid Deposit deposit, @RequestParam int bankId,
                        @RequestParam int clientId) {
        return service.save(bankId, clientId, deposit);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Deposit update(@RequestBody @Valid Deposit deposit, @RequestParam int bankId,
                          @RequestParam int clientId) {
        return service.save(bankId, clientId, deposit);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean delete(@PathVariable("id") int id) {
        return service.delete(id);
    }
}
