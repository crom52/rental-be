package crom.rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableJpaAuditing
public class RentalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentalApplication.class, args);
	}
}
