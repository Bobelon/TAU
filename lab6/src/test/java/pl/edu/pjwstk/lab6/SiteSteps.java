/*package pl.edu.pjwstk.lab6;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import pl.edu.pjwstk.lab4.Alarm;
import pl.edu.pjwstk.lab4.AlarmImpl;
import pl.edu.pjwstk.lab4.MyCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SiteSteps {
	
	private final Pages pages;

    public SiteSteps(Pages pages) {
        this.pages = pages;
    }
    
	@Given("user in home page")
	public void addAlarm(int year, int month, int day, int hour, int minute) {
		pages.helpdesk().open();
	}
	
	@When("now is $year-$month-$day $hour:$minute")
	public void setCurrentDate(int year, int month, int day, int hour, int minute) {
		when(myCalendar.getTime()).thenReturn(new MyCalendar(year, month, day, hour, minute));
	}
	
	@Then("$resutl")
	public void checkTime(String result) {
		boolean res = result.equals("ring") ? true : false;
		assertEquals(alarm.shouldRing(), res);

    @When("user clicks the $linkText tab")
    public void userClicksTabLink(String linkText) {
        pages.helpdesk().click(linkText);
    }

    @Then("the tab with text $linkText should have class $classInside")
    public void tabWithTextAndClass(String linkText, String classInside) {
        assertTrue( pages.helpdesk().getClassesForLink(linkText).contains(classInside));
    }

    @Then("the tab with text $linkText should not have class $classInside")
    public void tabWithTextAndNotClass(String linkText, String classInside) {
        assertTrue( !pages.helpdesk().getClassesForLink(linkText).contains(classInside));
    }
}
*/