package com.campus.mapper;

import com.campus.pojo.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {

    @Select("select * from admin where account=#{account} and password=#{password}")
    Boolean login(String account, String password);

    @Insert("insert into admin(account, password) VALUES (#{account},#{password})")
    Boolean insertAdmin(Admin admin);
}
