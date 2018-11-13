package com.plutus.yahoo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.plutus.dao.AssetDAO;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;
import yahoofinance.histquotes2.HistoricalSplit;
import yahoofinance.quotes.fx.FxQuote;
import yahoofinance.quotes.fx.FxSymbols;

public class Yahoo {
	
	private static String file = "/Users/wendellopes/downloads/YahooTickerSymbols.xlsx";

	
	public static void main(String[] args) throws IOException {
		
//		List<String> stocks = readListStocks();
		
//		if(AssetDAO.insert(stocks)) {
//			System.out.println("Inset all data");
//		}else {
//			System.err.println("Problems with insert data");
//		}
	
		
//		Calendar from = Calendar.getInstance();
//		Calendar to = Calendar.getInstance();
//		from.add(Calendar.YEAR, -1); // from 1 year ago
//		Stock google = YahooFinance.get("GOOG");
//		List<HistoricalQuote> gq = google.getHistory(from, to, Interval.DAILY);
		//gq.forEach(x -> x.get);
	

	}
	
	public static List<String> readListStocks() {
		
		List<String> listStocks = new ArrayList<>();
		
		//
		//A1:A106328
		try {
			FileInputStream excelFile = new FileInputStream(file);
			Workbook workbook = new XSSFWorkbook(excelFile);		
			Sheet sheet =workbook.getSheet("Stock");
	    
			FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
	
			for (int i = 1; i < 106328; i++) {
				CellReference cellReference = new CellReference("A"+i);
				Row row = sheet.getRow(cellReference.getRow());
				Cell cell = row.getCell(cellReference.getCol());
				CellValue cellValue = evaluator.evaluate(cell);
				listStocks.add(cellValue.getStringValue());
				//System.out.println(cellValue.getStringValue());
			}
			
	        excelFile.close();
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listStocks;
		
	}
	

}
