package com.bill.coder;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by huangbin on 2016/12/15.
 * 模板属性
 */
@Data
@Entity
@Table(name="Template_Attributes")
public class TemplateAttributes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String templateUrl; //模板路径
    private String generateUrl; //生成路径
    private String logicName; //文件逻辑分类
    private String suffix; //后缀名称
}
