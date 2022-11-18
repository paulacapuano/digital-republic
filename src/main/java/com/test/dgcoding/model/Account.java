package com.test.dgcoding.model;

import com.test.dgcoding.repository.AccountRepository;

import javax.persistence.*;

@Entity
public class Account {
    @Id
    @GeneratedValue
    @Column
    private Integer id;
    @Column (nullable = false)
    @GeneratedValue
    private Integer accountNumber;
    @Column
    private Double balance;
    @OneToOne
    @Column
    private Client client;

    public Account () {
    }

    public Account(Integer accountNumber, Client client) {
        this.accountNumber = accountNumber;
        this.balance = 0.0;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Account updateAccount(Integer id, AccountRepository accountRepository) {
        Account account = accountRepository.getOne(id);

        account.setAccountNumber(this.accountNumber);
        account.setBalance(this.balance);
        account.setClient(this.client);

        return account;
    }
}