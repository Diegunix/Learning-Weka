package com.learning.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.learning.controller.dto.DataDTO;
import com.learning.learning.controller.dto.mappers.WeatherMapper;
import com.learning.learning.dao.domain.Weather;
import com.learning.learning.dao.repository.WeatherRepository;
import com.learning.learning.service.LearningService;

@RestController
public class WeatherController {
    
private WeatherRepository weatherRepository;
private LearningService service;
    
    public WeatherController(WeatherRepository weatherRepository,LearningService service) {
        this.weatherRepository = weatherRepository;
        this.service = service;
    }

  @GetMapping(value = "/weather")
  public @ResponseBody DataDTO createWeather(@RequestParam(value = "provincia", required = false) int provincia , 
  @RequestParam(value = "localidad", required = false) int localidad) throws Exception {
      return  WeatherMapper.map(service.predicePlay(provincia,localidad));
  }
  
  @GetMapping(value = "/weathers")
  public @ResponseBody Iterable<Weather> getAllWeather() {
      return this.weatherRepository.findAll();
  }
  
  @PutMapping(value = "/weather/{id}")
  public @ResponseBody DataDTO updateWeather(@PathVariable("id") int weatherId ,@RequestBody DataDTO weather) throws Exception {
      return  WeatherMapper.map(service.updateWeather(Weather.builder().id(weatherId).action(weather.getAction()).build()));
  }
  
}