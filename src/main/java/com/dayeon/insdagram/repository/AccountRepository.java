
package com.dayeon.insdagram.repository;

import com.dayeon.insdagram.domain.Account;
import com.dayeon.insdagram.dto.AccountDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>{

    Account findByUsername(String username);

}
