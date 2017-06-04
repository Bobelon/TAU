package com.example.shdemo.service;

import java.util.List;

import com.example.shdemo.domain.City;
import com.example.shdemo.domain.Unicorn;

public interface SellingManager {
	
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
