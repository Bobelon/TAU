package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Car;
import com.example.shdemo.domain.City;
import com.example.shdemo.domain.Person;
import com.example.shdemo.domain.Unicorn;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@Rollback
//@Commit
@Transactional(transactionManager = "txManager")
@TestExecutionListeners({
    DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
public class SellingManagerDBUnitTest {


	@Autowired
	SellingManager sellingManager;

	/*@Test
	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/addPersonData.xml", 
	assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void getClientCheck() {
	    assertEquals(2, sellingManager.getAllClients().size());
        
        Person p = new Person();
        p.setFirstName("Kaziu");
        p.setPin("8754");
        p.setRegistrationDate(new Date());

        sellingManager.addClient(p);
        assertEquals(3, sellingManager.getAllClients().size());

    }*/

	
	
	
	
//####################################################################################
// City tests
//####################################################################################	
	
	@Test
	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/addCityData.xml", table = "CITY", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void addCityTest() {
	    assertEquals(3, sellingManager.getAllCities().size());
	    
	    // add 3 cities
        sellingManager.addCity(new City("Grasmere", "33-333"));
        sellingManager.addCity(new City("Lockinge", "44-444"));
        sellingManager.addCity(new City("Easthaven", "55-555"));
        
        assertEquals(6, sellingManager.getAllCities().size());
    }
	
	@Test
	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/deleteCityData.xml", table = "CITY", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void deleteCityTest() {
	    assertEquals(3, sellingManager.getAllCities().size());

	    // delete 3 cities
        sellingManager.deleteCity(new City(0L, "Glenarm", "00-000"));
        sellingManager.deleteCity(new City(1L, "Hythe", "11-111"));
        sellingManager.deleteCity(new City(2L, "Wolford", "22-222"));
        
        assertEquals(0, sellingManager.getAllCities().size());
    }
	
	@Test
	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/updateCityData.xml", table = "CITY", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void updateCityTest() {
	    assertEquals(3, sellingManager.getAllCities().size());

	    // update 3 cities
        sellingManager.updateCity(new City(0L, "Carran", "49-123"));
        sellingManager.updateCity(new City(1L, "Swinford", "59-234"));
        sellingManager.updateCity(new City(2L, "Pella's Wish", "69-345"));
        
        assertEquals(3, sellingManager.getAllCities().size());
    }
	
	
	
	
	
//####################################################################################
// Unicorn tests
//####################################################################################	
	
	@Test
	@DatabaseSetup("/fullData.xml")
	@ExpectedDatabase(value = "/addUnicornData.xml", table = "UNICORN", assertionMode = DatabaseAssertionMode.NON_STRICT)
	public void addUnicornTest() {
	    assertEquals(1, sellingManager.getLiveUnicorns().size());
	    
	    // add 3 unicorns
        sellingManager.addUnicorn(new Unicorn("Leila"));
        sellingManager.addUnicorn(new Unicorn("Denali"));
        sellingManager.addUnicorn(new Unicorn("Tomo"));
        
        assertEquals(4, sellingManager.getLiveUnicorns().size());
    }
}
