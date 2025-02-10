package com.evangelizacao_back.assistance;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssistanceApplication {
	public static void main(String[] args) {
		// Carregar o arquivo .env
		Dotenv dotenv = Dotenv.load();

		// Definir variáveis de ambiente
		System.setProperty("DB_URL", dotenv.get("DB_URL", ""));
		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME", ""));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD", ""));

		SpringApplication.run(AssistanceApplication.class, args);
	}
}