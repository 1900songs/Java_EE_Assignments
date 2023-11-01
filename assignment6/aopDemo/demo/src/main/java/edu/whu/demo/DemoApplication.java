package edu.whu.demo;

import edu.whu.demo.aspect.ApiAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.Collections;
import java.util.List;

@SpringBootApplication

public class DemoApplication {
	public static void main(String[] args) {
		ApiAspect apiAspect = new ApiAspect();
		SpringApplication.run(DemoApplication.class, args);
		apiAspect.showResult();
	}
}
