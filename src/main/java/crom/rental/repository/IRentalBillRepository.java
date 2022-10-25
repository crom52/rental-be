package crom.rental.repository;

import crom.rental.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface IRentalBillRepository extends JpaRepository<Bill, String>, PagingAndSortingRepository<Bill, String>,
    JpaSpecificationExecutor<Bill> {

}
