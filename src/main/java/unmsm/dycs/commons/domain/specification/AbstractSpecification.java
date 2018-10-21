package unmsm.dycs.commons.domain.specification;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractSpecification<T> implements Specification<T> {

	public abstract boolean isSatisfiedBy(T t);

	public AbstractSpecification<T> or(Specification<T> s) {
		return new OrSpecification<T>(this, s);
	}

	public AbstractSpecification<T> and(Specification<T> s) {
		return new AndSpecification<T>(this, s);
	}

	public AbstractSpecification<T> not() {
		return new NotSpecification<T>(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getType() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		return (Class<T>) type.getActualTypeArguments()[0];
	}

}
