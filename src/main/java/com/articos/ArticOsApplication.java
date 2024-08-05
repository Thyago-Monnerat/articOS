package com.articos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = { @Server(url="/", description = "Default server URL")})
@SpringBootApplication
public class ArticOsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticOsApplication.class, args);
	}

}
