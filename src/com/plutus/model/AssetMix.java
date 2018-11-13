package com.plutus.model;

import java.util.Map;

/**
 * to spread risk and minimize loss
 * Portfolio may contain the mix of preference equity shares, bonds and cash.
 * The percentage of the mix depends upon the risk tolerance and investment limit of the investor
 * 
 * @author wendellopes
 *
 */
public class AssetMix {
	
	private long id;
	private long portfolio_id;
	private long asset_id;
	private long timeline_id;
	private double shares;
	private double value_risk;
	private double value_return;
	// constraints variables
	private double constraint_min;
	private double constraints_target;
	private double constraints_max;
	
	// simulation and optimization variables;
	private Map<Long, AssetMix> simulations;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getPortfolio_id() {
		return portfolio_id;
	}
	public void setPortfolio_id(long portfolio_id) {
		this.portfolio_id = portfolio_id;
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
	public double getShares() {
		return shares;
	}
	public void setShares(double shares) {
		this.shares = shares;
	}
	
	public double getConstraint_min() {
		return constraint_min;
	}
	public void setConstraint_min(double constraint_min) {
		this.constraint_min = constraint_min;
	}
	public double getConstraints_target() {
		return constraints_target;
	}
	public void setConstraints_target(double constraints_target) {
		this.constraints_target = constraints_target;
	}
	public double getConstraints_max() {
		return constraints_max;
	}
	public void setConstraints_max(double constraints_max) {
		this.constraints_max = constraints_max;
	}
	
	public double getValue_risk() {
		return value_risk;
	}
	public void setValue_risk(double value_risk) {
		this.value_risk = value_risk;
	}
	public double getValue_return() {
		return value_return;
	}
	public void setValue_return(double value_return) {
		this.value_return = value_return;
	}
	public Map<Long, AssetMix> getSimulations() {
		return simulations;
	}
	public void setSimulations(Map<Long, AssetMix> simulations) {
		this.simulations = simulations;
	}

}
