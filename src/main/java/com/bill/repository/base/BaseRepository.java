package com.bill.repository.base;

import com.bill.domain.person.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by huangbin on 16-12-10.
 * 基础的数据库访问接口
 */

@Repository
public interface BaseRepository extends CrudRepository, PagingAndSortingRepository {


    /**
     * @return 查询所有的ID集合
     */
    List<Long> findAllId();
}
