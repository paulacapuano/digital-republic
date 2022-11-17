package com.test.dgcoding.repository;

import com.test.dgcoding.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {
}
