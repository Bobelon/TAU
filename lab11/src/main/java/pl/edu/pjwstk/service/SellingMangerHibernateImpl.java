package pl.edu.pjwstk.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import pl.edu.pjwstk.domain.City;
import pl.edu.pjwstk.domain.Unicorn;

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
		sessionFactory.getCurrentSession().getNamedQuery("city.update")
		.setParameter("name", city.getName())
		.setParameter("zipcode", city.getZipcode())
		.setParameter("id", city.getId()).executeUpdate();
	}

	@Override
	public void deleteCity(City city) {
		city = (City) sessionFactory.getCurrentSession().get(City.class, city.getId());

		// lazy loading here
		for (Unicorn unicorn : city.getUnicorns()) {
			sessionFactory.getCurrentSession().update(unicorn);
		}
		
		sessionFactory.getCurrentSession().delete(city);
	}

	@Override
	public List<Unicorn> getLivingUnicorns(City city) {
		city = (City) sessionFactory.getCurrentSession().get(City.class, city.getId());

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
		return (City) sessionFactory.getCurrentSession()
				.getNamedQuery("city.byZipcode")
				.setString("zipcode", zipcode)
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
	
	
	
	
	
// ####################################################################################
//  More advanced business method
// ####################################################################################
	
	public 	List<Unicorn> searchUnicorn(Long min, Long max) {
		return sessionFactory.getCurrentSession().getNamedQuery("unicorn.select")
				.setParameter("min", min)
				.setParameter("max", max)
				.list();
	}
}
