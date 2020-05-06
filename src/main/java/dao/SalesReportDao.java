package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.SalesReport;

public class SalesReportDao {
	
	public List<SalesReport> getSalesReport(String month, String year) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get sales report for a particular month must be implemented using month and year passed
		 */
		
		
		List<SalesReport> sales = new ArrayList<SalesReport>();
			
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver" );
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from SalesReport where ResrDate like \'%" + month + "-" + year + "%\'" );
			while(rs.next()) {
				SalesReport salesreport = new SalesReport();
				salesreport.setBookingFee(rs.getDouble("BookingFee"));
				salesreport.setFirstName(rs.getString("FirstName"));
				salesreport.setLastName(rs.getString("LastName"));
				salesreport.setRepSSN(rs.getString("RepSSN"));
				salesreport.setResrDate(rs.getString("ResrDate"));
				salesreport.setResrNo(rs.getInt("ResrNo"));
				salesreport.setTotalFare(rs.getDouble("TotalFare"));
				sales.add(salesreport);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
						
		return sales;
		
	}

}
