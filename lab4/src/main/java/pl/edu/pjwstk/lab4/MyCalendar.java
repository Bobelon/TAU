package pl.edu.pjwstk.lab4;

import java.util.Calendar;

public class MyCalendar {
	
	private  int year = 0, month = 0, day = 0, hour = 0, minute = 0;
	
	public MyCalendar(int year, int month, int day, int hour, int minute) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}
	
	public boolean equals(MyCalendar another) {
		return ( this.year == another.year
			&& this.month == another.month 
			&& this.day == another.day
			&& this.hour == another.hour
			&& this.minute == another.minute) ? true : false;
	}
	
	public MyCalendar getTime() { return this; }
}
