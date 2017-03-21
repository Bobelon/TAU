package pl.edu.pjwstk.lab4;

import java.util.Calendar;

public interface Alarm {
	public boolean shouldRing();
	public void addAlarmTime(MyCalendar time);
	public void clearAlarmTime(MyCalendar time);
}
