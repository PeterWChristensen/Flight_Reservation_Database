package dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.FlightReservations;

import model.Itinerary;

public class FlightReservationsDao {
	
	public List<FlightReservations> getReservations(int FlightNum, String airlineID, String CustomerName) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get flight reservations based on FlightNum OR CustomerName passed
		 * Only one of the two strings will be set, either (FlightNum = 0 and airlineID="") or CustomerName = "" depending on query
		 */
		
		List<FlightReservations> reservations = new ArrayList<FlightReservations>();
			
		/*Sample data begins*/
		for (int i = 0; i < 4; i++) {
			FlightReservations reservation = new FlightReservations();
			
			reservation.setResrNo(111);
			reservation.setResrDate("2011-01-01");
			reservation.setTotalFare(150.22); 
			reservation.setBookingFee(10.12);
			reservation.setRepSSN("198498472");
			reservation.setFirstName("John");
			reservation.setLastName("Wick");

			reservations.add(reservation);
				
		}
		/*Sample data ends*/
						
		return reservations;
		
	}
	
	public List<FlightReservations> getRevenueSummary(int FlightNum, String airlineID, String CustomerName,String destCity) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get flight reservations based on FlightNum OR CustomerName passed
		 * Only one of the two strings will be set, either (FlightNum = 0 and airlineID = "") or CustomerName = ""  
		 * or destCity = "" depending on query
		 */
		
		List<FlightReservations> reservations = new ArrayList<FlightReservations>();
		try {
			Class.forName("com.mysql.jdbc.Driver" );
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("");
			if (airlineID != null) {
				rs = st.executeQuery("select * from FlightReservations F, Itinerary I where I.FlightNo =" + FlightNum + "and I.AirlineID = " + airlineID 
					+ "and F.ResrNo = I.ResrNo");
			}
			else if (CustomerName != null) {
				rs = st.executeQuery("select * from FlightReservations F, Itinerary I where I.FlightNo =" + FlightNum + "and F.FirstName" + " " + "F.LastName = " + CustomerName 
						+ "and F.ResrNo = I.ResrNo");
			}
			else {
				rs = st.executeQuery("select * from FlightReservations F, Itinerary I where I.FlightNo =" + FlightNum + "and Arrival = " + destCity 
						+ "and F.ResrNo = I.ResrNo");
			}
			while(rs.next()) {
				FlightReservations flightres = new FlightReservations();
				flightres.setFirstName(rs.getString("FirstName"));
				flightres.setLastName(rs.getString("LastName"));
				flightres.setPassengerID(rs.getInt("PassengerID"));
				reservations.add(flightres);
			}
		} catch(Exception e) {
			System.out.println(e);
		}	
		
		/*Sample data ends*/
						
		return reservations;
		
	}
	
	
	public List<FlightReservations> getPassengerList(int FlightNum, String AirlineID) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get passenger list given flight number and Airline ID
		 */
		
		List<FlightReservations> reservations = new ArrayList<FlightReservations>();
			
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver" );
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from FlightReservations F, Itinerary I where I.FlightNo =" + FlightNum + "and I.AirlineID = " + AirlineID 
					+ "and F.ResrNo = I.ResrNo");
			while(rs.next()) {
				FlightReservations flightres = new FlightReservations();
				flightres.setFirstName(rs.getString("FirstName"));
				flightres.setLastName(rs.getString("LastName"));
				flightres.setPassengerID(rs.getInt("PassengerID"));
				reservations.add(flightres);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
						
		return reservations;
		
	}
	
	public List<FlightReservations> getCurrentReservations(int accountNo) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get current flight reservations based on accountno 
		 */
		
		List<FlightReservations> reservations = new ArrayList<FlightReservations>();
			
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver" );
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from FlightReservations F, Itinerary I where AccountNo =" + accountNo
					+ "and F.ResrNo = I.ResrNo and I.DepTime >= NOW()");
			while(rs.next()) {
				FlightReservations flightres = new FlightReservations();
				flightres.setAccountNo(rs.getInt("AccountNo"));
				flightres.setResrDate(rs.getString("ResrDate"));
				flightres.setBookingFee(rs.getDouble("BookingFee"));
				flightres.setFirstName(rs.getString("FirstName"));
				flightres.setLastName(rs.getString("LastName"));
				flightres.setPassengerID(rs.getInt("PassengerID"));
				flightres.setRepSSN(rs.getString("RepSSN"));
				flightres.setResrNo(rs.getInt("ResrNo"));
				flightres.setRevenue(rs.getDouble("Revenue"));
				flightres.setTotalFare(rs.getDouble("TotalFare"));
				reservations.add(flightres);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
						
		return reservations;
		
	}

	public List<FlightReservations> getAllReservations(int accountNo) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get all flight reservations based on accountno 
		 */
		
		List<FlightReservations> reservations = new ArrayList<FlightReservations>();
			
		/*Sample data begins*/
		try {
			Class.forName("com.mysql.jdbc.Driver" );
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from FlightReservations where AccountNo =" + accountNo);
			while(rs.next()) {
				FlightReservations flightres = new FlightReservations();
				flightres.setAccountNo(rs.getInt("AccountNo"));
				flightres.setResrDate(rs.getString("ResrDate"));
				flightres.setBookingFee(rs.getDouble("BookingFee"));
				flightres.setFirstName(rs.getString("FirstName"));
				flightres.setLastName(rs.getString("LastName"));
				flightres.setPassengerID(rs.getInt("PassengerID"));
				flightres.setRepSSN(rs.getString("RepSSN"));
				flightres.setResrNo(rs.getInt("ResrNo"));
				flightres.setRevenue(rs.getDouble("Revenue"));
				flightres.setTotalFare(rs.getDouble("TotalFare"));
				reservations.add(flightres);
			}
		} catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
						
		return reservations;
		
	}
	
	public String cancelReservation(int resrNo) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get cancel reservations based on resrNo
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		try {
			Class.forName("com.mysql.jdbc.Driver" );
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
			Statement st = con.createStatement();
			String sql = "DELETE from FlightReservations where ResrNo =" + resrNo;
			st.execute(sql);
		} catch(Exception e) {
			System.out.println(e);
		}
		return "success";	
		
	}


}
