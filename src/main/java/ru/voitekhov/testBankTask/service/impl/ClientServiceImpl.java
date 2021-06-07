package ru.voitekhov.testBankTask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voitekhov.testBankTask.model.Client;
import ru.voitekhov.testBankTask.model.LegalStatus;
import ru.voitekhov.testBankTask.repository.datajpa.ClientRepository;
import ru.voitekhov.testBankTask.service.ClientService;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    final ClientRepository crudRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository crudRepository) {
        this.crudRepository = crudRepository;
    }

    @Override
    @Transactional
    public Client save(Client client) {
        return crudRepository.save(client);
    }

    @Override
    public Client get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = crudRepository.findAll();
        return clients.isEmpty() ? null : clients;
    }

    @Override
    public List<Client> sortByLegalStatus(LegalStatus legalStatus) {
        List<Client> clients = crudRepository.sortByLegalStatus(legalStatus);
        return clients.isEmpty() ? null : clients;
    }
}
