package com.dayeon.insdagram.domain;

import lombok.*;

import javax.persistence.*;


@Table(name="account")
@Entity
@Data
@NoArgsConstructor
public class Account  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;


    private String profileImage;

    private String name;
    private String website;
    private String bio;
    private String email;
    private String phoneNumber;




    @Builder
    public Account(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

}
