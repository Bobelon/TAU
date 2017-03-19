package pl.edu.pjwstk.lab3;

import org.junit.Test;

public class AlarmTest {

	@Test
	public void ringTest() {
		Alarm alarm = new AlarmImpl();
		
		/*
		 * alarm.shouldRing(t0) => true
		 * alarm.shouldRing(t0) => false
		 * alarm.shouldRing(t1) => false
		 * alarm.shouldRing(t0) => true
		 */
	}
	
	@Test
	public void addTest() {
		Alarm alarm = new AlarmImpl();		
	
		/*
		 * alarm.addAlarmTime(t0)
		 * alarm.addAlarmTime(t1)
		 * alarm.addAlarmTime(t2) 
		 */
		
		/*
		 * alarm.shouldRing(t0) => true
		 * alarm.shouldRing(t1) => true
		 * alarm.shouldRing(t2) => true
		 * alarm.shouldRing(t3) => false
		 */
	}
	
	@Test
	public void clearTest() {
		Alarm alarm = new AlarmImpl();
		
		/*
		 * alarm.addAlarmTime(t0)
		 * alarm.addAlarmTime(t1)
		 * alarm.addAlarmTime(t2)
		 */
		
		/*
		 * alarm.shouldRing(t0) => true
		 * alarm.shouldRing(t1) => true
		 * alarm.shouldRing(t2) => true
		 */
		
		/*
		 * alarm.clearAlarmTime(t0);
		 * alarm.clearAlarmTime(t1);
		 * alarm.clearAlarmTime(t2);
		 */
		
		/*
		 * alarm.shouldRing(t0) => false
		 * alarm.shouldRing(t1) => false
		 * alarm.shouldRing(t2) => false
		 */
	}
	
	@Test(expected=Exception.class) 
	public void addAlarmErrorTest() {
		Alarm alarm = new AlarmImpl();
		
		/*
		 * alarm.addAlarmTime(przeszły czas);
		 */
	}
}
