package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Employee;

public class EmployeeDao {
	/*
	 * This class handles all the database operations related to the employee table
	 */
	
	public String addEmployee(Employee employee) {

		/*
		 * All the values of the add employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database insertion of the employee details and return "success" or "failure" based on result of the database insertion.
		 */
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/rzinoviev", "rzinoviev", "111595936");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT MAX(Id) FROM Person");
		Integer id = rs.getInt("Id") + 1;
		st.executeQuery("INSERT INTO Person VALUES(" + id + ", " +
				employee.getFirstName() + ", " +
				employee.getLastName() + ", " +
				employee.getAddress() + ", " +
				employee.getCity() + ", " +
				employee.getState() + ", " +
				employee.getZipCode() + ", " + ")");
		st.executeQuery("INSERT INTO Employee VALUES(" + id + ", " +
				employee.getSSN() + ", " +
				employee.getIsManager() + ", " +
				"CURRENT_DATE, " +
				employee.getHourlyRate() + ")");
		} catch(Exception e) {
			System.out.println(e);
			return "failure";
		}

		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	public String editEmployee(Employee employee) {
		/*
		 * All the values of the edit employee form are encapsulated in the employee object.
		 * These can be accessed by getter methods (see Employee class in model package).
		 * e.g. firstName can be accessed by employee.getFirstName() method.
		 * The sample code returns "success" by default.
		 * You need to handle the database update and return "success" or "failure" based on result of the database update.
		 */
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/rzinoviev", "rzinoviev", "111595936");
		Statement st = con.createStatement();
		st.executeQuery("UPDATE Employee SET SSN=" + employee.getSSN() + ", " +
				"IsManager=" + employee.getIsManager() + ", " +
				"HourlyRate=" + employee.getHourlyRate() + ")");
		} catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	public String deleteEmployee(String SSN) {
		/*
		 * SSN, which is the Employee's SSN which has to be deleted, is given as method parameter
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/rzinoviev", "rzinoviev", "111595936");
		Statement st = con.createStatement();
		st.executeQuery("DELETE FROM Employee WHERE SSN=" + SSN + ")");
		} catch(Exception e) {
			System.out.println(e);
			return "failure";
		}
		/*Sample data begins*/
		return "success";
		/*Sample data ends*/

	}

	
	public List<Employee> getEmployees() {

		/*
		 * The students code to fetch data from the database will be written here
		 * Query to return details about all the employees must be implemented
		 * Each record is required to be encapsulated as a "Employee" class object and added to the "employees" List
		 */
		List<Employee> employees = new ArrayList<Employee>();
		
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/rzinoviev", "rzinoviev", "111595936");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM Employee E, Person P WHERE E.Id=P.Id");
		while(rs.next()){
			Employee employee = new Employee();
			employee.setFirstName(rs.getString("FirstName"));
			employee.setLastName(rs.getString("LastName"));
			employee.setAddress(rs.getString("Address"));
			employee.setCity(rs.getString("City"));
			employee.setStartDate(rs.getDate("StartDate").toString());
			employee.setState(rs.getString("State"));
			employee.setZipCode(rs.getInt("ZipCode"));
			employee.setSSN(rs.getString("SSN"));
			employee.setHourlyRate(rs.getInt("HourlyRate"));
			employee.setIsManager(rs.getBoolean("IsManager"));
			employee.setEmail(rs.getString("Email"));
			
			employees.add(employee);
		}
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return employees;
	}

	public Employee getEmployee(String SSN) {

		/*
		 * The students code to fetch data from the database based on "SSN" will be written here
		 * SSN, which is the Employee's SSN who's details have to be fetched, is given as method parameter
		 * The record is required to be encapsulated as a "Employee" class object
		 */

		Employee employee = new Employee();
		
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/rzinoviev", "rzinoviev", "111595936");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM Employee E, Person P WHERE E.Id=P.Id AND E.SSN=" + SSN);
			employee.setFirstName(rs.getString("FirstName"));
			employee.setLastName(rs.getString("LastName"));
			employee.setAddress(rs.getString("Address"));
			employee.setCity(rs.getString("City"));
			employee.setStartDate(rs.getDate("StartDate").toString());
			employee.setState(rs.getString("State"));
			employee.setZipCode(rs.getInt("ZipCode"));
			employee.setSSN(rs.getString("SSN"));
			employee.setHourlyRate(rs.getInt("HourlyRate"));
			employee.setIsManager(rs.getBoolean("IsManager"));
			employee.setEmail(rs.getString("Email"));
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return employee;
	}
	
	public Employee getHighestRevenueEmployee() {
		
		/*
		 * The students code to fetch employee data who generated the highest revenue will be written here
		 * The record is required to be encapsulated as a "Employee" class object
		 */
		
		Employee employee = new Employee();
		
		try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/rzinoviev", "rzinoviev", "111595936");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("SELECT MAX(HourlyRate) FROM Employee");
		int hourlyrate = rs.getInt("HourlyRate");
		rs = st.executeQuery("SELECT * FROM Employee E, Person P WHERE E.Id=P.Id AND E.HourlyRate=" + hourlyrate);
			employee.setFirstName(rs.getString("FirstName"));
			employee.setLastName(rs.getString("LastName"));
			employee.setAddress(rs.getString("Address"));
			employee.setCity(rs.getString("City"));
			employee.setStartDate(rs.getDate("StartDate").toString());
			employee.setState(rs.getString("State"));
			employee.setZipCode(rs.getInt("ZipCode"));
			employee.setSSN(rs.getString("SSN"));
			employee.setHourlyRate(rs.getInt("HourlyRate"));
			employee.setIsManager(rs.getBoolean("IsManager"));
			employee.setEmail(rs.getString("Email"));
		} catch(Exception e) {
			System.out.println(e);
		}
		
		return employee;
	}

	public String getEmployeeID(String username) {
		/*
		 * The students code to fetch data from the database based on "username"(email) will be written here
		 * username, which is the Employee's email address who's Employee ID has to be fetched, is given as method parameter
		 * The Employee ID(SSN) is required to be returned as a String
		 */
		String id = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/rzinoviev", "rzinoviev", "111595936");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT E.SSN FROM Employee E, Person P WHERE E.Id=P.Id AND P.Email=" + username);
			id = String.valueOf(rs.getInt("SSN"));
		} catch(Exception e) {
			System.out.println(e);
		}

		return id;
	}
	
}
