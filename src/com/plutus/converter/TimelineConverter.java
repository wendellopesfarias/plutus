package com.plutus.converter;

import java.sql.Timestamp;
import java.util.Calendar;

import com.plutus.model.Timeline;

public class TimelineConverter {

	public static Timeline calendarToTimeline(Calendar cal) {
		Timeline tl = new Timeline();
		tl.setDate_stamp(new Timestamp(cal.getTimeInMillis()));
		tl.setYear(cal.get(Calendar.YEAR));
		tl.setMonth(cal.get(Calendar.MONTH) + 1);
		tl.setDay(cal.get(Calendar.DAY_OF_MONTH));
		tl.setWeekday(cal.get(Calendar.DAY_OF_WEEK));
		return tl;
	}
	
	public static Calendar timelineToCalendar(Timeline tl) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(tl.getDate_stamp().getTime());
		return cal;
	}
}
