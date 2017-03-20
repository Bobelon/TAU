package pl.edu.pjwstk.lab3;

import java.util.Calendar;

public class MyCalendar{
	
	private  int year = 0, month = 0, day = 0, hour = 0, minute = 0;
	
	public MyCalendar (int y, int m, int d, int h, int min) {
		set(y, m, d, h, min);
	}
	
	public void set (int y, int m, int d, int h, int min) {
		year = y;
		month = m;
		day = d;
		hour = h;
		minute = min;
	}
	
	public boolean equals(MyCalendar another) {
		if( this.year == another.year
			&& this.month == another.month 
			&& this.day == another.day
			&& this.hour == another.hour
			&& this.minute == another.minute)
			return true;
		
		return false;
	}
}
