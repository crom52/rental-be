package crom.rental.renter.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import crom.rental.renter.entity.MasterRenter;
import crom.rental.renter.repository.IRenterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

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

	public MasterRenter findById(String id) {
		return renterRepository.findById(id).orElse(null);
	}

	public List<MasterRenter> findByKeyword(String keyWord) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MasterRenter> query = builder.createQuery(MasterRenter.class);

		Root<MasterRenter> root = query.from(MasterRenter.class);
		if(isNotBlank(keyWord)) {
			Predicate likeRoomNumber = builder.like(root.get("roomNumber"), "%" + keyWord + "%");
			Predicate likeName = builder.like(root.get("name"), "%" + keyWord + "%");
			Predicate likeUserName = builder.like(root.get("fullName"), "%" + keyWord + "%");
			Predicate conditions = builder.or(likeName, likeUserName, likeRoomNumber);
			query.where(conditions);
		}
		return entityManager.createQuery(query).getResultList();
	}

}
