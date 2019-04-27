package com.plutus.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.plutus.model.Asset;
import com.plutus.model.Bean;
import com.plutus.model.Person;


public class PersonDAO {
	
	private static Gson g = new Gson();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		BigDecimal bd = new BigDecimal("555.00003210");
//		for(int i = 0; i<20;i++) {
//			Person p = new Person();
//			p.setAge(i*2*3);
//			p.setBigDecimal(bd);
//			p.setDoubleNumber(i*5.890);
//			p.setDOUBLENumber(i*5.000001);
//			p.setName("Names "+ i);
//			p.setLast_name(i+"xxxx");
//			
//			System.out.println(insert(p));		
//		}
		
		List<Person> list = selectByInfo("wend");
	//	List<Person> list = selectByInfo("Eins","EIns");
		
		//list.addAll(selectByInfo("wend"));
		
		for (Person person : list) {
			System.out.println(person.getFirstName()+" age = "+person.getAge()+"");
		}
		//select();
	}
	
	public static boolean insert(Person p) {

		Connection con = null;
		PreparedStatement pst = null;
		boolean status = false;
		p.setId(UUID.randomUUID().toString());
		
		try {
			
			con = Connector.getConnection();
			
			pst = con.prepareStatement("INSERT INTO person (id, dataset) VALUES (?,?);");
			pst.setString(1, p.getId());
			pst.setString(1, g.toJson(p));
			
			if (pst.executeUpdate() == 1) {
				status = true;
				
				System.out.println("Status Insert: "+status);
				
//	            	long id = selectPerson(uuid).getPerson_id();
//	            //System.out.println("ID = "+id);
//	            	    
//	            p.setPerson_id(id);
//	                
//	            System.out.println("Status Update: "+update(p,id));
//	            
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.closeConnection(con, pst, null);
		}
		return status;
	}
	
	public static boolean update(Person p) {

		Connection con = null;
		PreparedStatement pst = null;
		boolean status = false;

		try {
			
			con = Connector.getConnection();
			
			pst = con.prepareStatement("UPDATE person SET dataset=? WHERE id=?;");
			pst.setString(1, g.toJson(p));
			pst.setLong(2, id);
			
			if (pst.executeUpdate() == 1) {
				status = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.closeConnection(con, pst, null);
		}
		return status;
	}

	public static Object select(String info) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Bean b = null;
		
		try {

			conn = Connector.getConnection();

			pst = conn.prepareStatement("SELECT * FROM "++" WHERE info ILIKE ?;");
			pst.setString(1, "%"+info+"%");
			
			rs = pst.executeQuery();
			

			if (rs.next()) {
				
				b = new Bean();
			
				b = g.fromJson(rs.getString("info"), Person.class);
				p.setPerson_id(rs.getLong("person_id"));
			
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {
			Connector.closeConnection(conn, pst, rs);
		}

		return p;
	
	}

	
	public static List<Person> selectByInfo(String info) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		List<Person> list = null;

		try {

			conn = Connector.getConnection();

			pst = conn.prepareStatement("SELECT * FROM person WHERE info ILIKE ?;");
			pst.setString(1, "%"+info+"%");
			
			rs = pst.executeQuery();
			
			list = new ArrayList<>();	

			if (rs.next()) {
				
				do {
					
					Person p = new Person();
					p = g.fromJson(rs.getString("info"), Person.class);
					p.setValue(rs.getString("info"));
					list.add(p);

				}while(rs.next());
				
			
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {
			Connector.closeConnection(conn, pst, rs);
		}

		return list;
	
	}
	
	public static List<Person> selectByInfo(String info, String replaceInfo) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		List<Person> list = null;

		try {

			conn = Connector.getConnection();

			pst = conn.prepareStatement("SELECT * FROM person WHERE info ILIKE ?;",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			pst.setString(1, "%"+info+"%");
			
			rs = pst.executeQuery();
			
			
			list = new ArrayList<>();	
			
//			int columnsNumber = rs.getMetaData().getColumnCount(); 
//			
//			for (int i = 0; i < columnsNumber; i++) {
//				System.out.print(rs.getMetaData().getColumnLabel(i+1)+" | ");
//			}
			
			

			if (rs.next()) {
				
				do {
					
//					for (int i = 0; i < columnsNumber; i++) {
//						System.out.print(rs.getObject(i+1)+"         | ");
//					}
//					
//					System.out.println();
					
					Person p = new Person();
					p.setPerson_id(rs.getLong("person_id"));
					p.setValue(rs.getString("info"));
					
					list.add(p);
					
					if(rs.getString("info").contains(info)) {
						String s = rs.getString("info");
						System.out.println("Old String: "+info+" New String: "+replaceInfo);
						String sr = s.replace(info, replaceInfo);
						System.out.println("Old Json: "+s+" New Json: "+sr);
						rs.updateString("info", sr);
						rs.updateRow();
						System.out.println("Record updated!!!");
					}

				}while(rs.next());
				
			
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {
			Connector.closeConnection(conn, pst, rs);
		}

		return list;
	
	}


	private static void select() {
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = Connector.getConnection();
			pst = con.prepareStatement("SELECT * FROM person;");
			rs = pst.executeQuery();

			int numberCols = rs.getMetaData().getColumnCount();
				
			for(int k = 0; k < numberCols;k++) {
				System.out.println(rs.getMetaData().getColumnName(k+1));
			}
			
			if(rs.next()) {
				do {
					for(int k = 0; k < numberCols;k++) {
						System.out.println(rs.getString(rs.getMetaData().getColumnName(k+1)));
					}
				}while(rs.next());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	

}
