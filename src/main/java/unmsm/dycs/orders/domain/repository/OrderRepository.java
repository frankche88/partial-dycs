package unmsm.dycs.orders.domain.repository;

import java.util.List;

import unmsm.dycs.commons.domain.specification.Specification;
import unmsm.dycs.orders.domain.entity.Order;

public interface OrderRepository {

	Order create(Order order);

	Order find(Long id);

	void delete(Long id);

	List<Order> findAll();

	List<Order> findBySpecification(Specification<Order> specification);

}