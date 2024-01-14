package SpringStarter.SpringChallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import SpringStarter.SpringChallenge.Services.CountryServices;
import SpringStarter.SpringChallenge.beans.Country;
import SpringStarter.SpringChallenge.repositories.CountryRepository;

@SpringBootTest(classes = {ServiceMockitoTest.class})
public class ServiceMockitoTest {
	
	@Mock
	CountryRepository countryrep ;
	
	@InjectMocks
	CountryServices countryServices;
	
	public List<Country> mycountries;
	
	@Test
	@Order(1)
	public void test_getAllCountry() {
		mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1,"Nigeria", "Abuja"));
		mycountries.add(new Country(2,"USA", "Washigton"));
		
		when(countryrep.findAll()).thenReturn(mycountries);
		assertEquals(2, countryServices.getAllCountries().size());
	}
	
	@Test
	@Order(2)
	public void test_getCountryById() {
		mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1,"Nigeria", "Abuja"));
		mycountries.add(new Country(2,"USA", "Washigton"));

		
		int countryId = 1;
		when(countryrep.findAll()).thenReturn(mycountries);
		assertEquals(countryId, countryServices.getCountryById(countryId ).getId());
	}
	
	@Test
	@Order(3)
	public void test_getCountryByName() {
		mycountries = new ArrayList<Country>();
		mycountries.add(new Country(1,"Nigeria", "Abuja"));
		mycountries.add(new Country(2,"USA", "Washigton"));

		
		String countryName = "Nigeria";
		when(countryrep.findAll()).thenReturn(mycountries);
		assertEquals(countryName, countryServices.getCountryByName(countryName).getCountryName());
	}
	
	@Test
	@Order(4)
	public void test_addCountry() {
		Country country = new Country(3, "India", "Delhi");
		
		when(countryrep.save(country)).thenReturn(country);
		assertEquals(country, countryServices.addCountry(country));
	}
	
	@Test
	@Order(5)
	public void test_updateCountry() {
		
		Country country = new Country(3, "India", "Delhi");
		
		when(countryrep.save(country)).thenReturn(country);
		assertEquals(country, countryServices.updateCountry(country));
	}
	
	@Test
	@Order(6)
	public void test_deleteCountry() {
		
		Country country = new Country(3, "India", "Delhi");
		countryServices.deleteCountry(country);
		verify(countryrep,times(1)).delete(country);;
		
	}
}
