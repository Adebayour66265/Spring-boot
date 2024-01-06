package SpringStarter.SpringChallenge.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SpringStarter.SpringChallenge.Services.CountryServices;
import SpringStarter.SpringChallenge.beans.Country;

@RestController
public class CountryController {
//	CountryServices countryServices = new CountryServices();
	
	@Autowired
	CountryServices countryServices;
	
	@GetMapping("/getcountries")
	public List getCountries() {
		return countryServices.getAllCountries();
	}
	
	@GetMapping("/getcountries/{id}")
	public Country getCountryById(@PathVariable(value = "id")int id) {
		return countryServices.getCountryById(id);
	}
	
	@GetMapping("/getcountries/countryname")
	public Country getCountryByName(@RequestParam(value = "name") String countryname) {
		return countryServices.getCountryByName(countryname);
	}
	
	@PostMapping("/addcountries")
	public Country addCountry(@RequestBody Country country) {
		return countryServices.addCountry(country);
	}
	
	@PutMapping("/updatecountries")
	public Country upCountry(@RequestBody Country country) {
		return countryServices.addCountry(country);
	}

	@DeleteMapping("/deletecountries/{id}")
	public AddResponse deleteCountry(@PathVariable(value = "id")int id) {
		return countryServices.deleteCountry(id);
	}
	
	
}
