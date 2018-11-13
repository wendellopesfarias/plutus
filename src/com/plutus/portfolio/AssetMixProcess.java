package com.plutus.portfolio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.plutus.model.Asset;
import com.plutus.model.AssetMix;

public class AssetMixProcess {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	/**
	 * Step 1
	 *  Search one list of assets that have 
	 *  correlation
	 * 
	 * @param a
	 * @return
	 */
	public List<Asset> searchSimilarAssetByCorrelation(Asset a){
		return new ArrayList<>();
	}
	/**
	 * Step 2
	 * Group assets by correlation
	 * @param a
	 * @return
	 */
	public Map<String,List<Asset>> groupAssetByGroupCorrelation(){
		return new HashMap<>();
	}
	/**
	 * 
	 * @param a
	 * @return
	 */
	
	public List<AssetMix> searchSimilarAssetMixByReturnAndRisk(Asset a){
		return new ArrayList<>();
	}
	
	

}
