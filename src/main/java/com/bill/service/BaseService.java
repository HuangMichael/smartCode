package com.bill.service;


import lombok.Data;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by huangbin  on 2016/5/20.
 * 日志对象
 */
@Service
@Data
public class BaseService {


    protected Log log = LogFactory.getLog(this.getClass());

    protected List dataList;


    /**
     * @param searchPhrase
     * @param paramSize
     * @return 组装查询参数数组
     */
    public String[] assembleSearchArray(String searchPhrase, int paramSize) {


        String array[] = new String[paramSize];
        if (!searchPhrase.isEmpty()) {
            array = searchPhrase.split(",", paramSize + 1);
        } else {
            for (int i = 0; i < paramSize; i++) {
                array[i] = "";
            }
        }

        return array;
    }


    /**
     * @param searchPhrase
     * @param paramSize
     * @param separatable
     * @return 组装查询参数数组
     */
    public String[] assembleSearchArray(String searchPhrase, int paramSize, Boolean separatable, String location) {
        String array[] = new String[paramSize + 1];
        if (!searchPhrase.isEmpty()) {
            array = searchPhrase.split(",", paramSize + 1);
        } else {
            for (int i = 0; i < paramSize; i++) {
                array[i] = "";
            }
        }
        if (separatable) {
            array[paramSize] = location;
        }
        return array;
    }


    /**
     * @param sortArray
     * @return 组装排序属性对象
     */
    public Sort assembleSort(String[] sortArray) {
        List<Sort.Order> orderList = new ArrayList<Sort.Order>();
        for (String str : sortArray) {
            orderList.add(new Sort.Order(str));
        }
        Sort sort = new Sort(orderList);
        return sort;

    }

    /**
     * @param list 批量保存
     */
    public void save(List list) {


    }





}
