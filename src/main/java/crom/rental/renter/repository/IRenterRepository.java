package crom.rental.renter.repository;

import crom.rental.renter.entity.MasterRenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IRenterRepository extends JpaRepository<MasterRenter, String>, PagingAndSortingRepository<MasterRenter, String>,
    JpaSpecificationExecutor<MasterRenter> {

}
