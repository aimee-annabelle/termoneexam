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
    public void findById_test() {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.of(new City("Kigali",24)));

        assertEquals("Kigali", cityService.getById(101).get().getName());
    }

//    not working
    @Test
    public void findById_test_404() {
        when(cityRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(CustomException.class, () -> cityService.getById(101));

        assertEquals("Contact with this id not found", exception.getMessage());
    }



}
