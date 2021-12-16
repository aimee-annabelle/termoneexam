package rw.ac.rca.termOneExam.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.dto.CreateCityDTO;
import rw.ac.rca.termOneExam.repository.ICityRepository;
import rw.ac.rca.termOneExam.utils.APICustomResponse;
import rw.ac.rca.termOneExam.utils.CustomException;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CityServiceTest {

    @Mock
    private ICityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @Test
    public void all_test() {
        when(cityRepository.findAll()).thenReturn(Arrays.asList(new City("Kigali",10), new City("Huye",20)));
        assertEquals("Huye", cityService.getAll().get(1).getName());
    }

    @Test
    public void all_test_fail() {
        when(cityRepository.findAll())
                .thenReturn(null);
        assertEquals(null,cityService.getAll());
    }
    @Test
    public void findById_test() {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(new City("Kigali",24)));
        assertEquals("Kigali", cityService.getById(101).get().getName());
    }
    @Test
    public void findById_test_404() {
        when(cityRepository.findById(100L)).thenReturn(Optional.empty());

        assertEquals(false, cityService.getById(100L).isPresent());
    }

    @Test
    public void existsByName_success() {
        when(cityRepository.existsByName("Kigali")).thenReturn(true);
        assertEquals(true, cityService.existsByName("Kigali"));
    }

    @Test
    public void existsByName_fail() {
        when(cityRepository.existsByName("Kagali")).thenReturn(false);

        assertEquals(false, cityService.existsByName("Kagali"));
    }
//    @Test
//    public void save_success() {
//        CreateCityDTO createCityDTO = new CreateCityDTO("Nyanza", 24);
//        City city = new City(createCityDTO.getName(),createCityDTO.getWeather());
//        City createdCity = new City(105,createCityDTO.getName(),createCityDTO.getWeather(), 0);
//        when(cityRepository.save(city))
//                .thenReturn(createdCity);
//        assertEquals(createdCity, cityService.save(createCityDTO));
//    }

    @Test
    public void calculateFahrenheit_success() {
        City city = new City("Kigali", 28);
        assertEquals(125.5, cityService.calculateFahrenheit(city));
    }
}
