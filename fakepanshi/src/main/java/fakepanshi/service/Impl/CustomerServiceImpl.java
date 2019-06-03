package fakepanshi.service.Impl;


import fakepanshi.dao.CustomerRepository;
import fakepanshi.entity.Customer;
import fakepanshi.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2019-2019
 * FileName: CustomerServiceImpl
 * Author:   s·D·bs
 * Date:     2019/5/31 16:33
 * Description: CustomerServiceImpl
 * Motto: 0.45%
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Customer saveCustomer(String firstName, String secondName) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setSecondName(secondName);
        return customerRepository.save(customer);
    }
}
