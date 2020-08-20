package com.demo.brprev.Entity;

import com.demo.brprev.Dto.ClientDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Builder
@Data
@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

    private static final long serialVersionUID = -1559864615786877060L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cpf")
    private long cpf;

    @Column(name = "adress")
    private String adress;

    @Transient
    public ClientDto clientDto(){
        return new ClientDto(name,cpf,adress);
    }

}