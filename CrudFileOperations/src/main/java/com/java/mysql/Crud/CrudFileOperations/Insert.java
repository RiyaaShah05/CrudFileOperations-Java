//package com.java.mysql.Crud.CrudFileOperations;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//public class Insert {
//
//	//private Validator validator;
//	private Connection conn;
//	PreparedStatement ps = null;
//	
//	Validator obj= new Validator();
//		
//	public Insert(Connection conn){
//		this.conn=conn;
//	}
//
//	public void insertC() throws NumberFormatException, IOException, SQLException
//	{
////		//PreparedStatement ps = null;
////		try {
////			ps = conn.prepareStatement("INSERT INTO customer (first_name,last_name,email,phone)VALUES (?,?,?,?)");
////			
////		} catch (SQLException e) {
////			e.printStackTrace();
////		}  
//
//		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
//
//		do{  
//			//System.out.println("enter id:");  
//			//int id=Integer.parseInt(br.readLine());  
//			System.out.println("enter first name:");  
//			String fname=br.readLine(); 
//			Select objf = new Select(conn);
//			//if(objf.selectFname(fname)) {break;}
//			
//			System.out.println("enter last name:"); 
//			String lname=br.readLine();
//			System.out.println("enter email:"); 		
//			String email=br.readLine();
//			
//
//			if (obj.emailValidator(email) ) {
//				if(objf.selectemail(email)) {break;}
//			}
//			else {
//				System.out.println("The email address " + email + " is invalid");
//				break;
//			}
//			System.out.println("enter phone:"); 		
//			String phone = br.readLine();  
//			if(obj.isPhoneNumberValid(phone)) {	
//			}else {
//				System.out.println("The phone number " + phone + " is invalid");
//				break;
//			}	
//			
//			Customer cust = new Customer(fname,lname,email,phone);
//			try {
//				int i= Query.insertC(cust);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		
//		
//			//ps.setInt(1,id);  
//			ps.setString(1,fname);  
//			ps.setString(2,lname);  
//			ps.setString(3, email);
//			ps.setString(4, phone);
//			int i=ps.executeUpdate();  
//			System.out.println(i+" records affected");  
//
//			System.out.println("Do you want to continue: y/n");  
//			String s=br.readLine();  
//			if(s.startsWith("n")){ 
//				break;  
//			}  
//		}while(true);  		
//		conn.close(); 	
//
//	}
//
//	public void insertP() throws NumberFormatException, IOException, SQLException
//	{
//		//PreparedStatement ps = null;
//		try {
//			ps = conn.prepareStatement("INSERT INTO product VALUES (?,?,?,?,?)");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}  
//
//		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
//
//		do{  
//			System.out.println("enter id:");  
//			int id=Integer.parseInt(br.readLine());  
//			System.out.println("enter product code:");  
//			String pcode=br.readLine();  
//			System.out.println("enter product name:"); 
//			String pname=br.readLine();
//			System.out.println("enter quantity:"); 		
//			String qty=br.readLine();	
//			System.out.println("enter price:"); 		
//			String price = br.readLine();  
//
//			ps.setInt(1,id);  
//			ps.setString(2,pcode);  
//			ps.setString(3,pname);  
//			ps.setString(4, qty);
//			ps.setString(5, price);
//			int i =ps.executeUpdate(); 
//			show(i);
//
//			System.out.println("Do you want to continue: y/n");  
//			String s=br.readLine();  
//			if(s.startsWith("n")){  
//				//System.out.println("Record Inserted Successfully");
//				break;  
//			}  
//		}while(true);  
//
//		conn.close();  
//	}
//
//	private void show(int i) {
//		
//		System.out.println(i+" records affected"); 
//		// TODO Auto-generated method stub
//	//	return i;
//		
//	}
//
//}
