package org.tcf.sql.entity;

public class Order {
	private String column;
	private OrderType orderType;
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	public Order(String column, OrderType orderType) {
		super();
		this.column = column;
		this.orderType = orderType;
	}
	public Order(String column) {
		super();
		this.column = column;
		this.orderType = OrderType.asc;
	}
}
