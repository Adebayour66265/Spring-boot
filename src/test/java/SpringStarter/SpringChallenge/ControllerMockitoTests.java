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
}
