package com.plutus.tests;

import java.text.DecimalFormat;

public class RSI {


	
	public static void main(String[] args) {
		
		double [] stock = {1.0,1.1,1.3,1.2,1.5,1.6,1.3,1.4,1.7};
		
		
		
		double[] numberOfBuys = new double[stock.length]; // apostou em crescimento do valors dos stocks Test outcome positive
		double[] numberOfSells = new double[stock.length]; // apostou em descrescimo do valor dos stocks
		
		double[] gains = new double[stock.length-1]; // numero de acertos fatos TRUE CASES
		double[] losses = new double[stock.length-1]; // numero de erros fatos FALSE CASES
		
		double precision = gains.length/(gains.length + numberOfSells.length); // numero de ganhos contra o numero de vendas erradas realizadas
		
		double gainsAverage = 0.0;
		double lossesAverage = 0.0;
		
		System.out.println("Size of my samples = "+stock.length);
		

		DecimalFormat df2 = new DecimalFormat("0.000");
		
		for (int i = 1; i < stock.length; i++) {
			
			double p = (stock[i]/stock[i-1])-1;
			System.out.println(""+df2.format(p));
		}

	}

}
