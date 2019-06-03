package fakepanshi.controller;


import fakepanshi.service.CustomerService;
import fakepanshi.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright (C), 2019-2019
 * FileName: CustomerController
 * Author:   s·D·bs
 * Date:     2019/5/31 15:57
 * Description:
 * Motto: 0.45%
 */

@RestController
@RequestMapping("/test")
@Api("customer相关的API，用于测试mongodb")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @ApiOperation(value = "增加一个Customer", notes = "直接请求获取即可")
    @RequestMapping(value = "/addCustomer", method = RequestMethod.GET)
    @ResponseBody
    public Response addCustomer(@RequestParam("firstName") String firstName,
                                @RequestParam("secondName") String secondName) {
        try {
            log.info("addCustomer 请求开始 firstName:[{}] , secondName:[{}]", firstName, secondName);
            return Response.of().success(customerService.saveCustomer(firstName, secondName));
        } catch (Exception e) {
            log.error("addCustomer error :[{}] ", e);
            return Response.of().failure("请求失败,请稍后重试");
        }
    }

    //增删改查
}
