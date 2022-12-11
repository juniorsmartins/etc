package io.portfolio.micro_cliente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MicroClienteApplication {

	private static Logger logger = LoggerFactory.getLogger(MicroClienteApplication.class);

	@Value("${application.name}")
	private String applicationName;

	public static void main(String[] args) {
		SpringApplication.run(MicroClienteApplication.class, args);
		logger.info("Início da aplicação");
	}

}
