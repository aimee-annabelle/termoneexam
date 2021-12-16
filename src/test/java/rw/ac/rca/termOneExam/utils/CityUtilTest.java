package rw.ac.rca.termOneExam.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.repository.ICityRepository;

import java.util.List;

import static org.junit.Assert.assertTrue;

@DataJpaTest
@RunWith(SpringRunner.class)
public class CityUtilTest {

    @Autowired
    private ICityRepository cityRepository;

    @Test
    public void weatherLessThan40DegreesTest_success(){
        List<City> cities = cityRepository.findAll();
        for(City city: cities){
            assertTrue(city.getWeather() < 40);
        }
    }
    @Test
    public void weatherGreaterThan10DegreesTest_success(){
        List<City> cities = cityRepository.findAll();
        for(City city: cities){
            assertTrue(city.getWeather() > 10);
        }
    }
}
