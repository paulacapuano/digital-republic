package com.test.dgcoding.controller;

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
@RequestMapping("clients")
public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping
    @Transactional
    public ResponseEntity<List<Client>> list() {
        List<Client> clients = clientRepository.findAll();
        return ResponseEntity.ok().body(clients);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Client> create(@RequestBody @Valid String name, String cpf) {
        Optional<Client> existentClient = clientRepository.findClientByCpf(cpf);

        if (existentClient.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        Client client = new Client(name, cpf);
        clientRepository.save(client);
        return ResponseEntity.status(201).body(client);
    }
}
