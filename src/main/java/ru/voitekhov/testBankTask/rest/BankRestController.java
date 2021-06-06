package ru.voitekhov.testBankTask.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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
        return service.get(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Bank save(@RequestBody @Valid Bank bank) {
        return service.save(bank);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Bank update(@RequestBody @Valid Bank bank) {
        return service.save(bank);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean delete(@PathVariable("id") int id) {
        return service.delete(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bank> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "getAllSorted", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Bank> getAllSorted() {
        return service.getAllSorted();
    }

    @GetMapping(value = "findByBik/{bik}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Bank getByBik(@PathVariable("bik") String bik) {
        return service.getByBik(bik);
    }

}
