package crom.rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "crom.rental.*")
public class RentalApplication {
	public static void main(String[] args) {
		SpringApplication.run(RentalApplication.class, args);
	}
}
