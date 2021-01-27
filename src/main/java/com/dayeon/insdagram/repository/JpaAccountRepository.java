package com.dayeon.insdagram.repository;

import com.dayeon.insdagram.domain.Account;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaAccountRepository implements AccountRepository {

    private final EntityManager em;

    public JpaAccountRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Account save(Account account) {
        em.persist(account);
        return account;
    }


    public Optional<Account> findById(Long id) {
        Account Account = em.find(Account.class, id);
        return Optional.ofNullable(Account);
    }

    public List<Account> findAll() {
        return em.createQuery("select m from Account m", Account.class)
                .getResultList();
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        List<Account> result = em.createQuery("select m from Account m where m.username = :username", Account.class)
                .setParameter("username", username)
                .getResultList();
        return result.stream().findAny();
    }
}
