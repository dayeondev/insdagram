package com.dayeon.insdagram.domain;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Table(name="member")
public class Account  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue
    private Long id;
    private String username;
    private String password;



    @Builder
    public Account(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
