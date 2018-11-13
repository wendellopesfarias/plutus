package com.plutus.model;

public class AssetPrice {
	
	private long asset_price_id;
	private double price;
	private double volume;
	private long asset_id;
	private long timeline_id;
	
	public long getAsset_price_id() {
		return asset_price_id;
	}
	public void setAsset_price_id(long asset_price_id) {
		this.asset_price_id = asset_price_id;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public long getAsset_id() {
		return asset_id;
	}
	public void setAsset_id(long asset_id) {
		this.asset_id = asset_id;
	}
	public long getTimeline_id() {
		return timeline_id;
	}
	public void setTimeline_id(long timeline_id) {
		this.timeline_id = timeline_id;
	}

}
