package com.bill.service.person;

import com.bill.repository.person.PersonRepository;
import com.bill.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huangbin on 16-12-10.
 * 应用业务类
 */
@Service
public class PersonService extends BaseService {
    @Autowired
    PersonRepository personRepository;


}
