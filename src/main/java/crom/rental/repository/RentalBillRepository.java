package crom.rental.repository;

import crom.rental.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RentalBillRepository extends JpaRepository<Bill, Long> {

}
