package SpringStarter.SpringChallenge.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import SpringStarter.SpringChallenge.beans.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

}
