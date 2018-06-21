package com.plutus.yahoo;

import java.io.IOException;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;
import yahoofinance.quotes.fx.FxSymbols;

public class Yahoo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stuba
		//FxQuote usdeur = YahooFinance.getFx(FxSymbols.USDEUR);
		//FxQuote usdgbp = YahooFinance.getFx("USDGBP=X");
		
		Stock stock = YahooFinance.get("INTC");
		
		System.out.println("Name: "+stock.getName());
		System.out.println(stock.getSymbol());
		System.out.println(stock.getCurrency());
		System.out.println(stock.getStockExchange());
		System.out.println(stock.getDividend());
		System.out.println(stock.getQuote().getLastTradeDateStr()+":"+stock.getQuote().getLastTradeTimeStr());
		System.out.println(stock.getQuote().getAvgVolume());
		System.out.println(stock.getDividend().getSymbol());
		System.out.println(stock.getStats().getBookValuePerShare());

		System.out.println(stock.getStats().getMarketCap());
		
		//System.out.println(stock.getStats().);
		

	}

}
