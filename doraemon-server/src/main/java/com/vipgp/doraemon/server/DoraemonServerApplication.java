package com.vipgp.doraemon.server;

import com.vipgp.doraemon.core.CoreBasePackage;
import com.vipgp.doraemon.core.id.generator.dao.IdGeneratorDaoBasePackage;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@DubboComponentScan
@SpringBootApplication
@ComponentScan(basePackageClasses = {CoreBasePackage.class,ServerBasePackage.class})
@MapperScan(basePackageClasses = {IdGeneratorDaoBasePackage.class})
public class DoraemonServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoraemonServerApplication.class, args);
	}

}
