package fakepanshi.controller;

import com.alibaba.fastjson.JSON;

import fakepanshi.entity.Person;
import fakepanshi.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Copyright (C), 2019-2019
 * FileName: PersonController
 * Author:   s·D·bs
 * Date:     2019/6/3 10:15
 * Description:
 * Motto: 0.45%
 */

@RestController
public class PersonController {

    @Autowired
    @Qualifier("personServiceImpl")
    IPersonService iPersonService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public List<Person> getAll(){
        List<Person> list=iPersonService.listAll();
        System.out.println("选出所有的人："+ JSON.toJSONString(list));
        return list;
    }

    @RequestMapping(value = "/posttest",method = RequestMethod.POST)
    public List<Person> insertOne(Person p){
        System.out.println(p.getId()+" And "+p.getUsername());
        iPersonService.insertOne(p);
        return getAll();
    }


}
