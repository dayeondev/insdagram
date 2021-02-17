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
import java.util.Optional;


@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepository.findByUsername(username);
        CustomUserDetail userDetail = null;
        if(account != null){
            userDetail = new CustomUserDetail();
            userDetail.setAccount(account);
        }
        else{
            throw new UsernameNotFoundException("Not Found 'username'");
        }
        return userDetail;
    }
}
