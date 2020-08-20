package com.demo.brprev.Repository;

import com.demo.brprev.Entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//Informa que a uma interface de um repositório
@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    //Assinatura do método que retorna um cliente pelo cpf informado
    Optional<Client> findOptionalByCpf(long cpf);
}