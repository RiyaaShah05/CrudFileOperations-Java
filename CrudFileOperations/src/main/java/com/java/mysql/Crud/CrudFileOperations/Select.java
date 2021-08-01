package com.java.mysql.Crud.CrudFileOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {

	private Connection conn;
	//private Select selectP;

	public Select(Connection conn){
		this.conn=conn;
	}

	public Select() {

	}

	public void selectC() throws SQLException {
		PreparedStatement ps=conn.prepareStatement("select * from customer");  
		ResultSet rs=ps.executeQuery();  
		while(rs.next()){  
			System.out.println("ID:"+ rs.getInt(1)+" ,"+"First name: "+rs.getString(2)+", "+"Last name: "+rs.getString(3)+" ,"+"Email: "+rs.getString(4)+", "+"Phone: "+rs.getString(5));  
		}  
	}

	public boolean selectemail(String email) throws SQLException {
		String query = "SELECT cust_id FROM customer WHERE email=" +"\""+email+"\""+";";
		//try {
			Statement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery(query) ;
			rs.next();
			if(rs!= null) {
				System.out.println("same");
			}
			//return true;
		 return true;
		
	}



	public void selectP() throws SQLException {
		PreparedStatement ps=conn.prepareStatement("select * from product ");  
		ResultSet rs=ps.executeQuery();  
		while(rs.next()){  
			System.out.println("ID:"+ rs.getInt(1)+" , "+"Product name: "+rs.getString(2)+" ,"+"Quantity: "+rs.getString(3)+", "+"Price: "+rs.getString(4));  
		}  
	}

	public int selectPrice(String s) throws SQLException {
		PreparedStatement ps=conn.prepareStatement("select price from product where product_name = ?"); 
		ps.setString(1,s);
		ResultSet rs=ps.executeQuery(); 
		rs.next();
		int i =rs.getInt(1);

		return i;

	}
	public int selectid(String s) throws SQLException {
		PreparedStatement ps=conn.prepareStatement("select product_id from product where product_name = ?"); 
		ps.setString(1,s);
		ResultSet rs=ps.executeQuery(); 
		rs.next();
		int i =rs.getInt(1);

		return i;

	}

	public void orderDetails(Integer i) throws SQLException {
		PreparedStatement ps=conn.prepareStatement("select * from orders where cust_id = ?");  
		ps.setInt(1, i);
		ResultSet rs=ps.executeQuery();  
		while(rs.next()){  		
			System.out.println("ID:"+ rs.getInt(1)+" ,"+"cust_id: "+rs.getInt(2)+", "+"order_id: "
		                   +rs.getInt(3)+" ,"+"product_id: "+rs.getInt(4)+", "
		                   		+ ""+"quantity: "+rs.getInt(5)+" ,"+"amount: "+rs.getInt(6));  

		}
	}

}
