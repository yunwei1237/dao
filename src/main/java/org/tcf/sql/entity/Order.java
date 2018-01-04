package org.tcf.sql.entity;

/**
 * 用于排序
 * @author Archer Tan
 *
 */
public class Order {
	private ColumnInfo column;
	private OrderType orderType;
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Order(ColumnInfo column, OrderType orderType) {
		super();
		this.column = column;
		this.orderType = orderType;
	}
	public ColumnInfo getColumn() {
		return column;
	}
	public void setColumn(ColumnInfo column) {
		this.column = column;
	}
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	@Override
	public String toString() {
		return "Order [column=" + column + ", orderType=" + orderType + "]";
	}
}
