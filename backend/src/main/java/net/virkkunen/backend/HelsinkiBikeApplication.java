package net.virkkunen.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class HelsinkiBikeApplication {

	public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();

    String dbUrl = dotenv.get("DB_URL");
    String dbUser = dotenv.get("DB_USER");
    String dbPsw = dotenv.get("DB_PSW");

    System.setProperty("spring.datasource.url", dbUrl);
    System.setProperty("spring.datasource.username", dbUser);
    System.setProperty("spring.datasource.password", dbPsw);

		SpringApplication.run(HelsinkiBikeApplication.class, args);
    
    System.out.println("Cha cha cha cha cha cha cha!");
	}

}
