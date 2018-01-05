package org.tcf.sql.entity;

import org.tcf.exception.DaoException;

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
		if(column.getName() == null || column.getName().trim().length() == 0)
			throw new DaoException("排序依据的列名不能为空");
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
	public static Order desc(String name){
		return new Order(new ColumnInfo(name), OrderType.desc);
	}
	public static Order asc(String name){
		return new Order(new ColumnInfo(name), OrderType.asc);
	}
	@Override
	public String toString() {
		return "Order [column=" + column + ", orderType=" + orderType + "]";
	}
}
