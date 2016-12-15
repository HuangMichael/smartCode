package com.bill.model.codeBulider;

import lombok.Data;

import java.util.Map;


/**
 * 表属性类
 */
@Data
public class TableInfo {
    private String tableName;
    private String className;
    private Map<String, ColumnInfo> primaryKeys;
    private Map<String, ColumnInfo> columnInfo;
}
