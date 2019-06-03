package fakepanshi.mapper;


import fakepanshi.entity.Person;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Copyright (C), 2019-2019
 * FileName: IPersonMapper
 * Author:   s·D·bs
 * Date:     2019/6/3 10:13
 * Description:
 * Motto: 0.45%
 */

@Component
public interface IPersonMapper {

    @Select("SELECT id, username ,create_time as createTime  FROM personinfo WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username")
    })
    Person findById(@Param(value = "id") String id);

    @Select("SELECT * FROM personinfo")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "createTime", column = "create_time")
    })
    List<Person> getAll();

    @Insert("INSERT INTO personinfo(id,username) VALUES(#{id}, #{username})")
    void insert(Person user);

    @Update("UPDATE personinfo SET username=#{username} WHERE id =#{id}")
    void update(Person user);

    @Delete("DELETE FROM personinfo WHERE id =#{id}")
    void delete(Integer id);
}
