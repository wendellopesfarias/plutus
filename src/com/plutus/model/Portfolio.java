package com.plutus.model;

import java.util.Map;

public class Portfolio {
	
	private long portfolio_id;
	private String info;
	private Map<Long,AssetMix> assetMix;
	
	public long getPortfolio_id() {
		return portfolio_id;
	}
	public void setPortfolio_id(long portfolio_id) {
		this.portfolio_id = portfolio_id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Map<Long, AssetMix> getAssetMix() {
		return assetMix;
	}
	public void setAssetMix(Map<Long, AssetMix> assetMix) {
		this.assetMix = assetMix;
	}
	
}
