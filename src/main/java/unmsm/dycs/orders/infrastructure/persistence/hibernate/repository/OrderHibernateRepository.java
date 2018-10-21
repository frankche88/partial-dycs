package unmsm.dycs.orders.infrastructure.persistence.hibernate.repository;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import unmsm.dycs.commons.domain.specification.Specification;
import unmsm.dycs.commons.infrastructure.persistence.hibernate.repository.BaseHibernateRepository;
import unmsm.dycs.orders.domain.entity.NullOrder;
import unmsm.dycs.orders.domain.entity.Order;
import unmsm.dycs.orders.domain.repository.OrderRepository;

public class OrderHibernateRepository extends BaseHibernateRepository<Order> implements OrderRepository {

	@Override
	public Order create(Order order) {

		this.save(order);
		return order;
	}

	@Override
	public Order find(Long id) {
		
		Order order = this.getSession().find(Order.class, id);
		if(order == null) {
			 return new NullOrder();
		}
		
		return order;

	}

	@Override
	public void delete(Long id) {
		Order order = new Order();
		this.getSession().delete(order);

	}

	@Override
	public List<Order> findAll() {

		CriteriaBuilder builder = this.getSession().getCriteriaBuilder();

		CriteriaQuery<Order> criteria = builder.createQuery(Order.class);

		Root<Order> orderRoot = criteria.from(Order.class);

		criteria.select(orderRoot);

		List<Order> orders = this.getSession().createQuery(criteria).getResultList();

		return orders;
	}

	@Override
	public List<Order> findBySpecification(Specification<Order> specification) {
		
		CriteriaBuilder cb = this.getSession().getCriteriaBuilder();

		CriteriaQuery<Order> criteriaQuery = cb.createQuery(specification.getType());
		Root<Order> root = criteriaQuery.from(specification.getType());
		// get predicate from specification
		Predicate predicate = specification.toPredicate(root, cb);
		// set predicate and execute query
		criteriaQuery.where(predicate);
		
		
		TypedQuery<Order> indexQuery =  getSession().createQuery(criteriaQuery);
		

		return indexQuery.getResultList();
		
	}

}
