package pl.edu.pjwstk.lab4;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.*;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import static org.junit.Assert.*;

public class AlarmSteps {
	
	private MyCalendar myCalendar = mock(MyCalendar.class);
	private Alarm alarm;
	
	@Given("set alarm on $year-$month-$day $hour:$minute")
	public void addAlarm(int year, int month, int day, int hour, int minute) {
		(alarm = new AlarmImpl(myCalendar)).addAlarmTime(new MyCalendar(year, month, day, hour, minute));
	}
	
	@When("now is $year-$month-$day $hour:$minute")
	public void setCurrentDate(int year, int month, int day, int hour, int minute) {
		when(myCalendar.getTime()).thenReturn(new MyCalendar(year, month, day, hour, minute));
	}
	
	@Then("$resutl")
	public void checkTime(String result) {
		boolean res = result.equals("ring") ? true : false;
		assertEquals(alarm.shouldRing(), res);
	}
}
