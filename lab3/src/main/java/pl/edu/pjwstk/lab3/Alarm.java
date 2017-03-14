package pl.edu.pjwstk.lab3;

import java.util.Date;

public interface Alarm {
	public boolean shouldRing();
	public void addAlarmTime(Date time);
	public void clearAlarmTime(Date time);
}
