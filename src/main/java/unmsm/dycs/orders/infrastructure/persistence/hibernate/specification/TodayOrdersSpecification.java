package unmsm.dycs.orders.infrastructure.persistence.hibernate.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.joda.time.DateTime;

import unmsm.dycs.commons.domain.specification.AbstractSpecification;
import unmsm.dycs.orders.domain.entity.Order;

public class TodayOrdersSpecification extends AbstractSpecification<Order> {

	@Override
	public Predicate toPredicate(Root<Order> root, CriteriaBuilder cb) {
		
		
		final Path<DateTime> ReleaseDate = root.<DateTime> get("orderDate");
		
		return cb.lessThanOrEqualTo(ReleaseDate, DateTime.now().minusDays(1));
	}

	@Override
	public boolean isSatisfiedBy(Order order) {
		
		return !order.getOrderDate().isAfter(DateTime.now().minusDays(1));
		
	}

}
