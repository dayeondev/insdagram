package com.dayeon.insdagram.service;

import com.dayeon.insdagram.domain.Account;
import com.dayeon.insdagram.domain.Role;
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
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Optional<Account> findByUsername(String username) throws UsernameNotFoundException {
        return accountRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        CustomUserDetail userDetail = null;
        if(optionalAccount.get() != null){
            userDetail = new CustomUserDetail();
            userDetail.setAccount(optionalAccount.get());
        }
        else{
            throw new UsernameNotFoundException("Not Found 'username'");
        }
        return userDetail;

    }

    public void updateUserInfo(Long id, Account account) {
        Account oldAccount = accountRepository.getOne(id);
        oldAccount.setUsername(account.getUsername());
        oldAccount.setName(account.getName());
        oldAccount.setWebsite(account.getWebsite());
        oldAccount.setBio(account.getBio());
        oldAccount.setEmail(account.getEmail());
        oldAccount.setPhoneNumber(account.getPhoneNumber());
        oldAccount.setProfileImage(account.getProfileImage());
        accountRepository.save(oldAccount);
    }

    public void signUpAccount(Account requestAccount) {

        try{
            if(accountRepository.findByUsername(requestAccount.getUsername()).get() != null){

                System.out.println("중복 계정임을 알려주는 기능을 넣어야 합니다.");
            }
        }
        catch (NoSuchElementException e){
            requestAccount.setPassword(passwordEncoder.encode(requestAccount.getPassword()));
            accountRepository.save(requestAccount);
        }

    }
}
