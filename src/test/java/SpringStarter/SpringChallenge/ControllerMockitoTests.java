package SpringStarter.SpringChallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import SpringStarter.SpringChallenge.Controller.CountryController;
import SpringStarter.SpringChallenge.Services.CountryServices;
import SpringStarter.SpringChallenge.beans.Country;

@SpringBootTest(classes = {ControllerMockitoTests.class})
public class ControllerMockitoTests {
	
	@Mock
	CountryServices countryServices;
	
	@InjectMocks
	CountryController countryController;
	
	List<Country> myCountries;
	
	Country country;
	
	@Test
	@Order(1)
	public void test_getAllCountries() {
		myCountries = new ArrayList<Country>();
		myCountries.add(new Country(1, "Nigeria", "Abuja"));
		myCountries.add(new Country(2, "Uk", "uk"));
		when(countryServices.getAllCountries()).thenReturn(myCountries);
		ResponseEntity<List<Country>> res = countryController.getCountries();
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(2,res.getBody().size());
	}
	@Test
	@Order(2)
	public void test_getCountryById() {
		country = new Country(2, "USA", "Washington");
		int countryId = 2;
		when(countryServices.getCountryById(countryId)).thenReturn(country);
		ResponseEntity<Country> res = countryController.getCountryById(countryId);
		
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(countryId,res.getBody().getId());
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() {
		country = new Country(2, "USA", "Washington");
		String countryName = "USA";
		
		when(countryServices.getCountryByName(countryName)).thenReturn(country);
		ResponseEntity<Country> res = countryController.getCountryByName(countryName);
		
		assertEquals(HttpStatus.FOUND, res.getStatusCode());
		assertEquals(countryName,res.getBody().getCountryName());
	}
	
	@Test
	@Order(4)
	public void test_addCountry() {
		country = new Country(3, "Ghana", "Accra");
		when(countryServices.addCountry(country)).thenReturn(country);
		
		ResponseEntity<Country> res = countryController.addCountry(country);
		
		assertEquals(HttpStatus.CREATED, res.getStatusCode());
		assertEquals(country,res.getBody());
	}
	
	@Test
	@Order(5)
	public void test_updateCountry() {
		country = new Country(3, "Japan", "Tokyo");
		int countryId = 3;
		
		when(countryServices.getCountryById(countryId)).thenReturn(country);
		when(countryServices.updateCountry(country)).thenReturn(country);
		
		
		ResponseEntity<Country> res = countryController.updateCountry(countryId, country);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(3,res.getBody().getId());
		
		assertEquals("Japan", res.getBody().getCountryName());
		assertEquals("Tokyo", res.getBody().getCountryCapital());
	}
	
	@Test
	@Order(6)
	public void test_deleteCountry() {
		country = new Country(3, "Japan", "Tokyo");
		int countryId = 3;
		
		when(countryServices.getCountryById(countryId)).thenReturn(country);
	
		ResponseEntity<Country> res = countryController.deleteCountry(countryId);
		
		assertEquals(HttpStatus.OK, res.getStatusCode());
		assertEquals(3,res.getBody().getId());
		
	}
}
