package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Auctions;
import model.Customer;


public class CustomerDao {
	/*
	 * This class handles all the database operations related to the customer table
	 */
	
	/**
	 * @param String searchKeyword
	 * @return ArrayList<Customer> object
	 */
	public List<Customer> getCustomers() {
		/*
		 * This method fetches one or more customers and returns it as an ArrayList
		 */
		
		List<Customer> customers = new ArrayList<Customer>();

		/*
		 * The students code to fetch data from the database will be written here
		 * Each record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */
		
		/*Sample data begins*/
		String query = "SELECT * FROM Customer";
		String query2 = "SELECT * FROM Person WHERE Id = ?";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/pwchristense", "pwchristense", "112123806");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				PreparedStatement ps = con.prepareStatement(query2);
				ps.setInt(1, rs.getInt("Id"));
				ResultSet result = ps.executeQuery();
				result.next();
				Customer customer = new Customer();
				customer.setAccountNo(rs.getInt("AccountNo"));
				customer.setAddress(result.getString("Address"));
				customer.setLastName(result.getString("LastName"));
				customer.setFirstName(result.getString("FirstName"));
				customer.setCity(result.getString("City"));
				customer.setState(result.getString("State"));
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(result.getInt("ZipCode"));
//				customer.setTelephone("5166328959");
				customer.setCreditCard(rs.getString("CreditCardNo"));
				customer.setRating(rs.getInt("Rating"));
				customers.add(customer);			
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
		
		return customers;
	}


	public Customer getHighestRevenueCustomer() {
		/*
		 * This method fetches the customer who generated the highest total revenue and returns it
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */


		/*Sample data begins*/
		
		Customer customer = new Customer();
		
		String query = "CREATE VIEW CustomerRevenue(AccountNo, TotalRevenue) AS SELECT AccountNo, SUM(TotalFare * 0.1) FROM Reservation GROUP BY AccountNo";
		String query2 = "SELECT CR.AccountNo, P.FirstName, P.LastName FROM CustomerRevenue CR, Customer C, Person P WHERE CR.AccountNo = C.AccountNo AND C.Id = P.Id AND CR.TotalRevenue >= (SELECT MAX(TotalRevenue) FROM CustomerRevenue)";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/pwchristense", "pwchristense", "112123806");
			Statement st = con.createStatement();
			int rows = st.executeUpdate(query);
			
			ResultSet rs = st.executeQuery(query2);
			rs.next();
			customer.setAccountNo(rs.getInt("AccountNo"));
			customer.setLastName(rs.getString("LastName"));
			customer.setFirstName(rs.getString("FirstName"));
		}
		catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
	
		return customer;
		
	}

	public List<Customer> getCustomerMailingList() {

		/*
		 * This method fetches the all customer mailing details and returns it
		 * The students code to fetch data from the database will be written here
		 * Each customer record is required to be encapsulated as a "Customer" class object and added to the "customers" List
		 */

		
		List<Customer> customers = new ArrayList<Customer>();
		
		/*Sample data begins*/
		
		String query = "SELECT P.FirstName, P.LastName, C.Email, P.Address, P.City, P.State, P.ZipCode FROM Customer C, Person P WHERE C.Id = P.Id";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/pwchristense", "pwchristense", "112123806");
			Statement st = con.createStatement();			
			ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setAddress(rs.getString("Address"));
				customer.setLastName(rs.getString("LastName"));
				customer.setFirstName(rs.getString("FirstName"));
				customer.setCity(rs.getString("City"));
				customer.setState(rs.getString("State"));
				customer.setEmail(rs.getString("Email"));
				customer.setZipCode(rs.getInt("ZipCode"));
				customers.add(customer);			
			}
		
		}
		catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
		
		return customers;
	}

	public Customer getCustomer(int accountNo) {

		/*
		 * This method fetches the customer details and returns it
		 * accountNo, which is the Customer's accountNo who's details have to be fetched, is given as method parameter
		 * The students code to fetch data from the database will be written here
		 * The customer record is required to be encapsulated as a "Customer" class object
		 */
		
		/*Sample data begins*/		
		String query = "SELECT * FROM Customer WHERE AccountNo = ?";
		Customer customer = new Customer();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/pwchristense", "pwchristense", "112123806");
			PreparedStatement st = con.prepareStatement(query);	
			st.setInt(1, accountNo);
			ResultSet rs = st.executeQuery();
			customer.setAccountNo(rs.getInt("AccountNo"));
			customer.setAddress(rs.getString("Address"));
			customer.setLastName(rs.getString("LastName"));
			customer.setFirstName(rs.getString("FirstName"));
			customer.setCity(rs.getString("City"));
			customer.setState(rs.getString("State"));
			customer.setEmail(rs.getString("Email"));
			customer.setZipCode(rs.getInt("ZipCode"));
			customer.setCreditCard(rs.getString("CreditCardNo"));
			customer.setRating(rs.getInt("Rating"));
		}
		catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
		
		return customer;
	}
	
	public String deleteCustomer(int accountNo) {

		/*
		 * This method deletes a customer returns "success" string on success, else returns "failure"
		 * The students code to delete the data from the database will be written here
		 * accountNo, which is the Customer's accountNo who's details have to be deleted, is given as method parameter
		 */

		/*Sample data begins*/
		return "success";
		/*Sample data ends*/
		
	}


	public int getCustomerID(String emailaddress) {
		/*
		 * This method returns the Customer's ID based on the provided email address
		 * The students code to fetch data from the database will be written here
		 * username, which is the email address of the customer, who's ID has to be returned, is given as method parameter
		 * The Customer's ID(accountNo) is required to be returned as a String
		 */
		int id = 0;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/rzinoviev", "rzinoviev", "111595936");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT C.Id FROM Customer C WHERE C.Email='" + emailaddress + "'");
		id = rs.getInt("Id");
		} catch(Exception e) {
			System.out.println(e);
		}

		return id;
	}


	public String addCustomer(Customer customer) {

		/*
		 * All the values of the add customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the customer details and return "success" or "failure" based on result of the database insertion.
		 */
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/rzinoviev", "rzinoviev", "111595936");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT MAX(Id) FROM Person");
			Integer id = rs.getInt("Id") + 1;
			st.executeQuery("INSERT INTO Person VALUES(" + id + ", " +
					customer.getFirstName() + ", " +
					customer.getLastName() + ", " +
					customer.getAddress() + ", " +
					customer.getCity() + ", " +
					customer.getState() + ", " +
					customer.getZipCode() + ", " + ")");
			st.executeQuery("INSERT INTO Customer VALUES(" + id + ", " +
					customer.getAccountNo() + ", " +
					customer.getCreditCard() + ", " +
					customer.getEmail() + ", " +
					"CURRENT_TIMESTAMP, " +
					customer.getRating() + ")");
			} catch(Exception e) {
				System.out.println(e);
				return "failure";
			}

			/*Sample data begins*/
			return "success";
		/*Sample data ends*/

	}

	public String editCustomer(Customer customer) {
		/*
		 * All the values of the edit customer form are encapsulated in the customer object.
		 * These can be accessed by getter methods (see Customer class in model package).
		 * e.g. firstName can be accessed by customer.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/rzinoviev", "rzinoviev", "111595936");
			Statement st = con.createStatement();
			st.executeQuery("UPDATE Customer SET CreditCardNo=" + customer.getCreditCard() + ", " +
					"Email=" + customer.getEmail() + ", " +
					"Rating=" + customer.getRating() + ")");
			} catch(Exception e) {
				System.out.println(e);
				return "failure";
			}
			/*Sample data begins*/
			return "success";
		/*Sample data ends*/

	}

}
