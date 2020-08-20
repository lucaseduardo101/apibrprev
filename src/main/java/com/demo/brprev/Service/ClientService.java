package com.demo.brprev.Service;

import com.demo.brprev.Dto.ClientDto;
import com.demo.brprev.Entity.Client;

import java.util.List;

public interface ClientService {

    Client find(int id);
    List<Client> findAll();
    Client update(int idClient, ClientDto client);
    Client insert(ClientDto client);
    void delete(int idClient);
}
