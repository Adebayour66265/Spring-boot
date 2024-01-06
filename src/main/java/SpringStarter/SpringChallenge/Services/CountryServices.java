package SpringStarter.SpringChallenge.Services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import SpringStarter.SpringChallenge.Controller.AddResponse;
import SpringStarter.SpringChallenge.beans.Country;

@Component
public class CountryServices {

	static HashMap<Integer, Country> countryIdMap;
	
	public CountryServices() {
		countryIdMap = new HashMap<Integer, Country>();
		
		Country nigeriaCountry = new Country(1, "Nigeria", "Lagos");
		Country usaCountry = new Country(2, "USA", "Washington");
		Country ukCountry = new Country(3, "UK", "London");
		Country indiaCountry = new Country(4, "India", "Delhi");
		
		
		countryIdMap.put(1, nigeriaCountry);
		countryIdMap.put(2, usaCountry);
		countryIdMap.put(3, ukCountry);
		countryIdMap.put(4, indiaCountry);
		
		
	}
	
	
	public List getAllCountries() {
		List countries = new ArrayList(countryIdMap.values());
		return countries;
	}
	
	public Country getCountryById(int id) {
		Country country = countryIdMap.get(id);
		return country;
	}
	
	public Country getCountryByName(String countryName) {
		Country country = null;
		for (int i : countryIdMap.keySet()) {
			if (countryIdMap.get(i).getCountryName().equals(countryName)) {		
			}
		}
		return country;
	}
	
	public Country addCountry(Country country) {
		country.setId(getMaxId());
		countryIdMap.put(country.getId(), country);
		return country;
	}
	
	public static int getMaxId() {
		int max = 0;
		for (int id : countryIdMap.keySet()) {
			if (max <= id) {
				max = id;
			}
		}
		return max + 1;
	}
	
	public Country updateCountry(Country country) {
		if (country.getId()>0) {
			countryIdMap.put(country.getId(), country);
		}
		return country;
	} 
	
	public AddResponse deleteCountry(int id) {
		countryIdMap.remove(id);
		AddResponse response = new AddResponse();
		response.setMsg("Country deleted");
		response.setId(id);
		return response;
	}
	
}
