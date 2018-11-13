package com.plutus.tests;

import java.math.BigDecimal;
import java.math.MathContext;

public class TestProb {

	/*
	 * Sensitivity = TP/(TP + FN) = (Number of true positive assessment)/(Number of all positive assessment) 
	 * Specificity = TN/(TN + FP) = (Number of true negative assessment)/(Number of all negative assessment) 
	 * Accuracy = (TN + TP)/(TN+TP+FN+FP) = (Number of correct assessments)/Number of all assessments)
	 * 
	 * In addition to the equation show above, accuracy can be determined from sensitivity and specificity, 
	 * where prevalence is known. Prevalence is the probability of disease in the population at a given time:
	 * 
	 * Accuracy = (sensitivity) (prevalence) + (specificity) (1 - prevalence)
	 * 
	 * AUC Range Classification
	 *  0.9 < AUC < 1.0 Excellent
	 *  0.8 < AUC < 0.9 Good 
	 *  0.7 < AUC < 0.8 Worthless
	 *  0.6 < AUC < 0.7 Not good
	 */
	
	public static void main(String[] args) {
		

	}
	
	public static BigDecimal getSensitivity(BigDecimal TP, BigDecimal FN) {
		BigDecimal r = new BigDecimal(2.5); 
		return r;
	}

}
