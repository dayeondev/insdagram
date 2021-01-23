package com.dayeon.insdagram.repository;

import com.dayeon.insdagram.domain.Account;
import org.springframework.stereotype.Repository;

import java.util.*;


public class MemoryAccountRepository implements AccountRepository {
    private static Map<Long, Account> store = new HashMap<>();
    private static long sequence = 0L;


    @Override
    public Account save(Account account) {
        account.setId(++sequence);
        store.put(account.getId(), account);
        return account;
    }

    @Override
    public Optional<Account> findByUsername(String username) {
//        return Optional.ofNullable(store.get(username));
        return store.values().stream()
                .filter(account -> account.getUsername().equals(username))
                .findAny();
    }

    public Optional<Account> findById(Long id){
        return Optional.ofNullable(store.get(id));
    }

    public List<Account> findAll(){
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
