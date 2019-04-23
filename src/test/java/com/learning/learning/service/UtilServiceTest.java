package com.learning.learning.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.learning.learning.dao.domain.Localidad;
import com.learning.learning.dao.domain.Provincia;
import com.learning.learning.dao.domain.Weather;
import com.learning.learning.dao.repository.ProvinciaRepository;
import com.learning.learning.dao.repository.WeatherRepository;

@RunWith(MockitoJUnitRunner.class)
public class UtilServiceTest {

    UtilService service;

    private ProvinciaRepository provinciaRepository;
    private WeatherRepository weatherRepository;

    @Before
    public void prepareEnvironment() {
        provinciaRepository = mock(ProvinciaRepository.class);
        weatherRepository = mock(WeatherRepository.class);
        service = new UtilService(provinciaRepository, weatherRepository);
    }

    @Test
    public void testGetProvincias() {
        List<Provincia> founds = new ArrayList<>();
        Provincia prov = new Provincia();
        prov.setId(2L);
        prov.setDescripcion("data");
        founds.add(prov);
        when(provinciaRepository.findAll()).thenReturn(founds);
        List<Provincia> resultData = service.getProvincias();
        assertThat(resultData.size(), is(1));
    }

    @Test
    public void testGetLocalidades() {
        List<Object[]> founds = new ArrayList<>();
        Object[] obj = { BigInteger.ONE, BigInteger.ZERO, "data" };
        founds.add(obj);
        when(provinciaRepository.findByProvincia(anyLong())).thenReturn(founds);
        List<Localidad> resultData = service.getLocalidades(1L);
        assertThat(resultData.size(), is(1));
    }

    @Test
    public void testFindWeather() {
        Weather founds = Weather.builder().id(2L).action(true).build();
        when(weatherRepository.findById(anyLong())).thenReturn(Optional.ofNullable(founds));
        Weather resultData = service.findWeatherById(2L);
        assertThat(resultData.getId(), is(2L));
    }

    @Test
    public void testGetWeather() {
        List<Weather> founds = new ArrayList<>();
        Weather wet = Weather.builder().id(2L).action(true).build();
        founds.add(wet);
        Page<Weather> foundsPage = new PageImpl<>(founds);
        when(weatherRepository.findAll(any(Pageable.class))).thenReturn(foundsPage);
        Pageable input = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC, "id"));
        Page<Weather> resultData = service.getWeather(input);
        assertThat(resultData.getContent().size(), is(1));
    }

}
