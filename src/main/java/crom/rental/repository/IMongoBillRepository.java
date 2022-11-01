package crom.rental.repository;

import crom.rental.entity.bill.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMongoBillRepository extends MongoRepository<Bill, String> {
}
