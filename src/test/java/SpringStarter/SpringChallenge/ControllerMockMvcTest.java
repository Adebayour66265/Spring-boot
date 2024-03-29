package SpringStarter.SpringChallenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import SpringStarter.SpringChallenge.Controller.CountryController;
import SpringStarter.SpringChallenge.Services.CountryServices;
import SpringStarter.SpringChallenge.beans.Country;


@TestMethodOrder(OrderAnnotation.class)
@ComponentScan(basePackages = "SpringStarter.restservices.demo")
@AutoConfigureMockMvc
@ContextConfiguration
@SpringBootTest(classes = {ControllerMockMvcTest.class})
public class ControllerMockMvcTest {

	@Autowired
	MockMvc mockMvc;
	
	@Mock
	CountryServices countryServices;
	
	@InjectMocks
	CountryController CountryController;
	
	List<Country> myCountries;
	
	Country country;
	
	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(CountryController).build();	
	}
	
	@Test
	@Order(1)
	public void test_getAllCountries() throws Exception {
		myCountries = new ArrayList<Country>();
		myCountries.add(new Country(1, "Nigeria", "Abuja"));
		myCountries.add(new Country(2, "Uk", "uk"));
		when(countryServices.getAllCountries()).thenReturn(myCountries);
		
		this.mockMvc.perform(get("/getcountries"))
		.andExpect(status().isFound())
		.andDo(print());
	}
	
	@Test
	@Order(2)
	public void test_getCountryById() throws Exception {
		country = new Country(2,"Nigeria", "Abuja");		
		int countryId = 2;
		
		
		when(countryServices.getCountryById(countryId ))
		.thenReturn(country);
		
		this.mockMvc.perform(get("/getcountry/{id}", countryId))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".id").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("Nigeria"))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Abuja"))
		.andDo(print());
	}
	
	
	@Test
	@Order(3)
	public void test_getCountryByName() throws Exception {
		country = new Country(2,"Nigeria", "Abuja");		
		String countryName = "Nigeria";
		
		
		when(countryServices.getCountryByName(countryName ))
		.thenReturn(country);
		
		this.mockMvc.perform(get("/getcountry/countryname").param("name", "Nigeria"))
		.andExpect(status().isFound())
		.andExpect(MockMvcResultMatchers.jsonPath(".id").value(2))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryName").value("Nigeria"))
		.andExpect(MockMvcResultMatchers.jsonPath(".countryCapital").value("Abuja"))
		.andDo(print());
	}
	
	
	@Test
	@Order(4)
	public void test_addCountry() throws Exception {
		country = new Country(3,"Germany", "Berlin");		
		
		when(countryServices.addCountry(country)).thenReturn(country);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonbody = mapper.writeValueAsString(country);
		
		this.mockMvc.perform(post("/addcountry").content(jsonbody).contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated()).andDo(print());
	}
	
	
	
	@Test
	@Order(6)
	public void test_deleteCountry() throws Exception {
		country = new Country(3,"Japan", "Tokyo");		
		int countryId = 3;
		
		when(countryServices.getCountryById(countryId)).thenReturn(country);
		when(countryServices.updateCountry(country)).thenReturn(country);
		
		
		this.mockMvc.perform(delete("/deletecountry/{id}", countryId))	
		.andExpect(status().isOk()).andDo(print());
	}
}
