package com.plutus.model;

import java.sql.Timestamp;

public class Timeline {
	
	private long timeline_id;
	private Timestamp date_stamp;
	private int year;
	private int month;
	private int day;
	private int weekday;
	
	
	public long getTimeline_id() {
		return timeline_id;
	}
	public void setTimeline_id(long timeline_id) {
		this.timeline_id = timeline_id;
	}
	public Timestamp getDate_stamp() {
		return date_stamp;
	}
	public void setDate_stamp(Timestamp date_stamp) {
		this.date_stamp = date_stamp;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getWeekday() {
		return weekday;
	}
	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}
	
	
	
	

}
