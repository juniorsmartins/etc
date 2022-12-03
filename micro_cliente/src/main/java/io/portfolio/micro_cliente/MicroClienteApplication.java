package io.portfolio.micro_cliente;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroClienteApplication {

	@Value("${application.name}")
	private String applicationName;

	public static void main(String[] args) {
		SpringApplication.run(MicroClienteApplication.class, args);
	}

}
