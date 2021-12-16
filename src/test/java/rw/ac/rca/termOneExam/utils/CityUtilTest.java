package rw.ac.rca.termOneExam.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import rw.ac.rca.termOneExam.domain.City;
import rw.ac.rca.termOneExam.repository.ICityRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

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

    @Test
    public void citiesContainMusanzeKigaliTest_success(){
        List<City> cities = cityRepository.findAll();
        List<String> cityName = new ArrayList<String>();
        for(City city: cities){
            cityName.add(city.getName());
        }
        assertTrue(cityName.contains("Musanze"));
        assertTrue(cityName.contains("Kigali"));
    }

    @Test
    public void list_spying() {

        ArrayList<City> arrayListSpy = spy(ArrayList.class);
        City city = new City("Butare",21);
        arrayListSpy.add(city);
        System.out.println(arrayListSpy.get(0).getWeather());
        System.out.println(arrayListSpy.size());

        arrayListSpy.add(city);
        arrayListSpy.add(city);
        System.out.println(arrayListSpy.size());

        when(arrayListSpy.size()).thenReturn(5);
        System.out.println(arrayListSpy.size());

        arrayListSpy.add(city);
        System.out.println(arrayListSpy.size());
    }

    @Test
    public void list_mocking() {
        City city = new City("Butare",21);
        List<City> mockList = mock(List.class);
        when(mockList.size()).thenReturn(5);
        assertEquals(5, mockList.size());
        when(mockList.get(0)).thenReturn(city);
        assertEquals("Butare", mockList.get(0).getName());
        assertEquals(null, mockList.get(1));
        verify(mockList,atLeast(1)).get(anyInt());
    }

}
