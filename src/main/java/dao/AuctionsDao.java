package dao;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

import model.Auctions;

public class AuctionsDao {
	
	public List<Auctions> getLatestBid(int AccountNo, String AirlineID, int FlightNo, String SeatClass) {

		/*
		 * This method fetches the latest auction details and returns it
		 * using method parameters given, find the latest bid
		 * The students code to fetch data from the database will be written here
		 * The Auctions record is required to be encapsulated as a "Auctions" class object
		 */
		List<Auctions> auctions = new ArrayList<Auctions>();
		/*Sample data begins*/		
		String query = "SELECT * FROM Auctions WHERE AccountNo = ? AND AirlineID = ? AND FlightNo = ? AND Class = ?"
				+ "	ORDER BY `Date` DESC LIMIT 0,1";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/pwchristense", "pwchristense", "112123806");
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, AccountNo);
			st.setString(2, AirlineID);
			st.setInt(3, FlightNo);
			st.setString(4, SeatClass);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Auctions auction = new Auctions();
				auction.setAccountNo(rs.getInt("AccountNo"));
				auction.setAirlineID(rs.getString("AirlineID"));
				auction.setFlightNo(rs.getInt("FlightNo"));
				auction.setSeatClass(rs.getString("Class"));
				auction.setAccepted(true);
				auction.setDate(rs.getString("Date"));
				auction.setNYOP(rs.getDouble("NYOP"));
				auctions.add(auction);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
		
		return auctions;
	}
	
	public List<Auctions> getAllBids(int AccountNo, String AirlineID, int FlightNo, String SeatClass) {
		
		/*
		 * The students code to fetch data from the database will be written here
		 * Query to get all bids given the parameters
		 */
		
		List<Auctions> auctions = new ArrayList<Auctions>();
			
		/*Sample data begins*/
		String query = "SELECT * FROM Auctions WHERE AccountNo = ? AND AirlineID = ? AND FlightNo = ? AND Class = ?"
				+ "	ORDER BY `Date` DESC";
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://mysql4.cs.stonybrook.edu:3306/pwchristense", "pwchristense", "112123806");
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, AccountNo);
			st.setString(2, AirlineID);
			st.setInt(3, FlightNo);
			st.setString(4, SeatClass);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Auctions auction = new Auctions();
				auction.setAccountNo(rs.getInt("AccountNo"));
				auction.setAirlineID(rs.getString("AirlineID"));
				auction.setFlightNo(rs.getInt("FlightNo"));
				auction.setSeatClass(rs.getString("Class"));
				auction.setAccepted(true);
				auction.setDate(rs.getString("Date"));
				auction.setNYOP(rs.getDouble("NYOP"));
				auctions.add(auction);
			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		/*Sample data ends*/
						
		return auctions;
		
	}
}
