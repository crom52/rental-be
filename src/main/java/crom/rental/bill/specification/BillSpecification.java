package crom.rental.bill.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import crom.rental.bill.entity.Bill;
import lombok.Data;

@Data
public class BillSpecification implements Specification<Bill> {
	private BillCriteria criteria;

	@Override
	public Predicate toPredicate(Root<Bill> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		if (criteria.getOperation().equalsIgnoreCase("=")) {
			return builder.equal(root.get(criteria.getKey()), criteria.getValue());
		} else if (criteria.getOperation().equalsIgnoreCase(">")) {
			return builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase(">=")) {
			return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase("<")) {
			return builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase("<=")) {
			return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
		} else if (criteria.getOperation().equalsIgnoreCase(":")) {
			if (root.get(criteria.getKey()).getJavaType() == String.class) {
				return builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
			} else {
				return builder.equal(root.get(criteria.getKey()), criteria.getValue());
			}
		}
		return null;
	}
}
