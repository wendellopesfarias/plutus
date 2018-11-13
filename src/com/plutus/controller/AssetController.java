package com.plutus.controller;

import java.util.List;

import com.plutus.model.Asset;
import com.plutus.model.AssetPrice;
import com.plutus.dao.AssetDAO;

public class AssetController {
	
	private static AssetDAO dao = new AssetDAO();
	
	public static boolean save(Asset obj) {
		if(obj.getId()!=0) {
			return dao.insert(obj);
		}else {
			return dao.update(obj);
		}
	}
	
	public static Asset selectByTicker(String ticker) {
		return dao.selectByTicker(ticker);
	}
	
	public static List<Asset> selectAll(){
		return dao.selectAll();
	}
	
	//Asset Price
	public static boolean insertAssetPrice(AssetPrice ap) {
		return dao.insertPrice(ap);
	}
	
	public static AssetPrice selectLastAssetPriceInfo(long asset_id) {
		return dao.selectLastAssetPriceInfo(asset_id);
	}
	
	

}
