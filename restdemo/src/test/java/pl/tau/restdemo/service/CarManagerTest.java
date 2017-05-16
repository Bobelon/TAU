package pl.tau.restdemo.service;
// przyklad na podstawie przykladow J. Neumanna
import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Test;

import pl.tau.restdemo.domain.Car;

public class CarManagerTest {

	CarManager carManager = new CarManagerImpl();
	
	private final static String NAME_1 = "Porshe";
	private final static int YEAR_1 = 1939;
	private final static int ID_1 = 2000;

	public CarManagerTest() throws SQLException {
	}
	
	@Test
	public void checkConnection() {
	    assertNotNull(carManager.getConnection());
	}
	
	@Test
	public void addTest() throws SQLException{		
		Car car = new Car(1000, "Testowe auto", 2017);
		carManager.addCar(car);		
		assertEquals(car.getName(), carManager.getAllCars().get(1000).getName());
		carManager.deleteCar(car);
	}

	@Test
	public void updateTest() throws SQLException{	
		int id = 300;
		Car car1 = new Car(id, "Testowe auto", 2017); // Przykladowy samochod o id == 300
		Car car2 = carManager.getCar(car1); // Samochod z bazy danych o id == 300		
		
		carManager.updateCar(car1);	// Samochod o id == 300 zmieniany jest na przykladowy samochod
		System.err.println(carManager.getAllCars().get(id).getName());
		System.err.println(car2.getName());
		assertEquals(car1.getName(), carManager.getCar(car1).getName());
		assertEquals(car1.getYear(), carManager.getCar(car1).getYear());
		
		carManager.updateCar(car2); // Samochod jest przywracany do stanu przed testem	
		assertEquals(car2.getName(), carManager.getCar(car2).getName());
		assertEquals(car2.getYear(), carManager.getCar(car2).getYear());
	}
}
