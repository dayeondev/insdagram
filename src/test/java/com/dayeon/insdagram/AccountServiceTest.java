package com.dayeon.insdagram;

import com.dayeon.insdagram.domain.Account;
import com.dayeon.insdagram.dto.AccountDto;
import com.dayeon.insdagram.repository.MemoryAccountRepository;
import com.dayeon.insdagram.service.AccountService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class AccountServiceTest {

    AccountService accountService;
    MemoryAccountRepository accountRepository;

    @BeforeEach
    public void beforeEach(){
        accountRepository = new MemoryAccountRepository();
        accountService = new AccountService(accountRepository);
    }

    @AfterEach
    public void afterEach(){
        accountRepository.clearStore();
    }


    @Test
    public void signUpTest() throws Exception{
        //Given
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("dayeon");
        accountDto.setPassword("dadada");

        //When
        Long saveId = accountService.signUp(accountDto);

        //Then
        Account findAccount = accountRepository.findById(saveId).get();
        assertEquals(accountDto.getUsername(), findAccount.getUsername());
//        System.out.println(accountDto.getPassword() + "\n" + findAccount.getPassword() + "\n");

    }

    @Test
    public void accountDuplicateTest() throws Exception{
        //Given
        AccountDto accountDto1 = new AccountDto();
        accountDto1.setUsername("dayeon");
        accountDto1.setPassword("dadada");
        AccountDto accountDto2 = new AccountDto();
        accountDto2.setUsername("dayeon");
        accountDto2.setPassword("dadada");

        //When
        accountService.signUp(accountDto1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> accountService.signUp(accountDto2));
        assertThat(e.getMessage()).isEqualTo("Username already exists.");

    }

    @Test
    public void loadUserByUsernameTest() throws Exception{
        //Given
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("dayeon");
        accountDto.setPassword("dadada");
        Long accountId = accountService.signUp(accountDto);

        UserDetails result = accountService.loadUserByUsername("dayeon");
        assertEquals(accountRepository.findById(accountId).get().getPassword()
                ,result.getPassword());

    }
}
