
package com.dayeon.insdagram.repository;

import com.dayeon.insdagram.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AccountRepository extends JpaRepository<Account, Long>{

    Account findByUsername(String username);

}
