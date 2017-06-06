package pl.edu.pjwstk.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

import pl.edu.pjwstk.domain.City;
import pl.edu.pjwstk.domain.Unicorn;
import pl.edu.pjwstk.service.CitiesManager;

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
public class UnicornsInCitiesDBUnitTest {


	@Autowired
	CitiesManager sellingManager;

		
	
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
        sellingManager.deleteCity(new City(0L, "Glenarm", "00-000"));        sellingManager.deleteCity(new City(2L, "Wolford", "22-222"));
        
        assertEquals(1, sellingManager.getAllCities().size());
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



//####################################################################################
// More advanced business method tests
//####################################################################################	
	
	@Test
	@DatabaseSetup("/fullData2.xml")
	public void moreAdvancedBusinessMethodTest() {
		assertEquals(5, sellingManager.searchUnicorn(3L, 7L).size());
		
		List<Unicorn> dbUnicorns = sellingManager.searchUnicorn(3L, 7L);
		List<Unicorn> assertUnicorns = new ArrayList<Unicorn>(Arrays.asList(
				new Unicorn("Albany"),
				new Unicorn("Aletha"),
				new Unicorn("Bennettia"),
				new Unicorn("Bellini"),
				new Unicorn("Benicia")));
		
		for (int i = 0; i < dbUnicorns.size(); i++){
			assertEquals(dbUnicorns.get(i).getName(),  assertUnicorns.get(i).getName());
		}		
  }
}