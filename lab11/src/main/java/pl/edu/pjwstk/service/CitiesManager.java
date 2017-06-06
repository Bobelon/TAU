package pl.edu.pjwstk.service;

import java.util.List;

import pl.edu.pjwstk.domain.City;
import pl.edu.pjwstk.domain.Unicorn;

public interface CitiesManager {
	
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
	
	List<Unicorn> searchUnicorn(Long min, Long max);
}
