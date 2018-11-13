package com.plutus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.plutus.model.Bean;

public class DAO {

	private static Gson g = new Gson();

	public static boolean insert(Object o) {

		Connection con = null;
		PreparedStatement pst = null;
		String table = null;
		boolean status = false;

		Bean b = (Bean) o;
		b.setId(UUID.randomUUID().toString());
		table = b.getType().toLowerCase();

		try {

			con = Connector.getConnection();
			pst = con.prepareStatement("INSERT INTO " + table + " (id, dataset) VALUES (?,?);");
			pst.setString(1, b.getId());
			pst.setString(2, g.toJson(b));

			if (pst.executeUpdate() == 1)
				status = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.closeConnection(con, pst, null);
		}
		return status;
	}

	public static boolean update(Object o) {

		Connection con = null;
		PreparedStatement pst = null;
		boolean status = false;
		String table = null;
		Bean b = (Bean) o;
		table = b.getType().toLowerCase();

		try {

			con = Connector.getConnection();

			pst = con.prepareStatement("UPDATE " + table + " SET dataset=? WHERE id=?;");
			pst.setString(1, g.toJson(o));
			pst.setString(2, b.getId());

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
	
	public static Bean selectById(Object o, String id) {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Bean b = null;

		try {

			conn = Connector.getConnection();

			pst = conn.prepareStatement(
					"SELECT * FROM " + o.getClass().getSimpleName().toLowerCase() + " WHERE id = ?;");
			pst.setString(1, id);
			rs = pst.executeQuery();

			if (rs.next()) {

				b = new Bean();
				b = (Bean) g.fromJson(rs.getString("dataset"), o.getClass());
				b.setId(rs.getString("id"));

			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {
			Connector.closeConnection(conn, pst, rs);
		}

		return b;

	}

	public static Bean select(Object o, String info) {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Bean b = null;

		try {

			conn = Connector.getConnection();

			pst = conn.prepareStatement(
					"SELECT * FROM " + o.getClass().getSimpleName().toLowerCase() + " WHERE dataset ILIKE ?;");
			pst.setString(1, "%" + info + "%");
			rs = pst.executeQuery();

			if (rs.next()) {

				b = new Bean();
				b = (Bean) g.fromJson(rs.getString("dataset"), o.getClass());
				b.setId(rs.getString("id"));

			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {
			Connector.closeConnection(conn, pst, rs);
		}

		return b;

	}
	
	public static List<Bean> selectList(Object o, String info) {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Bean> list = null;

		try {

			conn = Connector.getConnection();

			pst = conn.prepareStatement(
					"SELECT * FROM " + o.getClass().getSimpleName().toLowerCase() + " WHERE dataset ILIKE ?;");
			pst.setString(1, "%" + info + "%");
			rs = pst.executeQuery();

			if (rs.next()) {
				list = new ArrayList<>();
				
				do {

					Bean b = new Bean();
					b = (Bean) g.fromJson(rs.getString("dataset"), o.getClass());
					b.setId(rs.getString("id"));
					list.add(b);
				}while(rs.next());
				
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {
			Connector.closeConnection(conn, pst, rs);
		}

		return list;

	}


	public static String getId(String json) {
		String id = null;
		JsonParser parser = new JsonParser();
		JsonElement jsonTree = parser.parse(json);
		if (jsonTree.isJsonObject()) {
			JsonObject jsonObject = jsonTree.getAsJsonObject();
			JsonElement je = jsonObject.get("id");
			id = je.getAsString();
		}
		return id;
	}

}
