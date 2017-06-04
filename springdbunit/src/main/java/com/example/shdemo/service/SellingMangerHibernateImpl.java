package com.example.shdemo.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.Car;
import com.example.shdemo.domain.City;
import com.example.shdemo.domain.Person;
import com.example.shdemo.domain.Unicorn;

@Component
@Transactional
public class SellingMangerHibernateImpl implements SellingManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	// ####################################################################################
	// Person
	// ####################################################################################

	@Override
	public void addClient(Person person) {
		person.setId(null);
		sessionFactory.getCurrentSession().persist(person);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateClient(Person person) {
		sessionFactory.getCurrentSession().update(person);
	}

	@Override
	public void deleteClient(Person person) {
		person = (Person) sessionFactory.getCurrentSession().get(Person.class, person.getId());

		// lazy loading here
		for (Car car : person.getCars()) {
			car.setSold(false);
			sessionFactory.getCurrentSession().update(car);
		}
		// person.getCars().clear();
		// sessionFactory.getCurrentSession().update(person);

		sessionFactory.getCurrentSession().delete(person);
	}

	@Override
	public List<Car> getOwnedCars(Person person) {
		person = (Person) sessionFactory.getCurrentSession().get(Person.class, person.getId());
		// lazy loading here - try this code without (shallow) copying
		List<Car> cars = new ArrayList<Car>(person.getCars());
		return cars;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Person> getAllClients() {
		return sessionFactory.getCurrentSession().getNamedQuery("person.all").list();
	}

	@Override
	public Person findClientByPin(String pin) {
		return (Person) sessionFactory.getCurrentSession().getNamedQuery("person.byPin").setString("pin", pin)
				.uniqueResult();
	}

	// ####################################################################################
	// Car
	// ####################################################################################

	@Override
	public Long addNewCar(Car car) {
		car.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(car);
	}

	@Override
	public void sellCar(Long personId, Long carId) {
		Person person = (Person) sessionFactory.getCurrentSession().get(Person.class, personId);
		Car car = (Car) sessionFactory.getCurrentSession().get(Car.class, carId);
		car.setSold(true);
		if (person.getCars() == null) {
			person.setCars(new LinkedList<Car>());
		}
		person.getCars().add(car);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Car> getAvailableCars() {
		return sessionFactory.getCurrentSession().getNamedQuery("car.unsold").list();
	}

	@Override
	public void disposeCar(Person person, Car car) {

		person = (Person) sessionFactory.getCurrentSession().get(Person.class, person.getId());
		car = (Car) sessionFactory.getCurrentSession().get(Car.class, car.getId());

		Car toRemove = null;
		// lazy loading here (person.getCars)
		for (Car aCar : person.getCars())
			if (aCar.getId().compareTo(car.getId()) == 0) {
				toRemove = aCar;
				break;
			}

		if (toRemove != null)
			person.getCars().remove(toRemove);

		car.setSold(false);
	}

	@Override
	public Car findCarById(Long id) {
		return (Car) sessionFactory.getCurrentSession().get(Car.class, id);
	}

	// ####################################################################################
	// City
	// ####################################################################################

	@Override
	public void addCity(City city) {
		city.setId(null);
		sessionFactory.getCurrentSession().persist(city);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateCity(City city) {
		// sessionFactory.getCurrentSession().getNamedQuery("city.update").setString("name",
		// city.getName()).setString("zipcode", city.getZipcode()).setLong("id",
		// city.getId());// String sqlQuery = "UPDATE city SET name='" +
		// city.getName()
		// + "', zipcode='" + city.getZipcode() + "' WHERE id=" + city.getId();
		// sessionFactory.getCurrentSession().createSQLQuery(sqlQuery);
		// sessionFactory.getCurrentSession().update(city, city.getId());

		// sessionFactory.getCurrentSession().createQuery(sqlQuery).executeUpdate();
		String sql = "UPDATE City SET name = :name, zipcode = :zipcode " + "WHERE id = :id";
		sessionFactory.getCurrentSession().createQuery(sql)
				.setParameter("name", city.getName())
				.setParameter("zipcode", city.getZipcode())
				.setParameter("id", city.getId()).executeUpdate();
	}

	@Override
	public void deleteCity(City city) {
		city = (City) sessionFactory.getCurrentSession().get(City.class, city.getId());

		// lazy loading here
		for (Unicorn unicorn : city.getUnicorns()) {
			// unicorn.setSold(false);
			sessionFactory.getCurrentSession().update(unicorn);
		}
		// person.getCars().clear();
		// sessionFactory.getCurrentSession().update(person);

		sessionFactory.getCurrentSession().delete(city);
	}

	@Override
	public List<Unicorn> getLivingUnicorns(City city) {
		city = (City) sessionFactory.getCurrentSession().get(City.class, city.getId());
		// lazy loading here - try this code without (shallow) copying
		List<Unicorn> unicorns = new ArrayList<Unicorn>(city.getUnicorns());
		return unicorns;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<City> getAllCities() {
		return sessionFactory.getCurrentSession().getNamedQuery("city.all").list();
	}

	@Override
	public City findCityByZipcode(String zipcode) {
		return (City) sessionFactory.getCurrentSession().getNamedQuery("city.byZipcode").setString("zipcode", zipcode)
				.uniqueResult();
	}

	// ####################################################################################
	// Unicorn
	// ####################################################################################

	@Override
	public Long addUnicorn(Unicorn unicorn) {
		unicorn.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(unicorn);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Unicorn> getLiveUnicorns() {
		return sessionFactory.getCurrentSession().getNamedQuery("unicorn.all").list();
	}

	@Override
	public Unicorn findUnicornById(Long id) {
		return (Unicorn) sessionFactory.getCurrentSession().get(Unicorn.class, id);
	}
}
