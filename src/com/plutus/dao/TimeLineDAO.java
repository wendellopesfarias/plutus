package com.plutus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import com.plutus.model.Timeline;


public class TimeLineDAO {
	

	private static final String INSERT = "INSERT INTO timeline (date_stamp,year,month,day,weekday) VALUES (?,?,?,?,?);";
	private static final String SELECT_BY_DATE = "SELECT * FROM timeline WHERE year=? AND month=? AND day=?;";
	
	public static void main(String [] args) {
		System.out.println("Start");
		

		
//		Calendar cal = Calendar.getInstance();
//		int years = 10 * 365 + 1;
//		
//		cal.add(Calendar.YEAR, -5);
//		
//		for(int i = 1; i < years; i++) {
//				
//			cal.add(Calendar.DAY_OF_MONTH, 1);
//	
//			int year = cal.get(Calendar.YEAR);
//			int month = cal.get(Calendar.MONTH)+1;
//			int day = cal.get(Calendar.DAY_OF_MONTH);
//			int weekday = cal.get(Calendar.DAY_OF_WEEK);
//			
//			System.out.println(day+"/"+month+"/"+year);
//			insert(new Timestamp(cal.getTimeInMillis()), year, month, day,weekday);
//		}
		
//		
//		for(int i =0; i<10;i++) {
//			insert(new Timestamp(System.currentTimeMillis()));
//		}
//		
		
		System.out.println(selectByDate(2021, 12, 20));
		
		System.out.println("End");

	}
	
	
	public static long selectByDate(int y, int m, int d) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		long id=0;

		try {

			conn = Connector.getConnection();

			pst = conn.prepareStatement(SELECT_BY_DATE);
			pst.setInt(1, y);
			pst.setInt(2, m);
			pst.setInt(3, d);

			rs = pst.executeQuery();

			if (rs.next()) {

				id = rs.getLong("timeline_id");

			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {
			Connector.closeConnection(conn, pst, rs);
		}

		return id;
	
	}
	public static long selectByDate(Timeline timeline) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		long id=0;

		try {

			conn = Connector.getConnection();

			pst = conn.prepareStatement(SELECT_BY_DATE);
			pst.setInt(1, timeline.getYear());
			pst.setInt(2, timeline.getMonth());
			pst.setInt(3, timeline.getDay());

			rs = pst.executeQuery();

			if (rs.next()) {

				id = rs.getLong("timeline_id");

			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {
			Connector.closeConnection(conn, pst, rs);
		}

		return id;
	
	}

	public static boolean insert(Timestamp ts, int year, int month, int day, int weekday) {

		Connection con = null;
		PreparedStatement pst = null;
		boolean status = false;

		try {
			con = Connector.getConnection();
			pst = con.prepareStatement(INSERT);

			pst.setTimestamp(1, ts);
			pst.setInt(2, year);
			pst.setInt(3, month);
			pst.setInt(4, day);
			pst.setInt(5, weekday);

			if (pst.executeUpdate() == 1)
				status = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.closeConnection(con, pst, null);
		}

		return status;

	}

}
