package com.plutus.dao;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.quotes.fx.FxQuote;
import yahoofinance.quotes.fx.FxSymbols;

public class TestDAO {

	public static void main(String[] args) {
		//Start get information about Asset
				Calendar from = Calendar.getInstance();
				Calendar to = Calendar.getInstance();
				from.add(Calendar.YEAR, -4); // from 4 year ago
		
		FxQuote cadusd;
		Stock s;
	
		try {
			cadusd = YahooFinance.getFx(FxSymbols.CADUSD);
			s = YahooFinance.get(FxSymbols.CADUSD);
			//Update informations about this Asset
			if(s!=null) {
				// Start insert historical
				List<HistoricalQuote> lhq = s.getHistory(from, to, Interval.DAILY);
				for(HistoricalQuote hq: lhq){
					//System.out.println(hq.g);
				}
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

	}

}
