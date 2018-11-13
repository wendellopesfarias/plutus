package com.plutus.datacleaning;

import java.util.List;
import java.util.ArrayList;

import com.plutus.controller.AssetController;
import com.plutus.model.Asset;

/**
 * Verification to enrich data
 * Validate the data against internal and external data sources to append value adding info. 
 * I.e., business contacts can be validated against yellow pages to verify their current phone number and addresses. 
 * Same goes for various other fields including credit ratings, geo-coords, key contacts, employee size, profit, revenue, time zones etc., 
 * can be fetched for each company.

 * @author wendellopes
 *
 */
public class DataVerification {
	
	
	public static void main(String [] args) {
		
		List<Asset> listAllAssets = AssetController.selectAll();
		System.out.println(listAllAssets.size());
		int i = 1;
		for (Asset asset : listAllAssets) {
			if(asset.getTicker().endsWith(".TO")||asset.getTicker().endsWith(".to")) {
				System.out.println(++i+") "+asset.getTicker());
			}
			
		}
	}
	
	public static List<Asset> listAssetToUpdate(List<Asset> listAllAssets){
		List<Asset> list = new ArrayList<>();
		
		return list;
	}
	
}
