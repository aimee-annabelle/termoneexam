package rw.ac.rca.termOneExam.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;

@Service
public class CityService {

	@Autowired
	private ICityRepository cityRepository;
	
	public Optional<City> getById(long id) {

		Optional<City> city = cityRepository.findById(id);
		if(city.isPresent()) {
			city.get().setFahrenheit(calculateFahrenheit(city.get()));
		}

		return city;
	}

	public List<City> getAll() {

		List<City> cities = cityRepository.findAll();
		for (City city : cities) {
			city.setFahrenheit(calculateFahrenheit(city));
		}
		return cities;
	}

	public boolean existsByName(String name) {
		
		return cityRepository.existsByName(name);
	}

	public City save(CreateCityDTO dto) {
		City city =  new City(dto.getName(), dto.getWeather());
		return cityRepository.save(city);
	}

	public double calculateFahrenheit(City city) {
		return city.getWeather() * 33.8;
	}

}
