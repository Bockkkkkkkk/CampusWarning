package com.campus.service.impl;


import com.campus.mapper.AdminMapper;
import com.campus.pojo.Admin;
import com.campus.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName:adminServiceImpl
 * Package:com.educationalsystembackend.service.impl
 * Description:
 *
 * @Author: Bock
 * @Create:2023/12/1922:42
 * @Version 1.0
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;


    @Override
    public Boolean login(String account, String password) {
        return adminMapper.login(account,password);
    }


    @Override
    public Boolean register(Admin admin) {
        //检查添加的用户数据是否有误
        if (admin.getAccount().isEmpty()||admin.getPassword().isEmpty()) {
            return false;
        }
        return adminMapper.insertAdmin(admin);
    }

}
