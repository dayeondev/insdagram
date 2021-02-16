package com.dayeon.insdagram;

import com.dayeon.insdagram.repository.AccountRepository;
import com.dayeon.insdagram.service.CustomUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SpringConfig {

//    private final AccountRepository accountRepository;

////    JpaAccountRepository 사용 시
//    private final DataSource dataSource;
//    private final EntityManager em;
//
//    public SpringConfig(DataSource dataSource, EntityManager em) {
//        this.dataSource = dataSource;
//        this.em = em;
//    }
//
//    @Bean
//    public CustomUserDetailService accountService(){
//        return new CustomUserDetailService(accountRepository);
//    }


//    @Bean
//    public AccountRepository accountRepository() {
////        return new MemoryAccountRepository();
////        return new JpaAccountRepository(em);
//    }


}
