package crom.rental.repository.renter;

import crom.rental.entity.bill.Bill;
import crom.rental.entity.renter.MasterRenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRenterRepository extends JpaRepository<MasterRenter, String>, PagingAndSortingRepository<MasterRenter, String>,
    JpaSpecificationExecutor<MasterRenter> {

}
