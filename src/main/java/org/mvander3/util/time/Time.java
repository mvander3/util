package org.mvander3.util.time;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;

public class Time implements Serializable {

    private static final long serialVersionUID = 2016515113495303362L;
    
    private static final String HOUR_MINUTE_SEPARATOR = ":";
	private int hour;
	private int minute;

    @JsonCreator
	public Time( String timeString) {
		String[] timeParts = timeString.split(HOUR_MINUTE_SEPARATOR);
		if(timeParts.length != 2) {
			throw new IllegalArgumentException("Invalid value for parameter 'timeString': " + timeString);
		}
		int hour = Integer.valueOf(timeParts[0]);
		int minute = Integer.valueOf(timeParts[1]);
		verifyHour(hour);
		verifyMinute(minute);
		this.hour = hour;
		this.minute = minute;
	}

	public Time(int hour, int minute) {
		verifyHour(hour);
		verifyMinute(minute);
		this.hour = hour;
		this.minute = minute;
	}
	
	public Time getCopy() {
	    return new Time(this.hour, this.minute);
	}

	private void verifyMinute(int minute) {
		if(minute < 0 || minute > 59) {
			throw new IllegalArgumentException("The 'minute' parameter must be between 0 and 59 inclusively.");
		}
	}

	private void verifyHour(int hour) {
		if(hour < 0 || hour > 23) {
			throw new IllegalArgumentException("The 'hour' parameter must be between 0 and 23 inclusively.");
		}
	}
	
	public int getMinute() {
		return this.minute;
	}
	
	public int getHour() {
		return this.hour;
	}
	
	@JsonValue
	public String toString() {
		StringBuilder timeStringBuilder = new StringBuilder();
		if(hour < 10) {
			timeStringBuilder.append("0");
		}
		timeStringBuilder.append(hour);
		
		timeStringBuilder.append(HOUR_MINUTE_SEPARATOR);
		
		if(minute < 10) {
			timeStringBuilder.append("0");
		}
		timeStringBuilder.append(minute);
		
		return timeStringBuilder.toString();
	}
	
}
