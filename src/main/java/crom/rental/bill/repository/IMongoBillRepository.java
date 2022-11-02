package crom.rental.bill.repository;

import crom.rental.bill.entity.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMongoBillRepository extends MongoRepository<Bill, String> {
}
