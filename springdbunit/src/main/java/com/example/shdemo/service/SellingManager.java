package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.Car;
import com.example.shdemo.domain.City;
import com.example.shdemo.domain.Person;
import com.example.shdemo.domain.Unicorn;

public interface SellingManager {
	
	// Person
	void addClient(Person person);
	List<Person> getAllClients();
	void deleteClient(Person person);
	Person findClientByPin(String pin);	
	List<Car> getOwnedCars(Person person);
    void updateClient(Person client);
	
	// Car
	Long addNewCar(Car car);
	List<Car> getAvailableCars();
	void disposeCar(Person person, Car car);
	Car findCarById(Long id);	
	void sellCar(Long personId, Long carId);

	// City
	void addCity(City city);
	void deleteCity(City city);
    void updateCity(City city);
	City findCityByZipcode(String zipcode);
	List<City> getAllCities();
	List<Unicorn> getLivingUnicorns(City city);
	
	// Unicorn
	Long addUnicorn(Unicorn unicorn);
	List<Unicorn> getLiveUnicorns();
	Unicorn findUnicornById(Long id);
}
