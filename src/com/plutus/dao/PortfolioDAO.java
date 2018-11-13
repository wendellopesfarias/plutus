package com.plutus.dao;

public class PortfolioDAO {
	
	private static final String PORTFOLIO_DATA = "SELECT a.timeline_id AS allocation_time, pr.timeline_id AS price_time, tl.date_stamp, p.info,a.shares, s.ticker, pr.price, pr.price*a.shares AS valuation " + 
			"	FROM portfolio AS p " + 
			"	INNER JOIN allocation AS a ON p.portfolio_id=a.portfolio_id " + 
			"	INNER JOIN asset AS s ON s.asset_id=a.asset_id " + 
			"	INNER JOIN asset_price AS pr ON pr.asset_id=s.asset_id " + 
			"	INNER JOIN timeline AS tl ON tl.timeline_id=a.timeline_id " + 
			"	WHERE p.portfolio_id IN (?) " + 
			"	AND a.timeline_id=? " + 
			"	AND pr.timeline_id=a.timeline_id ORDER BY p.portfolio_id;";

	
	
}
