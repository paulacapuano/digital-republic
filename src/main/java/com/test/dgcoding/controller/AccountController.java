package com.test.dgcoding.controller;

import com.test.dgcoding.model.Account;
import com.test.dgcoding.model.Client;
import com.test.dgcoding.repository.AccountRepository;
import com.test.dgcoding.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("accounts")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ClientRepository clientRepository;

    @GetMapping
    @Transactional
    public ResponseEntity<List<Account>> list() {
        List<Account> accounts = accountRepository.findAll();
        return ResponseEntity.ok().body(accounts);
    }

    @PostMapping
    @Transactional
    public  ResponseEntity<Account> create(@RequestBody @Valid Integer accountNumber, Client client) {
        Optional<Client> clientOptional =clientRepository.findById(client.getId());

        if (clientOptional.isPresent()){
            Account account = new Account(accountNumber, client);
            accountRepository.save(account);
            return ResponseEntity.status(201).body(account);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Account> deposit(@RequestBody @Valid Double amount, Integer accountId) {
        Optional<Account> account = accountRepository.findById(accountId);

        if (account.isPresent()) {
            if (amount > 0.0 && amount <= 2000.0) {
                Double currentBalance = account.get().getBalance();
                Double newBalance = currentBalance + amount;
                // account.get().setBalance(newBalance);
                accountRepository.updateBalance(newBalance, accountId);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Account> transfer(@RequestBody @Valid Integer accountIdOrigin,
                                            Integer accountIdDestiny, Double amount) {
        Optional<Account> originAccount = accountRepository.findById(accountIdOrigin);
        Optional<Account> destinyAccount = accountRepository.findById(accountIdDestiny);

        if (originAccount.isPresent() && destinyAccount.isPresent()){
            if (amount > 0.0 && amount <= originAccount.get().getBalance()) {
                Double currentOriginBalance = originAccount.get().getBalance();
                Double currentDestinyBalance = destinyAccount.get().getBalance();
                Double newOriginBalance = currentOriginBalance - amount;
                Double newDestinyBalance = currentDestinyBalance + amount;
                accountRepository.updateBalance(newOriginBalance, accountIdOrigin);
                accountRepository.updateBalance(newDestinyBalance, accountIdDestiny);
                return ResponseEntity.ok().build();
            }
        }
        return ResponseEntity.notFound().build();
    }
}
