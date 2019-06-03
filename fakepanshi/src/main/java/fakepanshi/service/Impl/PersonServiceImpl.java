package fakepanshi.service.Impl;


import fakepanshi.entity.Person;
import fakepanshi.mapper.IPersonMapper;
import fakepanshi.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright (C), 2019-2019
 * FileName: PersonServiceImpl
 * Author:   s·D·bs
 * Date:     2019/6/3 10:14
 * Description:
 * Motto: 0.45%
 */

@Service
public class PersonServiceImpl implements IPersonService {

    @Autowired
    IPersonMapper iPersonMapper;


    @Override
    public List<Person> listAll() {
        return iPersonMapper.getAll();
    }

    // 使用@Transactional开启事务，出错抛出RuntimeException
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void insertOne(Person p) {
        iPersonMapper.insert(p);
    }
}
