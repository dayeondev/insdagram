package com.dayeon.insdagram;

import com.dayeon.insdagram.domain.Account;
import com.dayeon.insdagram.dto.AccountDto;
import com.dayeon.insdagram.repository.MemoryAccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
public class MemoryAccountRepositoryTest {

    MemoryAccountRepository repository = new MemoryAccountRepository();

    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        //Given
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("dayeon");
        accountDto.setPassword("dadada");

        //When
        Account account = repository.save(accountDto.toEntity());

        //then
        Account result = repository.findById(account.getId()).get();
        assertThat(account).isEqualTo(result);

    }

    @Test
    public void findByUsername(){
        //Given
        AccountDto accountDto1 = new AccountDto();
        accountDto1.setUsername("dayeon1");
        accountDto1.setPassword("dadada");
        Account account1 = repository.save(accountDto1.toEntity());
        AccountDto accountDto2 = new AccountDto();
        accountDto2.setUsername("dayeon2");
        accountDto2.setPassword("dadada");
        Account account2 = repository.save(accountDto2.toEntity());

        //When
        Account result = repository.findByUsername("dayeon2").get();

        //Then
        assertThat(result).isEqualTo(account2);

    }

    @Test
    public void findAll(){
        //Given
        AccountDto accountDto1 = new AccountDto();
        accountDto1.setUsername("dayeon1");
        accountDto1.setPassword("dadada");
        Account account1 = repository.save(accountDto1.toEntity());
        AccountDto accountDto2 = new AccountDto();
        accountDto2.setUsername("dayeon2");
        accountDto2.setPassword("dadada");
        Account account2 = repository.save(accountDto2.toEntity());


        //When
        List<Account> result = repository.findAll();

        //Then
        assertThat(result.size()).isEqualTo(2);
    }

}
