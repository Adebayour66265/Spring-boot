package SpringStarter.SpringChallenge.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public List<Country> getCountries() {
		return countryServices.getAllCountries();
	}
	
	@GetMapping("/getcountry/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable(value = "id")int id) {
		try {
			Country country = countryServices.getCountryById(id);
			return new ResponseEntity<Country>(country, HttpStatus.OK);
		} catch (Exception e) {
			// handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/getcountry/countryname")
	public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String countryname) {
		try {
			Country country = countryServices.getCountryByName(countryname);
			return new ResponseEntity<Country>(country, HttpStatus.OK);
		} catch (Exception e) {
			// handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/addcountry")
	public Country addCountry(@RequestBody Country country) {
		return countryServices.addCountry(country);
	}
	
	@PutMapping("/updatecountry")
	public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") int id, @RequestBody Country country) {
		try {
			Country existingCountry = countryServices.getCountryById(id);
			
			existingCountry.setCountryName(country.getCountryName());
			existingCountry.setCountryCapital(country.getCountryCapital());
			
			Country update_country = countryServices.updateCountry(existingCountry);
			return new ResponseEntity<Country>(update_country,HttpStatus.OK);
			
		} catch (Exception e) {
			// handle exception
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}

	@DeleteMapping("/deletecountry/{id}")
	public AddResponse deleteCountry(@PathVariable(value = "id") int id) {
		return countryServices.deleteCountry(id);
	}
	
	
}
