package com.java.mysql.Crud.CrudFileOperations;

import java.util.Scanner;

public class MakeOrder {

	String product_id;
	String product_name;
	
	
	public MakeOrder()
	{   
		System.out.print("\nPurchase Item(y/n)?");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String line = sc.nextLine();
		if("y".equalsIgnoreCase(line)){
			OpenOrder();
		}
	}

	private void OpenOrder() {
		OrderList obj = new OrderList();
		obj.orderList();
		//obj.getName();
		//OrderList obj1 = new OrderList();
		String id=obj.getid();

		System.out.println("Enter id");
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String code = sc.nextLine();

		if(id.contains(code)) {

			obj.getName();
			// EnterQuantity();   
		}
		else {System.out.print("Product Code is Invalid\n"); }
	}
}


