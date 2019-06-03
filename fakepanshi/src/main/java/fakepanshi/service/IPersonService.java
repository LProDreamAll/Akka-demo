package fakepanshi.service;



import fakepanshi.entity.Person;

import java.util.List;

/**
 * Copyright (C), 2019-2019
 * FileName: IPersonService
 * Author:   s·D·bs
 * Date:     2019/6/3 10:14
 * Description:
 * Motto: 0.45%
 */
public interface IPersonService {
    List<Person> listAll();
    void insertOne(Person p);
}
