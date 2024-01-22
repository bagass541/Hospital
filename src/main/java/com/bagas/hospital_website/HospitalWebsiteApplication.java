package com.bagas.hospital_website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HospitalWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalWebsiteApplication.class, args);
	}

}
