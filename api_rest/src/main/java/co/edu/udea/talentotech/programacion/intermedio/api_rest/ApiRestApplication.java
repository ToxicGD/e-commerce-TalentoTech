package co.edu.udea.talentotech.programacion.intermedio.api_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
		System.out.println(System.getenv("DB_HOST"));
		System.out.println(System.getenv("DB_PORT"));
		System.out.println(System.getenv("DB_NAME"));
		System.out.println(System.getenv("DB_USER"));
		System.out.println(System.getenv("DB_PASSWORD"));
	}

}
