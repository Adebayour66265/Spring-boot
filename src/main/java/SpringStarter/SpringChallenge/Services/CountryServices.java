package SpringStarter.SpringChallenge.Services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import SpringStarter.SpringChallenge.Controller.AddResponse;
import SpringStarter.SpringChallenge.beans.Country;
import SpringStarter.SpringChallenge.repositories.CountryRepository;

@Component
@Service
public class CountryServices {
	
	@Autowired
	CountryRepository countryrep;
	
	public List<Country> getAllCountries() {
		return countryrep.findAll();
		
	}
	
	public Country getCountryById(int id) {
		return countryrep.findById(id).get();
	}
	
	public Country getCountryByName(String countryName) {
		List<Country> countries = countryrep.findAll();
		Country country = null;
		for (Country countryresult : countries) {
			if (countryresult.getCountryName().equals(countryName)) {
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
	
	public AddResponse deleteCountry(int id) {
		if (countryrep == null) {
			AddResponse response = new AddResponse();
			response.setMsg("Country Not found");
			response.setId(id);
			return response;
		}
		
		countryrep.deleteById(id);
		AddResponse response = new AddResponse();
		response.setMsg("Country deleted");
		response.setId(id);
		return response;
		
		
	}
	
}
