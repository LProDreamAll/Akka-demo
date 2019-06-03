package fakepanshi.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;

/**
 * Copyright (C), 2019-2019
 * FileName: Post
 * Author:   s·D·bs
 * Date:     2019/5/31 15:42
 * Description: 测试使用实体类
 * Motto: 0.45%
 */
@Data
public class Post {

    @Id
    private Long id;
    private String title;
    private String content;
    private Date createTime;
}
