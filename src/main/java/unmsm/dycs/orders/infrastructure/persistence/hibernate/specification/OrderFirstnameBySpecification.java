package unmsm.dycs.orders.infrastructure.persistence.hibernate.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import unmsm.dycs.commons.domain.specification.AbstractSpecification;
import unmsm.dycs.orders.domain.entity.Order;

public class OrderFirstnameBySpecification extends AbstractSpecification<Order> {

	private static String firstname;

	public OrderFirstnameBySpecification(final String firstName) {
		firstname = firstName;
	}

	@Override
	public Predicate toPredicate(Root<Order> root, CriteriaBuilder cb) {
		
		return cb.equal(root.get("buyer.firstName"), firstname);
	}

	@Override
	public boolean isSatisfiedBy(Order movie) {
		
		return firstname.equals(movie.getBuyer().getFirstName());
	}

}
