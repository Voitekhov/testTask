package ru.voitekhov.testBankTask.service;

import ru.voitekhov.testBankTask.model.Client;
import ru.voitekhov.testBankTask.model.LegalStatus;

import java.util.List;

public interface ClientService {

    Client save(Client client);

    Client get(int id);

    boolean delete(int id);

    List<Client> getAll();

    List<Client> sortByLegalStatus(LegalStatus legalStatus);

}
