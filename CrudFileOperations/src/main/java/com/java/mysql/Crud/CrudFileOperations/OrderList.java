package com.java.mysql.Crud.CrudFileOperations;

import java.util.ArrayList;
import java.util.List;

public class OrderList {


	String product_id; String product_name; int quantity; int price;

	public OrderList(String product_id,String product_name,int quantity,int price) {
		this.product_id = product_id; 
		this.product_name = product_name; 
		this.quantity = quantity; 
		this.price = price; }

	//	private String name; 

	public String getid() { 
		return product_id; 
	}

	public String getName() {
		return product_name; 

	} 
	public int getquantity() {
		return quantity;
	} 

	public int getprice() {
		return price; 
	}
	
	public OrderList() {}

	public void orderList() { // TODO Auto-generated constructor stub }

		//Creating a List 
		List<OrderList> list=new ArrayList<OrderList>(); 
		//Adding elements in the List

		OrderList obj  = new OrderList("1A","pen red  ",500,5); 
		OrderList obj1 = new OrderList("2A","pen blue ",500,4); 
		OrderList obj2 = new OrderList("3A","pen black",500,5); 
		OrderList obj3 = new OrderList("4A","pencil 2B",500,3); 
		OrderList obj4 = new OrderList("5A","pencil 2H",500,4); 
		OrderList obj5 = new OrderList("6A","pencil 4B",500,5);

		list.add(obj); list.add(obj1); list.add(obj2); list.add(obj3);
		list.add(obj4); list.add(obj5);

		for(OrderList l:list){
			//System.out.println(l.product_id+" "+l.product_name+" "+l.quantity+" "+l.price); 
			System.out.println("Product ID: " +l.getid());
			System.out.println("Product name: " +l.getName());
			System.out.println("Quantity:"+l.getquantity());
			System.out.println("Price: "+l.getprice());
			System.out.println("-----------------------------");


		}
	}
}


