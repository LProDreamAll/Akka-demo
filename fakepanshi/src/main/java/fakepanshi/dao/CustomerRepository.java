package fakepanshi.dao;

import fakepanshi.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Copyright (C), 2019-2019
 * FileName: CustomerRepository
 * Author:   s·D·bs
 * Date:     2019/5/31 15:57
 * Description: CustomerRepository
 * Motto: 0.45%
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Customer findByFirstName(String name);

    List<Customer> findBySecondName(String name);
}
