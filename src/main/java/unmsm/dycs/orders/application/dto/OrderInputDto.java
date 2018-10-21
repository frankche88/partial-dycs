package unmsm.dycs.orders.application.dto;

import java.util.List;

public class OrderInputDto {

	private String address;
	private String creditCardNumber;
	private String creditCardAuthCode;
	
	private BuyerInputDto buyer;
	
	private List<OrderItemInputDto> orderItems;
	
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
	public List<OrderItemInputDto> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItemInputDto> orderItems) {
		this.orderItems = orderItems;
	}
	public BuyerInputDto getBuyer() {
		return buyer;
	}
	public void setBuyer(BuyerInputDto buyer) {
		this.buyer = buyer;
	}

}
