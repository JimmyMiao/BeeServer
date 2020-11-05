package com.app.server.controller;

import com.app.server.mapper.BusinessMapper;
import com.app.server.model.response.BaseResponse;
import com.app.server.model.response.ConstResponse;
import com.app.server.model.response.DataResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusinessController {
    @Autowired
    private BusinessMapper businessMapper;

    @RequestMapping("/home/businesslist")
    public BaseResponse business(){
        System.out.println("aaaaaa");
        return new DataResponse(businessMapper.selectAll(), ConstResponse.DESC_OK, ConstResponse.STATUS_OK);
    }
}
