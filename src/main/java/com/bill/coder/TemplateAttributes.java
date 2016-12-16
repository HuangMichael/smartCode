package com.bill.coder;

import lombok.Data;

/**
 * Created by huangbin on 2016/12/15.
 * 模板属性
 */
@Data
public class TemplateAttributes {

    private String templateUrl; //模板路径
    private String generateUrl; //生成路径
    private String logicName; //文件逻辑分类
    private String suffix; //后缀名称
    private String packageName;//包名称
    private String tableName; //表名称
}
