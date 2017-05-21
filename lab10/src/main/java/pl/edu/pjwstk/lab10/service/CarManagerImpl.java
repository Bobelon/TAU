package pl.edu.pjwstk.lab10.service;

import org.springframework.stereotype.Component;

import pl.edu.pjwstk.lab10.domain.Car;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarManagerImpl implements CarManager {

	private Connection connection;

	private PreparedStatement addCarStmt;
	private PreparedStatement deleteCarStmt;
	private PreparedStatement getAllCarsStmt;
	private PreparedStatement getCarStmt;
	private PreparedStatement updateCarStmt;

	public CarManagerImpl(Connection connection) throws SQLException {
		this.connection = connection;

		ResultSet rs = connection.getMetaData().getTables(null, null, null, null);
		boolean tableExists = false;
		while (rs.next()) {
			if ("Car".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
				tableExists = true;
				break;
			}
		}

		if (!tableExists)
			connection.createStatement().executeUpdate("CREATE TABLE Car(id bigint GENERATED BY DEFAULT AS IDENTITY, "
					+ "name varchar(40), year integer)");

		addCarStmt = connection.prepareStatement("INSERT INTO Car (id, name, year) VALUES (?, ?, ?)");
		deleteCarStmt = connection.prepareStatement("DELETE FROM Car where name = ?");
		updateCarStmt = connection.prepareStatement("UPDATE Car SET name = ?, year = ? WHERE name = ?");
		getAllCarsStmt = connection.prepareStatement("SELECT id, name, year FROM Car");
		getCarStmt = connection.prepareStatement("SELECT id, name, year FROM Car WHERE id=?");

	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public void deleteCar(Car car) throws SQLException {
		deleteCarStmt.setString(1, car.getName());
		deleteCarStmt.executeUpdate();
	}

	@Override
	public void clearCar() throws SQLException {
		connection.prepareStatement("delete from Car").executeUpdate();
	}

	@Override
	public int addCar(Car car) {
		int count = 0;
		try {
			addCarStmt.setLong(1, car.getId());
			addCarStmt.setString(2, car.getName());
			addCarStmt.setInt(3, car.getYear());

			count = addCarStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public void updateCar(Car car, String name) {
		try {
			updateCarStmt.setString(1, car.getName());
			updateCarStmt.setInt(2, car.getYear());
			updateCarStmt.setString(3, name);
			updateCarStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Car getCar(Car car) {
		Car findedCar = new Car();

		try {
			getCarStmt.setLong(1, car.getId());
			ResultSet rs = getCarStmt.executeQuery();
			rs.next();

			findedCar.setId(rs.getInt("id"));
			findedCar.setName(rs.getString("name"));
			findedCar.setYear(rs.getInt("year"));

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return findedCar;
	}

	public List<Car> getAllCars() {
		List<Car> cars = new ArrayList<Car>();

		try {
			ResultSet rs = getAllCarsStmt.executeQuery();

			while (rs.next()) {
				Car p = new Car();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setYear(rs.getInt("year"));
				cars.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cars;
	}
}
