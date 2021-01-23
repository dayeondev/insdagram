package com.dayeon.insdagram.dto;


import java.time.LocalDateTime;

import com.dayeon.insdagram.domain.Account;
import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
public class AccountDto {
    Long id;

    String username;
    String password;
    LocalDateTime createDate;
    LocalDateTime modifiedDate;

    public Account toEntity(){
        return Account.builder()
                .id(id)
                .username(username)
                .password(password)
                .build();
    }

    @Builder AccountDto(Long id,String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;

    }

}
