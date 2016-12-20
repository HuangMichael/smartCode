package com.bill.domain.location;

import javax.persistence.*;

import lombok.Data;

import java.lang.*;

/**
 * location
 * Created by huangbin on 2016.12.16.
 */
@Data
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private java.lang.String locName;
    private java.lang.String location;
    private java.lang.String status;

}