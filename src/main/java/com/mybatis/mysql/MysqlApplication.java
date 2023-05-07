package com.mybatis.mysql;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mybatis.mysql.repository")
public class MysqlApplication {
	public static void main(String[] args) {
		SpringApplication.run(MysqlApplication.class, args);
		System.out.println("Done Executed");
	}
}