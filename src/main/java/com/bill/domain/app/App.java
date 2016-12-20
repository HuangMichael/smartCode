package com.bill.domain.app;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by huangbin on 2016/12/15.
 * 应用管理
 */

@Entity
@Table(name = "APP")
@Data
public class App {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "app_name")
    private String appName;
    @Column(name = "app_desc")
    private String appDesc;
    @Column(name = "package_name")
    private String  packageName;
    @Column(name = "status")
    private String  status;

}
