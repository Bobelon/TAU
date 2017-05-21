package pl.edu.pjwstk.lab10.service;

// w oparciu o przyklad J Neumanna, przerobiony przez T Puzniakowskiego

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import pl.edu.pjwstk.lab10.domain.Car;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public interface CarManager {
	public Connection getConnection();
	public void deleteCar(Car car) throws SQLException;
	public void clearCar() throws SQLException;
	public int addCar(Car car);
	public Car getCar(Car car);
	public void updateCar(Car car, String name);
	public List<Car> getAllCars();
}
