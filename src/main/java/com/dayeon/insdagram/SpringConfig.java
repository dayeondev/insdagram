package com.dayeon.insdagram;

import com.dayeon.insdagram.repository.AccountRepository;
import com.dayeon.insdagram.repository.MemoryAccountRepository;
import com.dayeon.insdagram.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public AccountService accountService(){
        return new AccountService(accountRepository());
    }

    @Bean
    public AccountRepository accountRepository() {
        return new MemoryAccountRepository();
    }

}
