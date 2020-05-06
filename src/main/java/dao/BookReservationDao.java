package dao;
import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import model.Auctions;
import model.BookReservation;

public class BookReservationDao {

	public String bookOneWayRoundTripReservation(BookReservation bookRes) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to book reservations based on bookRes object passed
		 * repSSN will be set depending on who booked the reservation
		 * Use getters to fetch the data from the object
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
		
		String query = "INSERT INTO Reservation VALUES (?, ?, ?, ?, ?, ?); ";
		String query2 = "INSERT INTO Includes VALUES (?, ?, ?, ?, ?); "; 
		String query3 = "INSERT INTO ReservationPassenger VALUES(?, ?, ?, ?, ?, ?); ";
		
		String findResNo = "SELECT MAX(ResrNo) FROM Reservation";
		
		String query4 = "INSERT INTO Includes VALUES (?, ?, ?, ?, ?);";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/pwchristense", "pwchristense", "112123806");
			PreparedStatement st = con.prepareStatement(query);
			Statement statement = con.createStatement();
			ResultSet resno = statement.executeQuery(findResNo);
			resno.next();
			int resNo = resno.getInt(1) + 1;
			if (!(resNo > 0)) {
				resNo = 0;
			}
			
			st.setInt(1, resNo);
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
			String strDate = dateFormat.format(date);  
			st.setString(2, strDate);
			st.setInt(3, 400);
			st.setInt(4, 1000);
			if (bookRes.getRepSSN() != null && bookRes.getRepSSN() != "" )
				st.setInt(5, Integer.parseInt(bookRes.getRepSSN()));
			else 
				st.setNull(5, Types.NULL);
			st.setInt(6, 1111);
			boolean success = true;
			int result = st.executeUpdate();
			if (result <= 0)
				success = false;
			
			st = con.prepareStatement(query2);
			st.setInt(1,  resNo);
			st.setString(2, bookRes.getAirlineID());
			st.setInt(3, bookRes.getFlightNum1());
			st.setInt(4, 1);
			st.setString(5, bookRes.getDepartureDate());
			result = st.executeUpdate();
			if (result <= 0)
				success = false;
			
			st = con.prepareStatement(query3);
			st.setInt(1, resNo);
			st.setInt(2, 111);
			st.setInt(3, 1111);
			st.setString(4,  bookRes.getSeatNum());
			st.setString(5, bookRes.getSeatClass());
			st.setString(6,  bookRes.getMealPref());
			result = st.executeUpdate();
			if (result <= 0)
				success = false;

			
			if (bookRes.getTypeOfTrip() == "Round-trip") {
				st = con.prepareStatement(query3);
				st.setInt(1, resNo);
				st.setString(2, bookRes.getAirlineID());
				st.setInt(3, bookRes.getFlightNum2());
				st.setInt(4, 1);
				st.setString(5,  bookRes.getReturnDate());
				result = st.executeUpdate();
				if (result <= 0)
					success = false;
			}
			
			if (success)
				return "success";
			else
				return "failure";
		}
		catch(Exception e) {
			System.out.println(e);
		}
					
		return "failure";
		
	}
	
	public String bookMultiCityReservation(BookReservation bookRes) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to book reservations based on bookRes object passed
		 * repSSN will be set depending on who booked the reservation
		 * Use getters to fetch the data from the object
		 * DepartureAirport1, ArrivalAirport1, DepartureAirport2, ArrivalAirport2, Trip1Date, Trip2Date are the attributes to use here
		 * The sample code returns "success" by default.
		 * You need to handle the database deletion and return "success" or "failure" based on result of the database deletion.
		 */
					
		String query = "INSERT INTO Reservation VALUES (?, ?, ?, ?, ?, ?); ";
		String query2 = "INSERT INTO Includes VALUES (?, ?, ?, ?, ?); "; 
		String query3 = "INSERT INTO ReservationPassenger VALUES(?, ?, ?, ?, ?, ?); ";
		
		String findResNo = "SELECT MAX(ResrNo) FROM Reservation";
		
		String query4 = "INSERT INTO Includes VALUES (?, ?, ?, ?, ?);";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/pwchristense", "pwchristense", "112123806");
			PreparedStatement st = con.prepareStatement(query);
			Statement statement = con.createStatement();
			ResultSet resno = statement.executeQuery(findResNo);
			resno.next();
			int resNo = resno.getInt(1) + 1;
			if (!(resNo > 0)) {
				resNo = 0;
			}
			
			st.setInt(1, resNo);
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
			String strDate = dateFormat.format(date);  
			st.setString(2, strDate);
			st.setInt(3, 400);
			st.setInt(4, 1000);
			if (bookRes.getRepSSN() != null && bookRes.getRepSSN() != "" )
				st.setInt(5, Integer.parseInt(bookRes.getRepSSN()));
			else 
				st.setNull(5, Types.NULL);
			st.setInt(6, 1111);
			boolean success = true;
			int result = st.executeUpdate();
			if (result <= 0)
				success = false;
			
			st = con.prepareStatement(query2);
			st.setInt(1,  resNo);
			st.setString(2, bookRes.getAirlineID());
			st.setInt(3, bookRes.getFlightNum1());
			st.setInt(4, 1);
			st.setString(5, bookRes.getTrip1Date());
			result = st.executeUpdate();
			if (result <= 0)
				success = false;
			
			st = con.prepareStatement(query3);
			st.setInt(1, resNo);
			st.setInt(2, 111);
			st.setInt(3, 1111);
			st.setString(4,  bookRes.getSeatNum());
			st.setString(5, bookRes.getSeatClass());
			st.setString(6,  bookRes.getMealPref());
			result = st.executeUpdate();
			if (result <= 0)
				success = false;

			st = con.prepareStatement(query3);
			st.setInt(1, resNo);
			st.setString(2, bookRes.getAirlineID());
			st.setInt(3, bookRes.getFlightNum2());
			st.setInt(4, 1);
			st.setString(5,  bookRes.getTrip2Date());
			result = st.executeUpdate();
			if (result <= 0)
				success = false;
			
			if (success)
				return "success";
			else
				return "failure";
		}
		catch(Exception e) {
			System.out.println(e);
		}
					
		return "failure";
		
	}
}
