package com.bill.domain.person;

import javax.persistence.*;
import lombok.Data;
import java.lang.*;

/**
* person
* Created by huangbin on 2016.12.15.
*/
@Data
@Entity
@Table(name="t_person")
public class Person{
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

    private java.lang.String personDesc;
    private java.lang.String personName;

}