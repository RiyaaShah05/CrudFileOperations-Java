package com.java.mysql.Crud.CrudFileOperations;

public class OrderDetails {
	private int order_id;
//	private List<Integer> product_id;
//	private List<Integer> quantity;
//	private List<Product> idQty;
	private int product_id;
	private int quantity;
	//List<OrderDetails> details;
	
	
	
//	public List<Product> getIdQty() {
//		return idQty;
//	}

	public OrderDetails(int order_id, int product_id, int quantity) {
		super();
		this.order_id = order_id;
		this.product_id = product_id;
		this.quantity = quantity;
	}
	
	public OrderDetails(int product_id, int quantity) {
		super();
		this.product_id = product_id;
		this.quantity = quantity;
	}

//	public OrderDetails(int order_id, List<OrderDetails> details) {
//		this.order_id = order_id;
//		this.details = details;
//		
//		// TODO Auto-generated constructor stub
//	}

	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public int getQuantity() {
		return quantity;
	}

//	public List<OrderDetails> getDetailOrder() {
//		// TODO Auto-generated method stub
//		return details;
//	}

}
