package pl.edu.pjwstk.lab3;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class AlarmImpl implements Alarm {
	
	private Set<MyCalendar> dates = new HashSet<MyCalendar>();
	private MyCalendar myCalendar;
	
	public AlarmImpl(MyCalendar myCalendar) {
		this.myCalendar = myCalendar;
	}
	
	public void setCalendar(MyCalendar myCalendar) {
		this.myCalendar = myCalendar;
	}

	public boolean shouldRing() {	
		for(MyCalendar c : dates) {
			if(c.equals(myCalendar)) {
				dates.remove(c);
				return true;
			}
		}
		return false;
	}

	public void addAlarmTime(MyCalendar time) {
		dates.add(time);
	}

	public void clearAlarmTime(MyCalendar time) {
		dates.remove(time);
	}

}
