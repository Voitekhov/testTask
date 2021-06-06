package ru.voitekhov.testBankTask.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.voitekhov.testBankTask.model.Client;
import ru.voitekhov.testBankTask.model.LegalStatus;
import ru.voitekhov.testBankTask.service.ClientService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clients/")
public class ClientRestController {

   private final ClientService service;

    @Autowired
    public ClientRestController(ClientService service) {
        this.service = service;
    }

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Client get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Client save(@RequestBody @Valid Client client) {
        return service.save(client);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean delete(@PathVariable("id") int id) {
        return service.delete(id);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Client update(@RequestBody @Valid Client client) {
        return service.save(client);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "filtered", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> filtered(@RequestBody LegalStatus legalStatus) {
        return service.sortByLegalStatus(legalStatus);
    }
}
