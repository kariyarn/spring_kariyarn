package com.example.boot06;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
/*
 * @PropertySource(value=" 커스텀 properties파일의 위치")
 * classpath: 는 resource 폴더를 가리킨다.
 */
@SpringBootApplication
@PropertySource(value = "classpath:custom.properties")
public class Boot06WebConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(Boot06WebConfigApplication.class, args);
		
	}

}
