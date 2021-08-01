package com.java.mysql.Crud.CrudFileOperations;

public class Product {
	
	private String product_name;
	private int quantity;
	private int price;
	private int product_id;
//	private ArrayList<Product> productList;
	
	public Product(String product_name, int quantity, int price) {
		super();
		this.product_name = product_name;
		this.quantity = quantity;
		this.price = price;
	}

	public Product(int product_id, String product_name, int quantity, int price) {
		super();
		this.product_id = product_id;
		this.product_name = product_name;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Product(int product_id, int quantity) {
		super();
		this.quantity = quantity;
		this.product_id = product_id;
	}

	public Product(String product_name) {
		this.product_name = product_name;
	}

	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public int getQuantity() {
		return quantity;
	}
	public int getPrice() {
		return price;
	}
	


}
