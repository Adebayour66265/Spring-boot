package SpringStarter.SpringChallenge.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import SpringStarter.SpringChallenge.Controller.AddResponse;
import SpringStarter.SpringChallenge.beans.Country;
import SpringStarter.SpringChallenge.repositories.CountryRepository;

@Service
@Component
public class CountryServices {
	
	@Autowired
	CountryRepository countryrep;
	
	public List<Country> getAllCountries() {
		List<Country> countries = countryrep.findAll();
		return countries;
		
	}
	
	public Country getCountryById(int id) {
		List<Country> countries = countryrep.findAll();
		Country country = null;
		for (Country con : countries) {
			if (con.getId() == id) {
				country = con;
			}
		}
		return country;
	}
	
	
	public Country getCountryByName(String countryName) {
		List<Country> countries = countryrep.findAll();
		Country country = null;
		for (Country countryresult : countries) {
			if (countryresult.getCountryName().equalsIgnoreCase(countryName)) {
				country = countryresult;
			}
		}
		return country;
	}
	
	
	public Country addCountry(Country country) {
		country.setId(getMaxId());
		countryrep.save(country);
		return country;
	}
	
	
	public int getMaxId() {
		return countryrep.findAll().size()+1;
	}
	
	
	public Country updateCountry(Country country) {
		countryrep.save(country);
		return country;
	} 
	
	public void deleteCountry(Country country) {
		countryrep.delete(country);
	}
	
}
