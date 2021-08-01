package com.java.mysql.Crud.CrudFileOperations;

import java.util.Date;
import java.util.List;

public class Orders {
	
	private int cust_id;
	private int order_id;
	private Date date;
	private int amount;
	private List<OrderDetails> detailOrder;
	
	
	public Orders(int cust_id, Date date, int amount) {
		super();
		this.cust_id = cust_id;
		this.date = date;
		this.amount = amount;
	}

	public Orders(int cust_id, Date date, int amount, List<OrderDetails> detailOrder) {
		super();
		this.cust_id = cust_id;
		this.date = date;
		this.amount = amount;
		this.detailOrder = detailOrder;
	}
	
	public Orders(int cust_id,int order_id, Date date, int amount) {
		super();
		this.cust_id = cust_id;
		this.order_id = order_id;
		this.date = date;
		this.amount = amount;
		
	}


	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public Date getDate() {
		return date;
	}

	public int getAmount() {
		return amount;
	}

	public List<OrderDetails> getDetailOrder() {
		return detailOrder;
	}



}
