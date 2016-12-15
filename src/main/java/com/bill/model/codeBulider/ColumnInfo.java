package com.bill.model.codeBulider;

import lombok.Data;

/**
 * 字段属性类
 */
@Data
public class ColumnInfo {
    private String columnName;
    private Integer columnType;
    private String columnDesc;
    private String fieldName;
    private String fieldType;
    private String pascalCaseFieldName;

}
