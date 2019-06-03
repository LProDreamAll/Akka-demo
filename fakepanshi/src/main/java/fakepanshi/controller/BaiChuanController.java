package fakepanshi.controller;

import fakepanshi.service.BaiChuanService;
import fakepanshi.service.Impl.BaiChuanServiceImpl;
import fakepanshi.utils.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import java.util.Map;

import static fakepanshi.utils.Utils.reqParamToGenericMap;

/**
 * Copyright (C), 2019-2019
 * FileName: BaiChuanController
 * Author:   s·D·bs
 * Date:     2019/5/31 17:57
 * Description: 百川测试环境搭建
 * Motto: 0.45%
 */
@RestController
@Api("fake of bai chuan")
@Slf4j
public class BaiChuanController {

    @Autowired
    private BaiChuanService baiChuanService;

    /**
     * 写不了 难受
     */

    @ApiOperation(value = "获取太平洋账号信息", notes = "根据不同参数获取不同信息")
    @RequestMapping(value = "/baichuan/quote/data/platform", method = RequestMethod.GET)
    @ResponseBody
    public String quoteDataPlatform(HttpServletRequest request,
                                    @RequestParam("taskType") String taskType,
                                    @RequestParam("enquiryId") String enquiryId,
                                    @RequestParam("processType") String processType,
                                    @RequestParam("monitorid") String monitorid,
                                    @RequestParam("taskId") String taskId) {
        try {
            return baiChuanService.getOne();
        } catch (Exception e) {
            log.error("addCustomer error :[{}] ", e);
            return "请求失败,请稍后重试";
        }
    }


}
