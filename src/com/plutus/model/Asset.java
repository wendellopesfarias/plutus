package com.plutus.model;

import java.util.Map;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;

public class Asset extends Bean{
	
	private String ticker;
	private String cusip;
	private String asset_name;
	private Map<Long,AssetPrice> map_valuation; // key corresponde timeline_id in table timeline. This map corresponde table asset_price
	

	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	public String getCusip() {
		return cusip;
	}
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	
	public String getAsset_name() {
		return asset_name;
	}
	public void setAsset_name(String asset_name) {
		this.asset_name = asset_name;
	}
	public Map<Long, AssetPrice> getMap_valuation() {
	
		return map_valuation;
	}
	public void setMap_valuation(Map<Long, AssetPrice> map_valuation) {
		this.map_valuation = map_valuation;
	}

}
