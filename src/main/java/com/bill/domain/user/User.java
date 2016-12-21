package com.bill.domain.user;

import com.bill.domain.person.Person;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by huangbin on 16-12-10.
 */
@Entity
@Table(name = "user")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "user_name", nullable = false, length = 20, unique = true, updatable = false)
    private String userName;
    @Column(name = "nick_name", nullable = false, length = 20)
    private String nickName;
    @JsonIgnore
    @Column(name = "password", nullable = false, length = 100)
    private String password;
    @Column(name = "status", nullable = false)
    private String status;
/*
    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;*/
}
