package fakepanshi.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * Copyright (C), 2019-2019
 * FileName: Person
 * Author:   s·D·bs
 * Date:     2019/6/3 10:13
 * Description:
 * Motto: 0.45%
 */
@Getter
@Setter
public class Person {

    private Integer id;
    private String username;
    private Timestamp createTime;
}
