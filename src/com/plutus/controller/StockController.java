package com.plutus.controller;

import java.io.IOException;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

public class StockController {

	public static void main(String[] args) {
		
		try {
			Stock s = YahooFinance.get("PXT.TO");
			Stock s2 = YahooFinance.get("AAPL");
			
			StockStats ss = s.getStats();
			System.out.println(ss.getMarketCap());

			
			
			StockQuote sq = s2.getQuote();
			System.out.println(sq.getAvgVolume()+""+ sq.getChange()+" "+sq.getTimeZone().getID());
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

	}

}
