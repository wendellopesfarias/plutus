package com.plutus.dao;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.google.gson.Gson;
import com.plutus.controller.AssetController;
import com.plutus.converter.TimelineConverter;
import com.plutus.model.Asset;
import com.plutus.model.AssetPrice;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class AssetDAO {
	
	private static final String INSERT = "INSERT INTO asset (ticker,cusip,name_english,name_french) VALUES (?,?,?,?);";
	private static final String UPDATE = "UPDATE asset SET ticker=?,cusip=?,name_english=?,name_french=? WHERE asset_id=?";
//	private static final String SELECT_BY_INFO = "SELECT * FROM asset WHERE info ILIKE ?;";
	private static final String SELECT_BY_INFO = "SELECT * FROM asset WHERE ticker ILIKE ? OR name_english ILIKE ?;";
	private static final String SELECT_BY_TICKER = "SELECT * FROM asset WHERE ticker = ?;";
	private static final String SELECT_ALL = "SELECT * FROM asset;";
	private static final String SELECT_BY_ID = "SELECT * FROM asset WHERE asset_id = ?;";

	//ASSET PRICE
	private static final String SELECT_LAST_INFO_ASSET_PRICE="SELECT * FROM asset_price WHERE asset_id=? ORDER BY timeline_id DESC LIMIT 1;";
	/*
	 *
	 * 
SELECT(t2.price - t1.price)/t1.price AS sub1
	FROM asset_price AS t1 
	INNER JOIN asset_price AS t2 ON t1.asset_id=t2.asset_id 
WHERE t1.asset_id=3 
AND t1.asset_price_id=(SELECT t1.asset_price_id FROM (SELECT * FROM asset_price WHERE asset_id=3 ORDER BY timeline_id DESC LIMIT 2) AS t1 ORDER BY t1.timeline_id ASC LIMIT 1) 
AND t2.asset_price_id=(SELECT asset_price_id FROM asset_price WHERE asset_id=3 ORDER BY timeline_id DESC LIMIT 1);
	 */
	
	private static final String INSERT_PRICE = "INSERT INTO asset_price (price, asset_id, timeline_id, return_value, risk_value) VALUES (?, ?, ?, ?, ?, ?);";
	
	
	private static final String SELECT_PRICE = "SELECT * FROM asset_price WHERE asset_id=?";
	private static final String SELECT_SNAP_PRICE_ASSET = "SELECT * FROM asset_price WHERE asset_id=? AND timeline_id=?";
	private static final String SELECT_SNAP_TIME_PRICE = "SELECT * FROM asset_price WHERE timeline_id=?";
	
	public static void main(String [] args) {
		
		//Start get information about Asset
		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.YEAR, -4); // from 4 year ago
		
		//Search assets
		List<Asset> assetList = AssetController.selectAll();
		
		for (int i = 0; i < 20; i++) {
			
			Asset a = assetList.get(i);
			
			
			Stock s;
			try {
				
				s = YahooFinance.get(a.getTicker());
				//Update informations about this Asset
				if(s!=null) {
					
					a.setAsset_name(s.getName());

					if(AssetController.save(a)) {
						
						System.out.println("Save DONE!");
						
						// Start insert historical
						List<HistoricalQuote> lhq = s.getHistory(from, to, Interval.DAILY);
						
						for(HistoricalQuote hq: lhq){
							
							Calendar d = hq.getDate();
							long timelineId = TimeLineDAO.selectByDate(TimelineConverter.calendarToTimeline(d));
							double price = hq.getAdjClose().doubleValue();
							
							if(timelineId!=0) {			
								
								AssetPrice ap = new AssetPrice();
								ap.setAsset_id(a.getId());
								ap.setPrice(price);
								ap.setTimeline_id(timelineId);
								
								if(AssetController.insertAssetPrice(ap)) {
									System.out.println("Insert asset price DONE!");
								}else {
									System.out.println("***** Error: Asset price can not insert!!");
								}
								
							}else {
								System.out.println("***** Error: Timeline no found!!");
							}
						}
						
					} else {
						System.err.println("******* Error: Insert or Update asset!!!");
					}
				
				} else {
					System.err.println("********* Failure: Can not found Stock in Yahoo Finance!");
				}
				
			} catch (IOException e) {
				//e.printStackTrace();
				System.err.println("IO Exception Message: "+e.getMessage());
			}
		}
		
	}
	
	public boolean insertPrice(AssetPrice ap) {

		Connection con = null;
		PreparedStatement pst = null;
		boolean status = false;

		AssetPrice apb = AssetController.selectLastAssetPriceInfo(ap.getAsset_id());
		double price_before = 0.0;
		double return_value = 0.0;
		
		if(apb!=null) {
			price_before = apb.getPrice();
			return_value = (ap.getPrice()-price_before)/price_before;
		}
		

		try {
			con = Connector.getConnection();
			pst = con.prepareStatement(INSERT_PRICE);

			pst.setDouble(1, ap.getPrice());
			pst.setLong(2, ap.getAsset_id());
			pst.setLong(3, ap.getTimeline_id());
			pst.setDouble(4, return_value);

			if (pst.executeUpdate() == 1)
				status = true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Connector.closeConnection(con, pst, null);
		}

		return status;

	}
	
	public Asset selectByTicker(String ticker) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Asset obj= null;

		try {

			conn = Connector.getConnection();

			pst = conn.prepareStatement(SELECT_BY_TICKER);
			pst.setString(1, ticker);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				obj = new Asset();

				obj.setId(rs.getLong("asset_id"));
				obj.setTicker(rs.getString("ticker"));
				obj.setCusip(rs.getString("cusip"));
				obj.setAsset_name(rs.getString("name_english"));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Connector.closeConnection(conn, pst, rs);
		}
		return obj;
	}
	
	public Asset selectById(long asset_id) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		Asset obj= null;

		try {

			conn = Connector.getConnection();

			pst = conn.prepareStatement(SELECT_BY_ID);
			pst.setLong(1, asset_id);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				obj = new Asset();

				obj.setId(rs.getLong("asset_id"));
				obj.setTicker(rs.getString("ticker"));
				obj.setCusip(rs.getString("cusip"));
				obj.setAsset_name(rs.getString("name_english"));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Connector.closeConnection(conn, pst, rs);
		}
		return obj;
	}
	
	public static List<Asset> selectByInfo(String info) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		List<Asset> list = null;

		try {

			conn = Connector.getConnection();

			pst = conn.prepareStatement(SELECT_BY_INFO);
			pst.setString(1, info);
			pst.setString(2, info);

			rs = pst.executeQuery();
			
			list = new ArrayList<>();

			if (rs.next()) {
				
				do {
					Asset a = new Asset();
					a.setId(rs.getLong("asset_id"));
					a.setTicker(rs.getString("ticker"));
					a.setCusip(rs.getString("cusip"));
					a.setAsset_name(rs.getString("name_english"));

				}while(rs.next());
				
			
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {
			Connector.closeConnection(conn, pst, rs);
		}

		return list;
	
	}
	
	public List<Asset> selectAll() {
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		List<Asset> list = null;

		try {

			conn = Connector.getConnection();
			pst = conn.prepareStatement(SELECT_ALL);
			rs = pst.executeQuery();
			list = new ArrayList<>();
		

			if (rs.next()) {
				do {
					Asset a = new Asset();
					a.setId(rs.getLong("asset_id"));
					a.setTicker(rs.getString("ticker"));
					a.setCusip(rs.getString("cusip"));
					a.setAsset_name(rs.getString("name_english"));
					
					list.add(a);
					
				}while(rs.next());
			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {
			Connector.closeConnection(conn, pst, rs);
		}
		return list;
	}

	public long selectByInfo(Asset asset) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		long id=0;
		
		Field[] fields = asset.getClass().getDeclaredFields();
		
		 List<String> lines = new ArrayList<>(fields.length);

		    Arrays.stream(fields).forEach(field -> {
		        field.setAccessible(true);
		        try {
		        		System.out.println(field.getName() + " = " + field.get(this));
		            lines.add(field.getName() + " = " + field.get(field));
		        } catch (final IllegalAccessException e) {
		            lines.add(field.getName() + " > " + e.getClass().getSimpleName());
		        }
		    });
		
		Gson g = new Gson();
		
		String json = g.toJson(asset);
		
		System.out.println(json);
		String var = "%"+json+"%";

		try {

			conn = Connector.getConnection();

			pst = conn.prepareStatement(SELECT_BY_INFO);
			pst.setString(1, var);

			rs = pst.executeQuery();

			if (rs.next()) {

				id = rs.getLong("asset_id");

			}

		} catch (Exception e) {

			System.out.println(e.getMessage());

		} finally {
			Connector.closeConnection(conn, pst, rs);
		}

		return id;
	
	}

	public boolean insert(Asset asset) {

		Connection con = null;
		PreparedStatement pst = null;
		boolean status = false;

		try {
			con = Connector.getConnection();
			pst = con.prepareStatement(INSERT);

			pst.setString(1, asset.getTicker());
			pst.setString(2, asset.getCusip());
			pst.setString(3, asset.getAsset_name());
			pst.setString(4, asset.getAsset_name());

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
	
	public boolean update(Asset asset) {

		Connection con = null;
		PreparedStatement pst = null;
		boolean status = false;

		try {
			con = Connector.getConnection();
			pst = con.prepareStatement(UPDATE);

			pst.setString(1, asset.getTicker());
			pst.setString(2, asset.getCusip());
			pst.setString(3, asset.getAsset_name());
			pst.setString(4, asset.getAsset_name());
			pst.setLong(5, asset.getId());

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
	
	public static boolean insert(List<String> infoList) {

		Connection con = null;
		PreparedStatement pst = null;
		boolean status = false;

		try {
			con = Connector.getConnection();
			pst = con.prepareStatement(INSERT);
			
			int batch = 1000;
			int count = 0;
			
			for (String info : infoList) {
				pst.setString(1, info);
				pst.addBatch();
				
				if(++count % batch == 0) {
					pst.executeBatch();
				}
			}

			pst.executeBatch(); // execute batch and insert remaining records
			status = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Connector.closeConnection(con, pst, null);
		}

		return status;

	}

	/**
	 * ASSET PRICE
	 */
	public AssetPrice selectLastAssetPriceInfo(long asset_id) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		AssetPrice obj= null;

		try {

			conn = Connector.getConnection();

			pst = conn.prepareStatement(SELECT_LAST_INFO_ASSET_PRICE);
			pst.setLong(1, asset_id);
			rs = pst.executeQuery();
			
			if (rs.next()) {
				obj = new AssetPrice();

				obj.setAsset_price_id(rs.getLong("asset_price_id"));
				obj.setAsset_id(rs.getLong("asset_id"));
				obj.setPrice(rs.getDouble("price"));
				obj.setTimeline_id(rs.getLong("timeline_id"));

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			Connector.closeConnection(conn, pst, rs);
		}
		return obj;
	}

}
