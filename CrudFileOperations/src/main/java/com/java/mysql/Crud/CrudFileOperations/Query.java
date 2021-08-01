package com.java.mysql.Crud.CrudFileOperations;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class Query {

	private Connection conn;
	//private Validator valid;
	private PreparedStatement checkemail;
	private PreparedStatement checkphone;
	private PreparedStatement insertCustomer;
	private PreparedStatement insertProduct;
	private PreparedStatement checkprodName;
	private PreparedStatement insertOrder;
	private PreparedStatement orderDetails;
	private PreparedStatement selectPrice;
	private PreparedStatement order;
	private PreparedStatement quantity;
	private PreparedStatement updatequantity;
	private PreparedStatement orderdetailsProduct;

	public Query(String dbURL,String uname,String pwd) throws SQLException {

		conn = DriverManager.getConnection(dbURL, uname, pwd);

		//	String query = "SELECT email FROM customer where email =?";

		checkemail = conn.prepareStatement("SELECT email FROM customer where cust_id =?");

		checkphone = conn.prepareStatement("SELECT phone FROM customer where cust_id =?");

		insertCustomer = conn.prepareStatement("INSERT INTO customer (first_name,last_name,email,phone) VALUES (?,?,?,?)");

		insertProduct = conn.prepareStatement("INSERT INTO product (product_name,quantity,price) VALUES (?,?,?)");

		checkprodName = conn.prepareStatement("SELECT product_name FROM product where product_id =?");

		String query1 = "INSERT INTO orders (cust_id,date,amount) VALUES (?,?,?)";
		insertOrder = conn.prepareStatement(query1,new String[]{"OrderID"});

		//	productName=conn.prepareStatement("select product_id from product); 
		selectPrice = conn.prepareStatement("select price from product where product_id = ?");

		orderDetails = conn.prepareStatement("select * from orders where cust_id = ?");  

		order = conn.prepareStatement("Insert into orderdetails (order_id,product_id ,quantity) values (?,?,?)");
		//insertCustomer = conn.prepareStatement(query)

		quantity = conn.prepareStatement("Select quantity from product where product_id = ?");

		orderdetailsProduct = conn.prepareStatement("select o.order_id,product_id, quantity from orderdetails o join orders ord"+ 
													" ON ord.order_id = o.order_id "+
													"where ord.cust_id = ?");  		
		
		updatequantity = conn.prepareStatement("update product set quantity = quantity-? WHERE product_id=?");

	}

	public Query() {
		// TODO Auto-generated constructor stub
	}

	public void connection() {
		if(conn != null) {}
	}

	public boolean checkemails(String email) throws SQLException {

		checkemail.setString(1,email);
		ResultSet rs = checkemail.executeQuery();
		if (rs.next()) {
			return false;
		} 
		return true;
	}

	public boolean checkphones(String phone) throws SQLException {

		checkphone.setString(1,phone);
		ResultSet rs = checkphone.executeQuery();
		if (rs.next()) {
			return false;
		} 
		return true;
	}

	public boolean checkpName(String pname) throws SQLException {

		checkprodName.setString(1,pname);
		ResultSet rs = checkprodName.executeQuery();
		if (rs.next()) {
			return false;
		} 
		return true;
	}


	public int validQty(int id) throws SQLException {
		int i = 0;
		quantity.setInt(1, id);
	
		ResultSet rs = quantity.executeQuery();
		if (rs.next()) {
			i = rs.getInt(1);
		}
		
		return i;
	}

	public int insertC(Customer c) throws SQLException {
		insertCustomer.setString(1,c.getFirst_name());  
		insertCustomer.setString(2,c.getLast_name());  
		insertCustomer.setString(3, c.getEmail());
		insertCustomer.setString(4, c.getPhone());
		int i=insertCustomer.executeUpdate();
		return i;
	}

	public int insertP(Product p) throws SQLException {

		insertProduct.setString(1, p.getProduct_name());
		insertProduct.setInt(2, p.getQuantity());
		insertProduct.setInt(3, p.getPrice());
		int i = insertProduct.executeUpdate(); 
		return i;		
	}

	public int insertO(Orders o) throws SQLException {
		int i=0;
		insertOrder.setInt(1, o.getCust_id());
		insertOrder.setDate(2,(java.sql.Date) o.getDate());
		insertOrder.setInt(3,o.getAmount());	
		insertOrder.executeUpdate();
		ResultSet rs = insertOrder.getGeneratedKeys();
		while(rs.next()) {
			i=rs.getInt(1);
		}
		rs.close();
		return i;
	}
	
	public int updateP(Product p) throws SQLException {
		//int i = 0;
		updatequantity.setInt(1, p.getQuantity());
		updatequantity.setInt(2, p.getProduct_id());
		int i = updatequantity.executeUpdate();
		//if(i < 0) {
		//	return p.getQuantity();
	//	}
		return i;
		
	}

	public Date getDate() {

		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		Date date = new Date(0);
		//	String odate = dateFormat.format(date);
		return date;
	}

	public List<Orders> getOrders(int CustomerID) throws SQLException{

		orderDetails.setInt(1, CustomerID);

		ResultSet rs=orderDetails.executeQuery(); 
		List<Orders> orderList = new ArrayList<>();
		while(rs.next()){  	
			int cust_id = rs.getInt("cust_id");
			int order_id = rs.getInt("order_id");
			Date order_date = rs.getDate("Date");
			int total = rs.getInt("amount");
			//prodQty();

			Orders orderobj = new Orders(cust_id,order_id,order_date,total);
			orderList.add(orderobj);
		}
		rs.close();
		return orderList;
	}

	public boolean prodQty(int i, List<OrderDetails> o) throws SQLException {
		
		for(OrderDetails val1 : o){
			order.setInt(1, i);
			order.setInt(2, val1.getProduct_id());
			order.setInt(3, val1.getQuantity());
			order.addBatch();
			
		}
		order.executeBatch();
		return false;
		
	}
	public List<OrderDetails> getOrderdetails(int CustomerID) throws SQLException{

		orderdetailsProduct.setInt(1, CustomerID);

		ResultSet rs=orderdetailsProduct.executeQuery(); 
		List<OrderDetails> orderList = new ArrayList<>();
		while(rs.next()){  	
			//int cust_id = rs.getInt("cust_id");
			int order_id = rs.getInt("order_id");
			int prod_id = rs.getInt("product_id");
			int quantity = rs.getInt("quantity");
			//prodQty();

			OrderDetails orderobj = new OrderDetails(order_id,prod_id,quantity);
			orderList.add(orderobj);
			
		//	orderList.add(orderobj);
		}
		rs.close();
		return orderList;
	}

	
	public List<Product> selectP() throws SQLException {	

		String query = "SELECT * FROM product";
		Statement st = conn.createStatement(); 
		ResultSet rs = st.executeQuery(query);
		List<Product> productList = new ArrayList<>();
		while (rs.next())
		{
			int product_id = rs.getInt("product_id");
			String product_name = rs.getString("product_name");
			int quantity = rs.getInt("quantity");
			int amount = rs.getInt("price");

			Product orderobj = new Product(product_id,product_name,quantity,amount);
			productList.add(orderobj);
		}
		rs.close();
		return productList;

	}

	public List<Product> selectPname() throws SQLException{
		String query = "SELECT product_name FROM product";
		Statement st = conn.createStatement(); 
		ResultSet rs = st.executeQuery(query);
		List<Product> productList = new ArrayList<>();
		while (rs.next())
		{			
			String product_name = rs.getString("product_name");
			Product orderobj = new Product(product_name);
			productList.add(orderobj);
		}
		rs.close();
		return productList;

	}
	public int selectPrice(int s) throws SQLException { 
		selectPrice.setInt(1,s);
		ResultSet rs=selectPrice.executeQuery(); 
		rs.next();
		int i =rs.getInt(1);
		return i;

	}

	
}
