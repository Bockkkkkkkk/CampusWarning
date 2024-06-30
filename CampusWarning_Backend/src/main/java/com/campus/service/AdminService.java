package com.campus.service;

import com.campus.pojo.Admin;

/**
 * ClassName:adminService
 * Package:com.educationalsystembackend.service
 * Description:
 *
 * @Author: Bock
 * @Create:2023/12/1922:42
 * @Version 1.0
 */
public interface AdminService {


    Boolean login(String account, String password);

    Boolean register(Admin admin);
}
