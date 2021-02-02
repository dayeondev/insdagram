package com.dayeon.insdagram;

import com.dayeon.insdagram.repository.AccountRepository;
import com.dayeon.insdagram.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SpringConfig {

    private final AccountRepository accountRepository;

////    JpaAccountRepository 사용 시
//    private final DataSource dataSource;
//    private final EntityManager em;
//
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }

    @Bean
    public AccountService accountService(){
        return new AccountService(accountRepository);
    }


//    @Bean
//    public AccountRepository accountRepository() {
////        return new MemoryAccountRepository();
////        return new JpaAccountRepository(em);
//    }


}
