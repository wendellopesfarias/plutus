package com.plutus.math;

import java.util.Arrays;

public class Statistic{
	
	public static double calculateAverage(double list[]){
		
		double sum = 0;
		double num = list.length;
		double average = 0;
		
		for(int i = 0; i < num; i++ ){
			sum += list[i]; 
		}
		
		average = sum/num;
		
		return average;
	}
	
	public static double[] calculateDesviation(double list[], double average){

		double listD[] = new double[list.length];
		
		for(int i=0; i< list.length; i++){
			listD[i] = list[i] - average;
		}
		
		return listD;
	}
	
	public static double calculateSumOfSquared(double list[]){
		
		double sos = 0;   // sum of squared
		
		for(int i = 0; i < list.length; i++ ){
			sos += Math.pow(list[i], 2); 
		}
		
		return sos;
	}
	
	public static double calculateVariace(double list[]){
		
		double average = calculateAverage(list);
	
        return calculateSumOfSquared(calculateDesviation(list, average)) / (list.length - 1);
	}

	public static double calculateStandardDeviation(double list[]){
		
		return Math.sqrt(calculateVariace(list));
	}
	/*
	 * Algumas publicações estabelecem critérios para classificação do coeficiente de variação, 
	 * de acordo com dados de trabalhos com as variáveis estudadas, muitas vezes expressando essa 
	 * classificação em tabelas onde determinam-se valores de CV considerados: Baixo, Médio, Alto e 
	 * Muito Alto (quanto menor o CV, maior a precisão dos dados)
	 */
	public static double calculateCoefficientVariation(double sd, double average){
		
		 double cv = sd/average;
		if(cv <= 0.15){ //For menor ou igual a 15% → baixa dispersão: dados homogêneos
			System.out.println("baixa dispersao");
		} else if(cv <= 0.30){ //For entre 15 e 30% → média dispersão
			System.out.println("média dispersão");
		} else{  //For maior que 30% → alta dispersão: dados heterogêneos
			System.out.println("alta dispersão");
		}
	
		return cv;
	}
	/*
	 * the quality or state of being correct or precise.
	 */
	public static double calculateAccuracy(double sd, double average){
		
		return sd/average;
	}
	
	
	

}
