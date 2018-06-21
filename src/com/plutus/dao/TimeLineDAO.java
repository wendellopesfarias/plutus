package com.plutus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;


public class TimeLineDAO {
	

	private static final String INSERT = "INSERT INTO timeline (date_stamp,year,month,day) VALUES (?,?,?,?);";
	
	public static void main(String [] args) {
		System.out.println("Start");
		
		for(int i =0; i<10;i++) {
			insert(new Timestamp(System.currentTimeMillis()));
		}
		
		System.out.println("End");

	}

	public static boolean insert(Timestamp ts) {

		Connection con = null;
		PreparedStatement pst = null;
		boolean status = false;
		
		

		try {
			con = Connector.getConnection();
//			pst = con.prepareStatement(INSERT);
//
//			pst.setTimestamp(1, ts);
//			pst.setInt(2, ts.getYear());
//			pst.setInt(3, ts.getMonth());
//			pst.setInt(4, ts.getDay());

			if (pst.executeUpdate() == 1)
				status = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;

	}

}
