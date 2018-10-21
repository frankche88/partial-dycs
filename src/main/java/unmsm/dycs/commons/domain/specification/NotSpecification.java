package unmsm.dycs.commons.domain.specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NotSpecification<T> extends AbstractSpecification<T> {

	private Specification<T> spec;

	public NotSpecification(Specification<T> s) {
		this.spec = s;
	}

	@Override
	public boolean isSatisfiedBy(T t) {
		return !spec.isSatisfiedBy(t);
	}

	@Override
	public Predicate toPredicate(Root<T> root, CriteriaBuilder cb) {
		return cb.not(spec.toPredicate(root, cb));
	}

}
