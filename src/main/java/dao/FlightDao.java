package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.Flight;

public class FlightDao {
	
	public List<Flight> getAllFlights() {
		/* Get list of all flights, code goes here
		 */
		List<Flight> flights = new ArrayList<Flight>();
		
//		for (int i = 0; i < 5; i++) {
//			Flight flight = new Flight();
//			flight.setAirlineID("AA");
//			flight.setFlightNo(111);
//			flight.setNumOfSeats(100);
//			flight.setDaysOperating("1010100");
//			flight.setMinLengthOfStay(1);
//			flight.setMaxLengthOfStay(30);
//			flights.add(flight);			
//		}
//		/*Sample data ends*/
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from flight");
			while(rs.next()) {
				Flight flight = new Flight();
				flight.setAirlineID(rs.getString("AirlineID"));
				flight.setFlightNo(rs.getInt("FlightNo"));
				flight.setNumOfSeats(rs.getInt("NoOfSeats"));
				flight.setDaysOperating(rs.getString("DaysOperating"));
				flight.setMinLengthOfStay(rs.getInt("MinLengthOfStay"));
				flight.setMaxLengthOfStay(rs.getInt("MaxLengthOfStay"));
				flights.add(flight);	
			}
		}catch (Exception e) {
			System.out.println(e);
		}
				
