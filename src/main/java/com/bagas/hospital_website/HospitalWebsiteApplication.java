package com.bagas.hospital_website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication()
public class HospitalWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalWebsiteApplication.class, args);
	}

}
