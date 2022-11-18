package com.test.dgcoding.repository;

import com.test.dgcoding.model.Account;
import com.test.dgcoding.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query("SELECT c FROM Client c WHERE c.cpf = :cpf")
    Optional<Client> findClientByCpf(@Param("cpf") String cpf);

 }
