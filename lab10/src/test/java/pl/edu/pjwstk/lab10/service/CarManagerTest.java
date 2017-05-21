package pl.edu.pjwstk.lab10.service;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import pl.edu.pjwstk.lab10.domain.Car;
import pl.edu.pjwstk.lab10.service.CarManager;
import pl.edu.pjwstk.lab10.service.CarManagerImpl;

import static org.junit.Assert.assertEquals;

import java.net.URL;
import java.sql.SQLException;

@RunWith(JUnit4.class)
public class CarManagerTest extends DBTestCase {
	CarManager carManager;

    public CarManagerTest() throws Exception {
        super("CarManagerImpl test");
    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.INSERT;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.TRUNCATE_TABLE;
    }

    /**
     * Gets the default dataset. This dataset will be the initial state of database
     * @return the default dataset
     * @throws Exception when there are errors getting resources
     */
    @Override
    protected IDataSet getDataSet() throws Exception {
        return this.getDataSet("dataset-pm-add.xml");
    }

    /**
     * Returns dataset for selected resource
     * @param datasetName filename in resources
     * @return flat xml data set
     * @throws Exception when there is a problem with opening dataset
     */
    protected IDataSet getDataSet(String datasetName) throws Exception {
        URL url = getClass().getClassLoader().getResource(datasetName);
        FlatXmlDataSet ret = new FlatXmlDataSetBuilder().build(url.openStream());
        return ret;
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        FlatXmlDataSet dataSet = new FlatXmlDataSetBuilder().build(
                ServiceTests.class.getClassLoader().
                        getResource("dataset-pm.xml").openStream()
        );
        carManager = new CarManagerImpl(this.getConnection().getConnection());
    }
	
	@Test
	public void addTest() throws Exception {
		
		assertEquals(1, carManager.addCar(new Car(1001, "Dodany Pan samochodzik 1", 2011)));
		assertEquals(1, carManager.addCar(new Car(1002, "Dodany Pan samochodzik 2", 2012)));
		assertEquals(1, carManager.addCar(new Car(1003, "Dodany Pan samochodzik 3", 2013)));
		assertEquals(1, carManager.addCar(new Car(1004, "Dodany Pan samochodzik 4", 2014)));
		assertEquals(1, carManager.addCar(new Car(1005, "Dodany Pan samochodzik 5", 2015)));
		assertEquals(1, carManager.addCar(new Car(1006, "Dodany Pan samochodzik 6", 2016)));
		assertEquals(1, carManager.addCar(new Car(1007, "Dodany Pan samochodzik 7", 2017)));
		assertEquals(1, carManager.addCar(new Car(1008, "Dodany Pan samochodzik 8", 2018)));
		assertEquals(1, carManager.addCar(new Car(1009, "Dodany Pan samochodzik 9", 2019)));
		assertEquals(1, carManager.addCar(new Car(1010, "Dodany Pan samochodzik 10", 2020)));

        // Data verification

        IDataSet dbDataSet = this.getConnection().createDataSet();
        ITable actualTable = dbDataSet.getTable("CAR");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"ID"});
        IDataSet expectedDataSet = getDataSet("dataset-pm-add-check.xml");
        ITable expectedTable = expectedDataSet.getTable("CAR");

        Assertion.assertEquals(expectedTable, filteredTable);
    }
	
	@Test
	public void updateTest() throws Exception {
		
		carManager.updateCar(new Car(2001, "Auto brum brum 1", 2011), "Poczatkowe autko 1");
		carManager.updateCar(new Car(2002, "Auto brum brum 2", 2012), "Poczatkowe autko 2");
		carManager.updateCar(new Car(2003, "Auto brum brum 3", 2013), "Poczatkowe autko 3");
		carManager.updateCar(new Car(2004, "Auto brum brum 4", 2014), "Poczatkowe autko 4");
		carManager.updateCar(new Car(2005, "Auto brum brum 5", 2015), "Poczatkowe autko 5");
		carManager.updateCar(new Car(2006, "Auto brum brum 6", 2016), "Poczatkowe autko 6");
		carManager.updateCar(new Car(2007, "Auto brum brum 7", 2017), "Poczatkowe autko 7");
		carManager.updateCar(new Car(2008, "Auto brum brum 8", 2018), "Poczatkowe autko 8");
		carManager.updateCar(new Car(2009, "Auto brum brum 9", 2019), "Poczatkowe autko 9");
		carManager.updateCar(new Car(2010, "Auto brum brum 10", 2020), "Poczatkowe autko 10");

        // Data verification

        IDataSet dbDataSet = this.getConnection().createDataSet();
        ITable actualTable = dbDataSet.getTable("CAR");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"ID"});
        IDataSet expectedDataSet = getDataSet("dataset-pm-update-test.xml");
        ITable expectedTable = expectedDataSet.getTable("CAR");

        Assertion.assertEquals(expectedTable, filteredTable);
    }

	@Test
	public void deleteTest() throws Exception {
		
		carManager.deleteCar(new Car(2001, "Poczatkowe autko 11", 2011));
		carManager.deleteCar(new Car(2002, "Poczatkowe autko 12", 2012));
		carManager.deleteCar(new Car(2003, "Poczatkowe autko 13", 2013));
		carManager.deleteCar(new Car(2004, "Poczatkowe autko 14", 2014));
		carManager.deleteCar(new Car(2005, "Poczatkowe autko 15", 2015));
		carManager.deleteCar(new Car(2006, "Poczatkowe autko 16", 2016));
		carManager.deleteCar(new Car(2007, "Poczatkowe autko 17", 2017));
		carManager.deleteCar(new Car(2008, "Poczatkowe autko 18", 2018));
		carManager.deleteCar(new Car(2009, "Poczatkowe autko 19", 2019));
		carManager.deleteCar(new Car(2010, "Poczatkowe autko 20", 2020));

        // Data verification

        IDataSet dbDataSet = this.getConnection().createDataSet();
        ITable actualTable = dbDataSet.getTable("CAR");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"ID"});
        IDataSet expectedDataSet = getDataSet("dataset-pm-delete-test.xml");
        ITable expectedTable = expectedDataSet.getTable("CAR");

        Assertion.assertEquals(expectedTable, filteredTable);
    }
}
