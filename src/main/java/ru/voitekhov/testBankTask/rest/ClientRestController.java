package ru.voitekhov.testBankTask.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.voitekhov.testBankTask.exception.NotFoundException;
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
        Client client = service.get(id);
        if (client == null) {
            throw new NotFoundException(String.format("Client with id: %s not found", id));
        }
        return client;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Client save(@RequestBody @Valid Client client) {
        return service.save(client);
    }

    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean delete(@PathVariable("id") int id) {
        if (!service.delete(id)) {
            throw new NotFoundException(String.format("Client with id: %s not found", id));
        }
        return true;
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Client update(@RequestBody @Valid Client client) {
        return service.save(client);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> getAll() {
        List<Client> clients = service.getAll();
        if (clients == null) {
            throw new NotFoundException("Clients not found");
        }
        return clients;
    }

    @GetMapping(value = "filtered", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Client> filtered(@RequestBody LegalStatus legalStatus) {
        List<Client> clients = service.sortByLegalStatus(legalStatus);
        if (clients == null) {
            throw new NotFoundException(String.format("Clients with legal status %s not found",
                    legalStatus.getTitle()));
        }
        return clients;
    }
}

