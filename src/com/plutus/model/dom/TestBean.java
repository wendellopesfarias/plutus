package com.plutus.model.dom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.plutus.dao.Connector;


public class TestBean {

	public static void main(String[] args) {
		
		Map<String,String> map = new HashMap<>();
		
		map.put("nome", "java.lang.String");
		map.put("dataNascimento", "java.util.Date");
		map.put("filhos", "java.lang.Integer");
		map.put("dataNascimento-ts", "java.sql.Timestamp");
		
		DynaBean bean = DynaBeanFactory.newInstance(map);
		

//		
//	//	DynaBean bean = DynaBeanFactory.newInstance("/Users/wendellopes/eclipse-workspace/plutus/src/com/plutus/model/dom/pessoa.properties");
//		
		Timestamp ts = new Timestamp(new Date().getTime());
		
		bean.setAttribute("nome", "Daniel");
		bean.setAttribute("filhos", 6);
		
		//bean.setAttribute("dataNascimento", ts);
		//bean.setAttribute("dataNascimento-ts", ts);
		bean.setAttribute("dataNascimento", new Date());
		
		Gson g = new Gson();
		
		String json = g.toJson(bean,DynaBean.class);
		System.out.println(json);
		
		
		System.out.println("Name: " + bean.getAttribute("nome") + " -> filhos: " + bean.getAttribute("filhos")
				+ " Date: " + bean.getAttribute("dataNascimento")+" Date-ts: "+bean.getAttribute("dataNascimento-ts"));
		
//		String tb = "person";
//		String table = tb.toLowerCase();
//		
//		List<DynaBean> list = select(table);
//		List<String> columns = listColumns(table);
//		
//		for (String colName : columns) {
//			System.out.print(colName+", ");
//		}
//
//		System.out.println();
//		
//
//		for (DynaBean dynaBean : list) {
//			for (String colName : columns) {
//				System.out.print(dynaBean.getAttribute(colName)+", ");
//			}
//			System.out.println();
//		}


	}
	
	public static List<String> listColumns(String table){
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<String> list = null;
	
		try {
			
			con = Connector.getConnection();
			
			pst = con.prepareStatement("SELECT * FROM "+table+";");
			rs = pst.executeQuery();
			
			int numberCols = rs.getMetaData().getColumnCount();
			list = new ArrayList<>();
			
			for(int k = 0; k < numberCols;k++) {
				list.add(rs.getMetaData().getColumnName(k+1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.closeConnection(con, pst, rs);
		}
		return list;
		
	}
	
	public static List<DynaBean> select(String table) {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<DynaBean> list = null;
	
		try {
			
			con = Connector.getConnection();
			
			pst = con.prepareStatement("SELECT * FROM "+table+" LIMIT 20;");
			rs = pst.executeQuery();
			
			Map<String,String> map = new HashMap<>();
			int numberCols = rs.getMetaData().getColumnCount();
			
			for(int k = 0; k < numberCols;k++) {
				//System.out.println(rs.getMetaData().getColumnName(k+1)+" -> "+rs.getMetaData().getColumnClassName(k+1));
				map.put(rs.getMetaData().getColumnName(k+1), rs.getMetaData().getColumnClassName(k+1));
			}
			
			list = new ArrayList<>();
			DynaBean bean = null;

			if (rs.next()) {
				
				do {
					bean = DynaBeanFactory.newInstance(table,map);
					for (String key : map.keySet()) {
						//System.out.println(key+"   "+ rs.getObject(key));
						Object value = rs.getObject(key);
						if(value==null) {
							value=1.01;
						}
						bean.setAttribute(key, value);
					}
					
					list.add(bean);
				}while(rs.next());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.closeConnection(con, pst, rs);
		}
		return list;
	}
	
	public static List<DynaBean> selectByInfo(String table, String info, String... columns) {

		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<DynaBean> list = null;
		
		

		String query = columns[0]+"=? OR"; 
		
		if(columns.length > 1) {
			for (int i = 1; i < columns.length-1;i++) {
				query+=" "+columns[i]+"=? OR";
			}
			
		}
		
		
		
		try {
			
			con = Connector.getConnection();
			
			pst = con.prepareStatement("SELECT * FROM "+table+" WHERE "+query+";");
			rs = pst.executeQuery();
			
			Map<String,String> map = new HashMap<>();
			int numberCols = rs.getMetaData().getColumnCount();
			
			for(int k = 0; k < numberCols;k++) {
				//System.out.println(rs.getMetaData().getColumnName(k+1)+" -> "+rs.getMetaData().getColumnClassName(k+1));
				map.put(rs.getMetaData().getColumnName(k+1), rs.getMetaData().getColumnClassName(k+1));
			}
			
			list = new ArrayList<>();
			DynaBean bean = null;

			if (rs.next()) {
				
				do {
					bean = DynaBeanFactory.newInstance(map);
					for (String key : map.keySet()) {
						//System.out.println(key+"   "+ rs.getObject(key));
						Object value = rs.getObject(key);
						if(value==null) {
							value="";
						}
						bean.setAttribute(key, value);
					}
					
					list.add(bean);
				}while(rs.next());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.closeConnection(con, pst, rs);
		}
		return list;
	}

	
	

}
