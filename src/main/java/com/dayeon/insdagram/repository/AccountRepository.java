package com.dayeon.insdagram.repository;

import com.dayeon.insdagram.domain.Account;

import java.util.Optional;


public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findByUsername(String username);

}
