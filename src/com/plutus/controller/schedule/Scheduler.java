package com.plutus.controller.schedule;

import java.util.Timer;

public class Scheduler {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Timer t = new Timer();
		ExtractData ed = new ExtractData(true,true);
		//t.schedule(ed, time);// Instantiate SheduledTask class
		
		t.schedule(ed, 0, 10000); // Create Repetitively task for every 1 secs
		
		for (int i = 0; i < 3; i++) {
			
			try {
				System.out.println("Thread sleep for 2 seconds");
				Thread.sleep(200000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		System.exit(0);

	}

}
