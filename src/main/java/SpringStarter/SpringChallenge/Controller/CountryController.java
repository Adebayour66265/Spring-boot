package SpringStarter.SpringChallenge.Controller;

import java.util.List;
import java.util.NoSuchElementException;

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
	public ResponseEntity<List<Country>> getCountries() {
		try {
			List<Country> countries = countryServices.getAllCountries();
			return new ResponseEntity<List<Country>>(countries, HttpStatus.FOUND);
		} catch (NoSuchElementException e) {
			//  handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		 
	}
	
	@GetMapping("/getcountry/{id}")
	public ResponseEntity<Country> getCountryById(@PathVariable(value = "id")int id) {
		try {
			Country country = countryServices.getCountryById(id);
			return new ResponseEntity<Country>(country, HttpStatus.FOUND);
		} catch (NoSuchElementException e) {
			// handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/getcountry/countryname")
	public ResponseEntity<Country> getCountryByName(@RequestParam(value = "name") String countryname) {
		try {
			Country country = countryServices.getCountryByName(countryname);
			return new ResponseEntity<Country>(country, HttpStatus.FOUND);
		} catch (NoSuchElementException e) {
			// handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/addcountry")
	public ResponseEntity<Country> addCountry(@RequestBody Country country) {
		try {
			country = countryServices.addCountry(country);
			return new ResponseEntity<Country>(country,HttpStatus.CREATED);
		} catch (NoSuchElementException e) {
			// exception
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping("/updatecountry")
	public ResponseEntity<Country> updateCountry(@PathVariable(value = "id") int id, @RequestBody Country country) {
		try {
			Country existingCountry = countryServices.getCountryById(id);
			
			existingCountry.setCountryName(country.getCountryName());
			existingCountry.setCountryCapital(country.getCountryCapital());
			
			Country update_country = countryServices.updateCountry(existingCountry);
			return new ResponseEntity<Country>(update_country,HttpStatus.OK);
			
		} catch (NoSuchElementException e) {
			// handle exception
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
	}

	@DeleteMapping("/deletecountry/{id}")
	public ResponseEntity<Country> deleteCountry(@PathVariable("id") int id) {
		Country country = null;
		try {
			country = countryServices.getCountryById(id);
			countryServices.deleteCountry(country);
		} catch (NoSuchElementException e) {
			// exception
			return new ResponseEntity<Country>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Country>(country, HttpStatus.OK);
	}
	
	
}
