package com.dayeon.insdagram.domain;

import lombok.Data;

import javax.persistence.*;
import java.security.Timestamp;

@Table(name="image")
@Entity
@Data
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @Column(length=1024000)
    private byte[] file;
    private String location;
    private String caption;

    @ManyToOne
    @JoinColumn(name="account_id")
    private Account account;





}
