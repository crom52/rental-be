package crom.rental.service.renter;

import crom.rental.entity.renter.MasterRenter;
import crom.rental.repository.renter.IRenterRepository;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RenterService {
  private final EntityManager entityManager;
  private final IRenterRepository renterRepository;

  public List<MasterRenter> findByIds(List<String> renterIds) {
    try {
      return renterRepository.findAllById(renterIds);
    } catch (Exception e) {
      return List.of();
    }
  }

  public MasterRenter findRenter(String id) {
    try {
      return renterRepository.findById(id).orElse(null);
    } catch (Exception e) {
      return null;
    }
  }

  public MasterRenter findByRoom(String roomNumber) {
    CriteriaBuilder builder = entityManager.getCriteriaBuilder();
    CriteriaQuery<MasterRenter> query = builder.createQuery(MasterRenter.class);
    Root<MasterRenter> studentRoot = query.from(MasterRenter.class);
    builder.equal(studentRoot.get("roomNumber"), roomNumber);
    TypedQuery<MasterRenter> typedQuery = entityManager.createQuery(query);
    return typedQuery.getSingleResult();
  }

}
