package com.java.mysql.Crud.CrudFileOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertO {
	int product_id; String product_name; int quantity; int price;
	int cust_id;
	int order_id;
	PreparedStatement ps = null;

	// Getter
	public int getid() {
		return product_id;
	}
	// Setter
	public void setC(int cust_id) {
		this.cust_id = cust_id;
	}
	public void setO(int order_id) {
		this.order_id = order_id;
	}

	public int getCid() {
		return cust_id;
	}

	public int getOid() {
		return order_id;
	}


	private  Connection conn;
	public InsertO(Connection conn) {
		this.conn = conn;
	}

	public void insertO() throws NumberFormatException, IOException, SQLException {

		Select obj = new Select(conn);
		//OrderList obj1 = new OrderList();
		//obj1.orderList();
		
		try {
			ps = conn.prepareStatement("INSERT INTO orders (cust_id,order_id,product_id,quantity,amount) VALUES (?,?,?,?,?)");
		} catch (SQLException e) {
			e.printStackTrace();
		}  

		BufferedReader br=new BufferedReader(new InputStreamReader(System.in)); 

		do{  
			System.out.println("enter product names:"); 		
			String prod_name=br.readLine();

			int prod_price = obj.selectPrice(prod_name);

			System.out.println("enter quantity:"); 		
			int qty=Integer.parseInt(br.readLine());

			int amount = qty*prod_price;

			int prod_id = obj.selectid(prod_name);

			ps.setInt(1, getCid());
			ps.setInt(2, getOid());
			ps.setInt(3, prod_id);
			ps.setInt(4, qty);
			ps.setFloat(5, amount);

			int i=ps.executeUpdate();  
			System.out.println(i+" records affected");  
			System.out.println("Do you want to continue: y/n");  
			String s=br.readLine();  
			if(s.startsWith("n")){  
				//System.out.println("Record Inserted Successfully");
				//new MysqlConnect();
				MysqlConnect.main(null);
				
			
			}
		}while(true);
	}
	
	
}
