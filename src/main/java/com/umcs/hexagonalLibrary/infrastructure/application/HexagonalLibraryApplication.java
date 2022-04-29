package com.umcs.hexagonalLibrary.infrastructure.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.umcs.hexagonalLibrary.infrastructure"})
public class HexagonalLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexagonalLibraryApplication.class, args);
	}

}
