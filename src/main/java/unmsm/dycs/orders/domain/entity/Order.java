package unmsm.dycs.orders.domain.entity;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.DateTime;

import unmsm.dycs.commons.domain.entity.BaseOrder;

public class Order implements BaseOrder {

	private Long id;
	private Buyer buyer;
	private String address;
	private String creditCardNumber;
	private String creditCardAuthCode;
	private DateTime orderDate;
	
	private Set<OrderItem> orderItems = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public String getCreditCardAuthCode() {
		return creditCardAuthCode;
	}

	public void setCreditCardAuthCode(String creditCardAuthCode) {
		this.creditCardAuthCode = creditCardAuthCode;
	}

	public DateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(DateTime orderDate) {
		this.orderDate = orderDate;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(Set<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public boolean isNil() {
		return false;
	}

	public Buyer getBuyer() {
		return buyer;
	}

	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}

}
