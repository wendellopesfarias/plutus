package com.plutus.analysis;

import java.util.Map;
import java.util.TreeMap;
import java.text.DecimalFormat;
import java.util.HashMap;

public class ConfusionMatrix {

	public static void main(String[] args) {
		DecimalFormat df = new DecimalFormat("0.00");
		
		double money = 50.0;
		
		double risk = 0.15;
		double ret = 0.01;
		double priceStock = 10.0;
		
		Map<Integer,Double> shares = new TreeMap<>(); // buy and sell
		
		Map<Integer,Double> stock = new TreeMap<>();
		for(int i = 1; i <= 100; i++) {
			priceStock = priceStock + priceStock*ret; 
			stock.put(i, randomMinMax(priceStock - priceStock*risk, priceStock + priceStock*risk));
			
			if(i < 100) {
				double rt = stock.get(i)/stock.get(i+1);
				System.out.println(rt);
//				if(rt < 0.0) {
//					shares.put(i, money/priceStock); // buy
//				}else {
//					money = money + shares.get(i) * priceStock; //sell
//				}
				
				
			}
		}
		stock.forEach((x,y)-> System.out.println(x+ " : "+ df.format(y)));
		
		System.out.println(money);
		
		Map<Integer,Double> tpMap = new HashMap<>();//correctly retrieved
		Map<Integer,Double> tnMap = new HashMap<>();//correctly not retrieved
		Map<Integer,Double> fpMap = new HashMap<>();//incorrectly retrieved
		Map<Integer,Double> fnMap = new HashMap<>();//incorrectly not retrieved
		
		
		double TP = (double) tpMap.size();
		double TN = (double) tnMap.size();
		double FP = (double) fpMap.size();
		double FN = (double) fnMap.size();
		
		double TPR = TP /(TP + FN); //sensitivity, recall, hit rate, or true positive rate (TPR)
		double TNR = TN /(TN + FP); //specificity, selectivity or true negative rate (TNR)
		double PPV = TP/(TP + FP);//precision or positive predictive value (PPV)
	}
	
	private static double randomMinMax(double min,double max) {
		return min + Math.random() * (max - min);
	}

}
