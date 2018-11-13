package com.plutus.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.stock.StockQuote;
import yahoofinance.quotes.stock.StockStats;

public class AssetClassification {

	public static void main(String[] args) {
		
		try {
			Stock s = YahooFinance.get("PXT.TO");
			
			StockStats ss = s.getStats();
			StockQuote sq = s.getQuote();
			
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	//	String query = "select * from yahoo.finance.quote where symbol in (\"YHOO\",\"AAPL\")";
		
		Document doc;
		Document doc2;
		try {
			doc = Jsoup.connect("https://finance.yahoo.com/quote/PXT.TO/profile?p=PXT.TO").get();
			//select * from yahoo.finance.industry where id in (select industry.id from yahoo.finance.sectors)
			//http://query.yahooapis.com/v1/public/yql?q=select * from geo.places where text="sunnyvale, ca"
			//select * from yahoo.finance.quote where symbol in ("YHOO","AAPL","GOOG","MSFT")
			//doc2 = Jsoup.connect("http://query.yahooapis.com/v1/public/yql?q=select * from geo.places where text=\"sunnyvale, ca\"").get();
			
			//System.out.println(doc2.text());
			
			Elements sector = doc.select("span[data-reactid='21']");
			
			for (Element e : sector) {
				System.out.println(e.text());

			}
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		

	}

}
