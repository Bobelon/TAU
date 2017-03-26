package pl.edu.pjwstk.lab4;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import org.junit.Test;

public class AlarmTest {	
	
	MyCalendar myCalendar = mock(MyCalendar.class);	
	
	private Alarm alarm;
	
	@Test
	public void ringTest() {		
		alarm = new AlarmImpl(myCalendar);
		
		when(myCalendar.getTime()).thenReturn(new MyCalendar(2017, 3, 14, 15, 21));
		
		// Sprawdza czy aktualnie musi dzwonić. Przy drugim sprawdzeniu nie powinno już dzwonić
		alarm.addAlarmTime(new MyCalendar(2017, 3, 14, 15, 21));
		assertTrue(alarm.shouldRing());		
		assertFalse(alarm.shouldRing());		
		alarm.addAlarmTime(new MyCalendar(2017, 3, 14, 15, 21));
		assertTrue(alarm.shouldRing());
		
		// Dodanie 2 czasów, ale obecnie nie powinno dzwonić
		alarm.addAlarmTime(new MyCalendar(2018, 3, 14, 15, 21));
		alarm.addAlarmTime(new MyCalendar(2017, 3, 14, 15, 30));
		assertFalse(alarm.shouldRing());

		// Zrobienie, żeby wcześniej dodane czasy, działały
		when(myCalendar.getTime()).thenReturn(new MyCalendar(2018, 3, 14, 15, 21));
		assertTrue(alarm.shouldRing());
		when(myCalendar.getTime()).thenReturn(new MyCalendar(2017, 3, 14, 15, 30));
		assertTrue(alarm.shouldRing());		
	}
}
