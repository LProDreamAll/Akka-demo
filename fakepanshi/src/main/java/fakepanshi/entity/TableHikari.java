package fakepanshi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Copyright (C), 2019-2019
 * FileName: TableHikari
 * Author:   s·D·bs
 * Date:     2019/7/10 17:23
 * Description:
 * Motto: 0.45%
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TableHikari implements Serializable {

    private int id;
    private String name;
    private String content;
}
