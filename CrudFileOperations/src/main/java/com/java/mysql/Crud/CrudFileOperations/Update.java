package com.java.mysql.Crud.CrudFileOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
	
	private Connection conn;
	
	public Update(Connection conn){
		this.conn=conn;
	}
	
	public void updateC() throws SQLException {

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("Update customer set first_name= ? where cust_id =?");
		} catch (SQLException e) {
			e.printStackTrace();
		}  

		ps.setString(1,"riyaa");//1 specifies the first parameter in the query i.e. name  
		ps.setInt(2,1);  

		int i=ps.executeUpdate();  
		System.out.println(i+" records updated"); 
		conn.close();

	}

	public void updateP() throws SQLException {

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("Update product set quantity= ? where product_id =?");
		} catch (SQLException e) {
			e.printStackTrace();
		}  

		ps.setInt(1,9000);//1 specifies the first parameter in the query i.e. name  
		ps.setInt(2,1007);  

		int i=ps.executeUpdate();  
		System.out.println(i+" records updated"); 
		conn.close();

	}

}
