package crom.rental;

import crom.rental.entity.Bill;
import crom.rental.repository.IMongoBillRepository;
import crom.rental.service.RentalBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = "crom.rental.*")
public class RentalApplication {
	public static void main(String[] args) {
		SpringApplication.run(RentalApplication.class, args);
	}
}
