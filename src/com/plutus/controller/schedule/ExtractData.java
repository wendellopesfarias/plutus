package com.plutus.controller.schedule;

import java.util.List;
import java.util.TimerTask;

import com.plutus.dao.PersonDAO;

public class ExtractData extends TimerTask {
	
	private boolean activeReader;
	private boolean activeLoader;
	
	public ExtractData() {
		
	}
	
	public ExtractData(boolean activeReader) {
		this.activeReader=activeReader;
	}
	
	public ExtractData(boolean activeReader, boolean activeLoader) {
		this.activeReader=activeReader;
		this.activeLoader=activeLoader;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		if(this.activeReader) {
			System.out.println("Start active method read ...");
			read();
		}else {
			System.out.println("Don't active method read!");
		}
		
		
	}
	
	


}
