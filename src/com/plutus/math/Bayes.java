package com.plutus.math;

import java.util.Map;
import java.util.HashMap;

public class Bayes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Integer,String> all = new HashMap<>();
		all.put(1, "Blue");
		all.put(2, "Blue");
		all.put(3, "Blue");
		all.put(4, "Red");
		all.put(5, "Red");
		
		Map<Integer,Map<String,Double>> mapProb = new HashMap<>();
	
		double pA = 0.0;
		double pB = 0.0;
		double total_A = 0;
		double total_B = 0;
		
		for(int i: all.keySet()) {
			System.out.println(all.get(i));
		}
		
		for(int i = 1; i <= all.size(); i++) {
			total_A += all.get(i).equals("Blue")?1:0;
			total_B += all.get(i).equals("Red")?1:0;
		}
		
		pA = total_A/all.size();
		pB = total_B/all.size();
		
		System.out.println("Prob Blue ="+ pA);
		System.out.println("Prob Red ="+ pB);
		
		Map<String,Double> m = new HashMap<>();
		m.put("Blue", pA);
		m.put("Red", pB);
		
		mapProb.put(1, m);
		
		System.out.println("Remove one "+all.get(1));
		all.remove(1);
		//all.remove(4);
		
		for(int i: all.keySet()) {
			System.out.println(all.get(i));
		}
		total_A = 0;
		total_B = 0;
		
		for(int i: all.keySet()) {
			total_A += all.get(i).equals("Blue")?1:0;
			total_B += all.get(i).equals("Red")?1:0;
		}
		
		pA = total_A/all.size();
		pB = total_B/all.size();
		
		System.out.println("Prob Blue ="+ pA);
		System.out.println("Prob Red ="+ pB);
		
		Map<String,Double> m2 = new HashMap<>();
		m2.put("Blue", pA);
		m2.put("Red", pB);

		mapProb.put(2, m2);
		
		for (int i : mapProb.keySet()) {
			System.out.println(i+" - "+mapProb.get(i));
		}
		
		System.out.println("");

	}

}
