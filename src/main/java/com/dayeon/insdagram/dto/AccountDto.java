package com.dayeon.insdagram.dto;


import java.time.LocalDateTime;

import com.dayeon.insdagram.domain.Account;
import lombok.*;

@Getter @Setter
@ToString
@NoArgsConstructor
public class AccountDto {
    private Long id;
    private String username;
    private String password;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

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

