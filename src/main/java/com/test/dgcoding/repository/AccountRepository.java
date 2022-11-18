package com.test.dgcoding.repository;

import com.test.dgcoding.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query("UPDATE Client SET balance = :balance WHERE id = :id")
    Optional<Account> updateBalance(@Param("balance, id") Double balance, Integer id);
}
