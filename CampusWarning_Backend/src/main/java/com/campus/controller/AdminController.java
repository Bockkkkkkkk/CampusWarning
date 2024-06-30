package com.campus.controller;


import com.campus.common.BaseResponse;
import com.campus.common.ErrorCode;
import com.campus.common.ResultUtils;
import com.campus.pojo.Admin;
import com.campus.service.impl.AdminServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:aminController
 * Package:com.educationalsystembackend.controller.admin
 * Description:
 *
 * @Author: Bock
 * @Create:2023/12/1922:33
 * @Version 1.0
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private AdminServiceImpl adminService;

    @GetMapping("/login")
    public BaseResponse<String> login(String account, String password){
        log.info("管理员登录：{}",account);
        Boolean res=adminService.login(account,password);
        return res!=null && res? ResultUtils.success("Success") : ResultUtils.error(ErrorCode.FAIL);
    }

    @PostMapping("/register")
    public BaseResponse<String> register(@RequestBody Admin admin){
        log.info("管理员注册：{}",admin);
        Boolean res=adminService.register(admin);
        return res!=null && res? ResultUtils.success("Success") : ResultUtils.error(ErrorCode.FAIL);
    }

}
