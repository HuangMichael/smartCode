package com.bill.utils.page;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangbin on 2016/4/18.
 * 返回对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnObject {
    private Boolean result;  //返回结果
    private String resultDesc;//返回描述
    private List<Object> objectsList = new ArrayList<Object>();//返回对象列表
}
