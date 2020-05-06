package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Itinerary;
import model.SalesReport;

public class ItineraryDao {
	
	public List<Itinerary> getItineraryForReservation(int resrNo) {
			/*
			 * Code to fetch itinerary from resrNo goes here
			 */
		
			List<Itinerary> its = new ArrayList<Itinerary>();
			try {
				Class.forName("com.mysql.jdbc.Driver" );
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo","root","root");
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from Itinerary where ResrNo =" + resrNo);
				while(rs.next()) {
					Itinerary itinerary = new Itinerary();
					itinerary.setAirlineID(rs.getString("AirlineID"));
					itinerary.setArrival(rs.getString("Arrival"));
					itinerary.setArrTime(rs.getString("ArrTime"));
					itinerary.setDeparture(rs.getString("Departure"));
					itinerary.setDepTime(rs.getString("DepTime"));
					itinerary.setFlightNo(rs.getInt("FlightNo"));
					itinerary.setResrNo(rs.getInt("ReserNo"));
					its.add(itinerary);
				}
			} catch(Exception e) {
				System.out.println(e);
			}
			
			/*Sample data ends*/
			return its;
		}
}
