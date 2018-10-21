package unmsm.dycs.orders.domain.entity;

public class NullOrder extends Order {

	@Override
	public boolean isNil() {
		return true;
	}

}
