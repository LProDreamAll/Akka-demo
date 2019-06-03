package fakepanshi.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Copyright (C), 2019-2019
 * FileName: Customer
 * Author:   s·D·bs
 * Date:     2019/5/31 15:56
 * Description:
 * Motto: 0.45%
 */
@Data
public class Customer {

    @Id
    private String cId;
    private String firstName;
    private String secondName;
}