		return flights;
	}
	public List<Flight> mostActiveFlights() {
		
		/* Get list of most active flights, code goes here
		 */
		
		List<Flight> flights = new ArrayList<Flight>();
		
//		for (int i = 0; i < 5; i++) {
//			Flight flight = new Flight();
//			flight.setAirlineID("AA");
//			flight.setFlightNo(111);
//			flight.setNumReservations(30);
//			flights.add(flight);			
//		}
		/*Sample data ends*/
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("CREATE VIEW FlightReservation(AirlineID, FlightNo, ResrCount) AS SELECT I.AirlineID, I.FlightNo, COUNT(DISTINCT I.ResrNo) FROM Includes I GROUP BY I.AirlineID, I.FlightNo");
			while(rs.next()) {
				Flight flight = new Flight();
				flight.setAirlineID(rs.getString("AirlineID"));
				flight.setFlightNo(rs.getInt("FlightNo"));
				flight.setNumReservations(rs.getInt("ResrCount"));
				flights.add(flight);	
			}
		}catch (Exception e) {
			System.out.println(e);
		}
				
		return flights;
	}
	
	public List<Flight> getFlightsForAirport(String airport) {
		
		/*
		 * Code here to get flights given an airport
		 */
		
		List<Flight> flights = new ArrayList<Flight>();
		
//		for (int i = 0; i < 5; i++) {
//			Flight flight = new Flight();
//			flight.setAirlineID("AA");
//			flight.setFlightNo(111);
//			flight.setNumOfSeats(100);
//			flight.setDaysOperating("1010100");
//			flight.setMinLengthOfStay(1);
//			flight.setMaxLengthOfStay(30);
//			flights.add(flight);			
//		}
//		SELECT DISTINCT F.* FROM Flight F, Leg L, Airport A WHERE F.AirlineID = L.AirlineID AND F.FlightNo = L.FlightNo AND (L.DepAirportId = A.Id OR L.ArrAirportId = A.Id) AND A.Name = 'LaGuardia'
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT DISTINCT F.* FROM Flight F, Leg L, Airport A WHERE F.AirlineID = L.AirlineID AND F.FlightNo = L.FlightNo AND (L.DepAirportId = A.Id OR L.ArrAirportId = A.Id) AND A.Name = " + airport);
			while(rs.next()) {
				Flight flight = new Flight();
				flight.setAirlineID(rs.getString("AirlineID"));
				flight.setFlightNo(rs.getInt("FlightNo"));
				flight.setNumOfSeats(rs.getInt("ResrCount"));
				flight.setDaysOperating(rs.getString("DaysOperating"));
				flight.setMinLengthOfStay(rs.getInt("MinLengthOfStay"));
				flight.setMaxLengthOfStay(rs.getInt("MaxLengthOfStay"));
				flights.add(flight);	
			}
		}catch (Exception e) {
			System.out.println(e);
		}
				
		return flights;
	}
	public List<Flight> getOnTimeFlights() {
		
		/*
		 * Code here to get on time flights
		 */
		
		List<Flight> flights = new ArrayList<Flight>();
//		
//		for (int i = 0; i < 5; i++) {
//			Flight flight = new Flight();
//			flight.setAirlineID("AA");
//			flight.setFlightNo(111);
//			flight.setNumOfSeats(100);
//			flight.setDaysOperating("1010100");
//			flight.setMinLengthOfStay(1);
//			flight.setMaxLengthOfStay(30);
//			flights.add(flight);			
//		}
//		SELECT * FROM Flight F WHERE NOT EXISTS ( SELECT * FROM Leg L WHERE F.AirlineID = L.AirlineID AND F.FlightNo = L.FlightNo AND (ActualArrTime <= ArrTime OR ActualDepTime <= DepTime))
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Flight F WHERE NOT EXISTS ( SELECT * FROM Leg L WHERE F.AirlineID = L.AirlineID AND F.FlightNo = L.FlightNo AND (ActualArrTime > ArrTime OR ActualDepTime > DepTime))");
			while(rs.next()) {
				Flight flight = new Flight();
				flight.setAirlineID(rs.getString("AirlineID"));
				flight.setFlightNo(rs.getInt("FlightNo"));
				flight.setNumOfSeats(rs.getInt("ResrCount"));
				flight.setDaysOperating(rs.getString("DaysOperating"));
				flight.setMinLengthOfStay(rs.getInt("MinLengthOfStay"));
				flight.setMaxLengthOfStay(rs.getInt("MaxLengthOfStay"));
				flights.add(flight);	
			}
		}catch (Exception e) {
			System.out.println(e);
		}
				
		return flights;
	}
	public List<Flight> getDelayedFlights() {
		
		/*
		 * Code here to get delayed flights
		 */
		
		List<Flight> flights = new ArrayList<Flight>();
		
//		for (int i = 0; i < 5; i++) {
//			Flight flight = new Flight();
//			flight.setAirlineID("AA");
//			flight.setFlightNo(111);
//			flight.setNumOfSeats(100);
//			flight.setDaysOperating("1010100");
//			flight.setMinLengthOfStay(1);
//			flight.setMaxLengthOfStay(30);
//			flights.add(flight);			
//		}		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM Flight F WHERE EXISTS ( SELECT * FROM Leg L WHERE F.AirlineID = L.AirlineID AND F.FlightNo = L.FlightNo AND (ActualArrTime > ArrTime OR ActualDepTime > DepTime))");
			while(rs.next()) {
				Flight flight = new Flight();
				flight.setAirlineID(rs.getString("AirlineID"));
				flight.setFlightNo(rs.getInt("FlightNo"));
				flight.setNumReservations(rs.getInt("ResrCount"));
				flight.setDaysOperating(rs.getString("DaysOperating"));
				flight.setMinLengthOfStay(rs.getInt("MinLengthOfStay"));
				flight.setMaxLengthOfStay(rs.getInt("MaxLengthOfStay"));
				flights.add(flight);	
			}
		}catch (Exception e) {
			System.out.println(e);
		}
				
		return flights;
	}
	
	public List<Flight> getCustomerFlightSuggestions(int accountNo) {
		
		/* Get list of suggested flights depending on customer's accountNo passed
		 */
		
		List<Flight> flights = new ArrayList<Flight>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from Flight Reservation FR where not exists (select * from "
					+ "Reservation R, Includes I where R.ResrNo = I.ResrNo and FR.AirlineID = I.AirlineID "
					+ "AND FR.FlightNo = I.FlightNo AND R.AccountNo = " + accountNo + ") ORDER BY FR.ResrCount DESC");
			while(rs.next()) {
				Flight flight = new Flight();
				flight.setAirlineID(rs.getString("AirlineID"));
				flight.setFlightNo(rs.getInt("FlightNo"));
				flight.setNumReservations(rs.getInt("ResrCount"));
				flights.add(flight);	
			}
		}catch (Exception e) {
			System.out.println(e);
		}
				
		return flights;
	}
	public List<Flight> getBestSellingFlights() {
		
		/* Get list of best selling flights
		 */
		
		List<Flight> flights = new ArrayList<Flight>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "root", "root");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("create view FlightReservation(AirlineID, FlightNo, ResrCount) as select AirlineID, FlightNo, count(distinct ResrNo) from Includes I group by AirlineID, FlightNo");
			while(rs.next()) {
				Flight flight = new Flight();
				flight.setAirlineID(rs.getString("AirlineID"));
				flight.setFlightNo(rs.getInt("FlightNo"));
				flight.setNumReservations(rs.getInt("ResrCount"));
				flights.add(flight);	
			}
		}catch (Exception e) {
			System.out.println(e);
		}
				
		return flights;
	}
}