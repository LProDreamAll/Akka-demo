package fakepanshi.mapper;


import fakepanshi.entity.Person;
import fakepanshi.entity.TableHikari;
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
public interface ITableHikariMapper {

    @Select("SELECT id, name ,content   FROM table_hikari WHERE id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "content", column = "content")
    })
    TableHikari findById(@Param(value = "id") String id);

    @Select("SELECT * FROM table_hikari")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "content", column = "content")
    })
    List<TableHikari> getAll();

    @Insert("INSERT INTO table_hikari(name,content) VALUES(#{name}, #{content})")
    int insert(TableHikari tableHikari);

//    @Update("UPDATE personinfo SET username=#{username} WHERE id =#{id}")
//    void update(Person user);
//
//    @Delete("DELETE FROM personinfo WHERE id =#{id}")
//    void delete(Integer id);
}
