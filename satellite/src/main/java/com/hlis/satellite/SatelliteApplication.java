package com.hlis.satellite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class SatelliteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SatelliteApplication.class, args);
	}

}
