package pl.edu.pjwstk.lab3;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Test;

public class AlarmTest {

	@Mock
	private MyCalendar myCalendar = new MyCalendar(2017, 3, 14, 15, 21);

	@TestSubject
	private Alarm alarm = new AlarmImpl(myCalendar);
	
	@Test
	public void ringTest() {
		myCalendar.set(2017, 3, 14, 15, 21);
		MyCalendar t0 = new MyCalendar(2017, 3, 14, 15, 21);
		
		// Sprawdza czy aktualnie musi dzwonić. Przy drugim sprawdzeniu nie powinno już dzwonić
		alarm.addAlarmTime(t0);	
		assertTrue(alarm.shouldRing());		
		assertFalse(alarm.shouldRing());		
		alarm.addAlarmTime(t0);
		assertTrue(alarm.shouldRing());
		
		
		MyCalendar t1 = new MyCalendar(2018, 3, 14, 15, 21);
		MyCalendar t2 = new MyCalendar(2017, 3, 14, 15, 30);
		
		// Dodanie 2 czasów, ale obecnie nie powinno dzwonić
		alarm.addAlarmTime(t1);
		alarm.addAlarmTime(t2);
		assertFalse(alarm.shouldRing());		
		
		alarm.addAlarmTime(t0);
		assertTrue(alarm.shouldRing());
	}
}
