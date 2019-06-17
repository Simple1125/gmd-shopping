package com.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.springcloud.dao") //设置mybatis接口所在的位置
public class SpringCloudGoodsProvider {

	public static void main(String[] args) {
		SpringApplication.run(SpringCloudGoodsProvider.class, args);
	}
}
