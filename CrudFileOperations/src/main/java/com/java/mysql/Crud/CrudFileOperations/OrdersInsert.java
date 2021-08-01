package com.java.mysql.Crud.CrudFileOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdersInsert {

	private Connection conn;
	//private Select selectP;


	public OrdersInsert(Connection conn) {
		this.conn = conn;

	}

	public void purchaseOrder() throws NumberFormatException, IOException, SQLException {

		Select obj = new Select(conn);
		OrderList obj1 = new OrderList();
		obj1.orderList();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO orders (cust_id,order_id,product_id,quantity,amount) VALUES (?,?,?,?,?)");
		} catch (SQLException e) {
			e.printStackTrace();
		}  

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 

		do{  
			//System.out.println("enter product id:");  
			//int prod_id=Integer.parseInt(br.readLine());  
			System.out.println("enter order id:");  
			int ord_id=Integer.parseInt(br.readLine());  

			System.out.println("enter customer id:"); 
			int cust_id=Integer.parseInt(br.readLine());

			System.out.println("enter product names:"); 		
			String prod_name=br.readLine();

			float prod_price = obj.selectPrice(prod_name);

			System.out.println("enter quantity:"); 		
			int qty=Integer.parseInt(br.readLine());

			float amount = qty*prod_price;

			//System.out.println("enter cust id:"); 		
			//int cust_id=Integer.parseInt(br.readLine());

			int prod_id = obj.selectid(prod_name);

			ps.setInt(1, ord_id);
			ps.setInt(2, cust_id);
			ps.setInt(3, prod_id);
			ps.setInt(4, qty);
			ps.setInt(5, (int) amount);

			//	ps.setInt(5, prod_id);

			int i=ps.executeUpdate();  
			System.out.println(i+" records affected");  

			System.out.println("Do you want to continue: y/n");  
			String s=br.readLine();  
			if(s.startsWith("n")){  
				//System.out.println("Record Inserted Successfully");
				break;
			}
		}while(true);
	}

	public void details() throws NumberFormatException, IOException, SQLException {
		//Integer i = null;
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
		Select obj =new Select(conn);
		System.out.println("enter id:");  
		int id=Integer.parseInt(br.readLine());
		obj.orderDetails(id);

	}

}






