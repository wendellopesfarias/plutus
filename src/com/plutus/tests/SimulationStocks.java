package com.plutus.tests;

import java.text.DecimalFormat;

import com.fasterxml.jackson.databind.ser.std.StaticListSerializerBase;
import com.plutus.math.Statistic;

public class SimulationStocks {
	

	public static void main(String[] args) {

		DecimalFormat df = new DecimalFormat("0.00");
		
		int n = 157;
		
		double min = 0.9;
		double max = 1.0;
		double expectedReturn = 0.05;
		double absolutRisk = 1.5;
		
		double listRisk[] =new double[n];
		double listReturn[] = new double[n];
		
		for (int i = 0; i < n; i++) {
//			if(Math.random() < 0.5) {
//				risk = (-1)*risk;
//			}
			double risk = randomMinMax(-absolutRisk, absolutRisk);
			double returnE = randomMinMax(0,expectedReturn);
			min+=expectedReturn + risk;
			max+=expectedReturn + risk;
			listRisk[i]=risk;
			listReturn[i]=returnE;
			
			System.out.println("Risk: "+ df.format(risk)+" Return: "+df.format(returnE));
			
			
			//System.out.println("Diff = "+df.format(max - min));
			//System.out.println("Min = "+df.format(min) +" Max = "+ df.format(max));
			//System.out.println("Random = "+df.format(randomMinMax(min, max)));
		}
		
		System.out.println("Average Risk:   "+df.format(Statistic.calculateAverage(listRisk)));
		System.out.println("Average Return: "+df.format(Statistic.calculateAverage(listReturn)));

	}
	
	private static double randomMinMax(double min,double max) {
		return min + Math.random() * (max - min);
	}

}
