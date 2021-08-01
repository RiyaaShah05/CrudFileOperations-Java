package com.java.mysql.Crud.CrudFileOperations;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class MysqlConnect {


	private Validator valid = new Validator();

	InputStream input = null;
	Query connect = null;

	//Query objQuery = new Query(dbURL,uname,pwd);

	public void fileRead() throws SQLException {
		//InputStream input = null;


		try {

			Properties prop = new Properties();
			input = new FileInputStream("C:\\Users\\SHrill\\eclipse-workspace\\CrudFileOperations\\Files\\Config.properties");
			prop.load(input);

			String dbURL = prop.getProperty("dburl");
			String uname = prop.getProperty("username");
			String pwd = prop.getProperty("password");
			System.out.println("file is found ");

			connect= new Query(dbURL,uname,pwd);
			connect.connection();
			System.out.println("Connected");	
		}catch (IOException ex) {
			//TODO Auto-generated catch block
			ex.printStackTrace();
		}


	}

	public static void main(String args[]) {

		MysqlConnect obj=new MysqlConnect();
		try {
			obj.fileRead();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Crud Operation");
		try (Scanner sc = new Scanner(System.in)) {

			System.out.println("1\t Insert Customer");
			System.out.println("2\t Insert Product");
			System.out.println("3\t Show list of product");
			System.out.println("4\t Insert Order");
			System.out.println("5\t show Order");

			System.out.println("Please enter your choice:");

			int crud=sc.nextInt();
			//
			switch(crud)
			{
			case 1:
				obj.insertCustomer();
				break;
			case 2:
				obj.insertProduct();
				break;
			case 3:
				obj.listProduct();
				break;
			case 4:
				obj.insertOrder();
				break;
			case 5:
				obj.orderDetail();
				break;
			default:
				System.out.println("Enter a Valid Choice");
				//break;
				obj.mainmenu();
			}

		}catch(Exception e)
		{
			System.out.println(e);
		}
	}

	private void mainmenu() throws IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
		System.out.println("Do you want to go to main menu: y/n");  
		String s=br.readLine();  
		if(s.startsWith("y")) {
			MysqlConnect.main(null);
		}
	}
	private void insertCustomer() throws IOException, SQLException {	
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  

		do{   
			//String fname = getproperfname(br);

			System.out.println("enter first name:");  
			String fname=br.readLine(); 

			System.out.println("enter last name:"); 
			String lname=br.readLine();

			String email = getproperemail(br);

			String phone = getproperphone(br);

			Customer cust = new Customer(fname,lname,email,phone);
			int i =connect.insertC(cust);

			System.out.println(i+" records affected");  

			System.out.println("Do you want to continue: y/n");  
			String s=br.readLine();  
			if(s.startsWith("n")){ 
				break; 
			}

		}while(true);
		mainmenu();
	}

	private String getproperemail(BufferedReader br) throws IOException, SQLException {
		System.out.println("enter email:"); 		
		String email=br.readLine();

		if(connect.checkemails(email)) {
			System.out.println(" email is Exists : Try again"); 	
			email=br.readLine();
		}
		else {
			while(!valid.emailValidator(email)) {
				System.out.println(" email is invalid : Try again"); 		
				email=br.readLine();
			}
		}
		return email;
	}


	private String getproperphone(BufferedReader br) throws IOException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("enter phone:");  
		String phone=br.readLine(); 

		if(connect.checkphones(phone)) {
			System.out.println(" Phone is Exists : Try again"); 	
			phone=br.readLine();
		}
		else {

			while(!valid.isPhoneNumberValid(phone)) {
				System.out.println(" phone is invalid : Try again"); 		
				phone=br.readLine();
			}
		}
		return phone;
	}

	private void insertProduct() throws IOException, SQLException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  


		do{   

			String pname = getproperName(br);

			System.out.println("enter quantity:"); 		
			String qty=br.readLine();	
			
			int quantity = Integer.parseInt(qty);
	
			System.out.println("enter price:"); 

			String price = br.readLine();  
			int amount = Integer.parseInt(price);

			Product prod = new Product(pname,quantity,amount);
			int i =connect.insertP(prod);

			System.out.println(i+" records affected");  

			System.out.println("Do you want to continue: y/n");  
			String s=br.readLine();  
			if(s.startsWith("n")){ 
				mainmenu();
			}

		}while(true);

	}

	private String getproperName(BufferedReader br) throws IOException, SQLException {
		// TODO Auto-generated method stub
		System.out.println("enter product name:"); 
		String pname=br.readLine();
		
		if(connect.checkpName(pname)) {
			System.out.println(" Product Name is Exists : Try again"); 	
			pname=br.readLine();
		}
		return pname;
	}


	public void listProduct() throws SQLException, NumberFormatException, IOException {

		List<Product> productList = connect.selectP();
		for(Product val : productList){
			System.out.println("Product ID:" +val.getProduct_id());
			System.out.println("Product_name:"+val.getProduct_name());
			System.out.println("Quantity:"+val.getQuantity());
			System.out.println("Price:"+val.getPrice());
			System.out.print("\n");

		}
		mainmenu();
	}

	private void insertOrder() throws SQLException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
		Query obj = new Query();
		int amount =0;

		List<OrderDetails> details = new ArrayList<>();
		//OrderDetails orderdetailobj;
	//	List<Integer> quantity = new ArrayList<>();

		System.out.println("Enter Customer id:");
		String cust_id = br.readLine();
		int customer_id = Integer.parseInt(cust_id);

		do{
			System.out.println("enter product id:"); 		
			String id=br.readLine();
			int prod_id = Integer.parseInt(id);

			int prod_price = connect.selectPrice(prod_id);
			
			int i =connect.validQty(prod_id);
			
			int quantity_p = getproperProduct(i);

		//	details.add(quantity_p);
			
			OrderDetails orderdetailobj = new OrderDetails(prod_id, quantity_p);
			details.add(orderdetailobj);
			
			Product prd = new Product(prod_id,quantity_p);
			connect.updateP(prd);

			amount = amount + (quantity_p*prod_price);

			System.out.println("Do you want to continue: y/n");  
			String s=br.readLine();  
			if(s.startsWith("n")){ 
				break; 
			}

		}while(true);

		Orders order = new Orders(customer_id,obj.getDate(),amount);
		int i = connect.insertO(order);
		
		//OrderDetails orderdetails = new OrderDetails(i,details);
		if( connect.prodQty(i,details)) {
			System.out.println("success");
		}

		mainmenu();
	}

	public int getproperProduct(int i ) throws NumberFormatException, SQLException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));  
		System.out.println("enter quantity:"); 		
		//String qty=br.readLine();	
		int quantity = Integer.parseInt(br.readLine());
		//int quant = connect.validQty(quantity);
		while(quantity > i) {
			  
			System.out.println(" Quantity is invalid : Try again"); 		
			quantity=Integer.parseInt(br.readLine()); 		

		}

		return quantity;

	}
	
	public void orderDetail() throws SQLException, IOException {
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	
		System.out.println("Enter Customer id:");
		String cust_id1 = br.readLine();
		int customer_id1 = Integer.parseInt(cust_id1);

		List<Orders> orderList  = connect.getOrders(customer_id1);
		for(Orders val1 : orderList){
			System.out.println("order ID:" +val1.getOrder_id());
			System.out.println("order_date:"+val1.getDate());
			System.out.println("Price:"+val1.getAmount());
			System.out.print("\n");
			detail(customer_id1);
			//System.out.println("Price:"+val1.getPrice());
			System.out.print("\n");
		}
		mainmenu();
	}
	
	public void detail(int customer_id1) throws IOException, SQLException {
		
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
//		System.out.println("Enter Customer id:");
//		String cust_id1 = br.readLine();
//		int customer_id1 = Integer.parseInt(cust_id1);

		List<OrderDetails> orderdetails =connect.getOrderdetails(customer_id1);
		
		for(OrderDetails val1 : orderdetails){
			System.out.println("order ID:" +val1.getOrder_id());
			System.out.println("Product_id:"+val1.getProduct_id());
			System.out.println("Quantity:"+val1.getQuantity());
			//System.out.println("Price:"+val1.getPrice());
			System.out.print("\n");
		}		
	}	
}








