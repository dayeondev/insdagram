package com.dayeon.insdagram.repository;

import com.dayeon.insdagram.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaAccountRepository extends JpaRepository<Account, Long>, AccountRepository {

    @Override
    public Optional<Account> findByUsername(String username);
}
