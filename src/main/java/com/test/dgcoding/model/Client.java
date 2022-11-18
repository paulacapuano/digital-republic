package com.test.dgcoding.model;

import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

@Entity
public class Client {
    @Id
    @GeneratedValue
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    @CPF
    private String cpf;
    @OneToOne
    private Account accountId;

    public Client() {
    }

    public Client(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
