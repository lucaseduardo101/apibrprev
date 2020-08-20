package com.demo.brprev.Service.Impl;

import lombok.var;
import com.demo.brprev.Controller.Exceptions.Type.DataIntegrationException;
import com.demo.brprev.Dto.ClientDto;
import com.demo.brprev.Entity.Client;
import com.demo.brprev.Repository.ClientRepository;
import com.demo.brprev.Service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Informando que a classe é um service
@Service
public class ClientServiceImpl implements ClientService {

    // Instanciando atraves da ingessão de dependencia um objeto do tipo
    // ClientRepository que nos permite manipular nosso banco de dados
    @Autowired
    ClientRepository clientRepository;

    public Client find(int id){
        //Realiza a busca de um cliente através do Id
        //O metodo findById retorna um objeto do tipo Optional
        var cli = clientRepository.findById(id);

        //Verifica se o Usuário existe
        if(!cli.isPresent()){
            //Lança uma exceção informando que o cliente não existe
            throw new DataIntegrationException("Cliente não existe");
        }

        return cli.get();
    }

    //Busca todos os clientes da base
    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    // Realiza o update do Cliente
    public Client update(int idClient, ClientDto client){

        //Busca o cliente que será alterado
        var cli = this.find(idClient);

        //sobrescreve os valores antigos pelos novos
        cli.setName(client.getName());
        cli.setCpf(client.getCpf());
        cli.setAdress(client.getAdress());

        // Salva a alteração no banco de dados
        return   clientRepository.save(cli);
    }

    //Insere um novo cliente no banco de dados
    public Client insert(ClientDto client) {

        //Busca um cliente através do cpf informado
        var cli = clientRepository.findOptionalByCpf(client.getCpf());

        //Verifica se já existe um cliente com o cpf informado
        if(cli.isPresent()){
            //Caso já exista um cliente cadastrado com o mesmo cpf informado
            //é retornado uma exceção informando que esse cpf ja foi cadastrado para outro cliente
            throw new DataIntegrationException("CPF Ja Cadastrado");
        }

        //Salva um novo Cliente
        // Não estamos informando um id pois estamos salvando um novo cliente
        return  clientRepository.save( Client.builder()
                .name(client.getName())
                .cpf(client.getCpf())
                .adress(client.getAdress())
                .build());
    }

    public void delete(int idClient){
        //Busca o cliente que será deletado
        var cli = this.find(idClient);
        //deleta o Cliente
        clientRepository.delete(cli);
    }
}
