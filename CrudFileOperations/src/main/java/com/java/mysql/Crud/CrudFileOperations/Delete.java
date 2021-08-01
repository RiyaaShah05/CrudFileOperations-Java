package com.java.mysql.Crud.CrudFileOperations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
	
	private Connection conn;
	
	public Delete(Connection conn){
		this.conn=conn;
	}
	
	public void deleteC() throws SQLException, NumberFormatException, IOException {

		PreparedStatement ps=conn.prepareStatement("delete from customer where cust_id=?"); 
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  

		do{  
			System.out.println("enter id:");  
			int id=Integer.parseInt(br.readLine());

			ps.setInt(1,id);  

			int i=ps.executeUpdate();  
			System.out.println(i+" records deleted");  
			System.out.println("Do you want to continue: y/n");  
			String s=br.readLine();  
			if(s.startsWith("n")){ 
				//System.out.println("Record Deleted Successfully");
				break;  
			}  
		}while(true);  

		conn.close();  
	}

	public void deleteP() throws SQLException, NumberFormatException, IOException {

		PreparedStatement ps=conn.prepareStatement("delete from product where product_id=?"); 
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  

		do{  
			System.out.println("enter id:");  
			int id=Integer.parseInt(br.readLine());

			ps.setInt(1,id);  

			int i=ps.executeUpdate();  
			System.out.println(i+" records deleted");  
			System.out.println("Do you want to continue: y/n");  
			String s=br.readLine();  
			if(s.startsWith("n")){ 
				//System.out.println("Record Deleted Successfully");
				break;  
			}  
		}while(true);  

		conn.close();  
	}

}
