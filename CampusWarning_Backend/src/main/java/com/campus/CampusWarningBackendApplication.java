package com.campus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.campus.mapper")
public class CampusWarningBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusWarningBackendApplication.class, args);
    }

}
