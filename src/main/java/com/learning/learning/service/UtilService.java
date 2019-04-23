package com.learning.learning.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.learning.learning.dao.domain.Localidad;
import com.learning.learning.dao.domain.Provincia;
import com.learning.learning.dao.domain.Weather;
import com.learning.learning.dao.repository.ProvinciaRepository;
import com.learning.learning.dao.repository.WeatherRepository;

@Service
public class UtilService {

    private ProvinciaRepository provinciaRepository;
    private WeatherRepository weatherRepository;

    public UtilService(ProvinciaRepository provinciaRepository, WeatherRepository weatherRepository) {
        this.provinciaRepository = provinciaRepository;
        this.weatherRepository = weatherRepository;
    }

    public List<Provincia> getProvincias() {
        return provinciaRepository.findAll();
    }

    public List<Localidad> getLocalidades(Long provincia) {
        return mapContentToList(provinciaRepository.findByProvincia(provincia));
    }

    private List<Localidad> mapContentToList(List<Object[]> objects) {
        return objects.stream().map(object -> {
            return Localidad.builder().id(((BigInteger) object[0]).longValue()).descripcion(((String) object[2]))
                    .provincia(Provincia.builder().id(((BigInteger) object[1]).longValue()).build()).build();
        }).collect(Collectors.toList());
    }

    public Page<Weather> getWeather(Pageable pageable) {
        return weatherRepository.findAll(pageable);
    }

    public Weather findWeatherById(Long id) {
        Optional<Weather> optWeather = weatherRepository.findById(id);
        if (optWeather.isPresent()) {
            return optWeather.get();
        }
        throw new EntityNotFoundException();
    }

}
