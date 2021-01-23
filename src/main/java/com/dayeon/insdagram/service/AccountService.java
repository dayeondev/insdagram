package com.dayeon.insdagram.service;

import com.dayeon.insdagram.domain.Account;
import com.dayeon.insdagram.domain.Role;
import com.dayeon.insdagram.dto.AccountDto;
import com.dayeon.insdagram.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@AllArgsConstructor
public class AccountService implements UserDetailsService {
    private final AccountRepository accountRepository;

//    joinUser()

    public Long signUp(AccountDto accountDto) {
        validateDuplicateMember(accountDto);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        accountDto.setPassword(passwordEncoder.encode(accountDto.getPassword()));


        return accountRepository.save(accountDto.toEntity()).getId();
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountWrapper = accountRepository.findByUsername(username);
        Account account = accountWrapper.get();

        List<GrantedAuthority> authorities = new ArrayList<>();
        if (("admin").equals(username)) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.getValue()));
        } else {
            authorities.add(new SimpleGrantedAuthority(Role.MEMBER.getValue()));
        }
        return new User(account.getUsername(), account.getPassword(), authorities);
    }

    private void validateDuplicateMember(AccountDto accountDto){
        accountRepository.findByUsername(accountDto.getUsername()).ifPresent(m -> {
            throw new IllegalStateException("Username already exists.");
        });
    }
}
