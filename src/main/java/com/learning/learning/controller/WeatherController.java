package com.learning.learning.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.learning.learning.controller.dto.DataDTO;
import com.learning.learning.controller.dto.mappers.WeatherMapper;
import com.learning.learning.dao.domain.Localidad;
import com.learning.learning.dao.domain.Provincia;
import com.learning.learning.dao.domain.Weather;
import com.learning.learning.service.LearningService;
import com.learning.learning.service.UtilService;

@RestController
public class WeatherController {

    private UtilService utilService;
    private LearningService learningService;

    public WeatherController(UtilService utilService, LearningService learningService) {
        this.utilService = utilService;
        this.learningService = learningService;
    }

    @GetMapping(value = "/weather")
    public @ResponseBody DataDTO createWeather(@RequestParam(value = "provincia", required = true) int provincia,
            @RequestParam(value = "localidad", required = true) int localidad) throws Exception {
        return WeatherMapper.map(learningService.predicePlay(provincia, localidad));
    }

    @GetMapping(value = "/weathers")
    public @ResponseBody Iterable<Weather> getAllWeather() {
        return this.utilService.getWeather();
    }

    @PutMapping(value = "/weather/{id}")
    public @ResponseBody DataDTO updateWeather(@PathVariable("id") int weatherId, @RequestBody DataDTO weather) {
        return WeatherMapper.map(learningService.updateWeather(Weather.builder().id(weatherId).action(weather.getAction()).build()));
    }
    
    @GetMapping(value = "/weather/provincias")
    public @ResponseBody List<Provincia> getProvincias() {
        return utilService.getProvincias();
    }
    
    @GetMapping(value = "/weather/provincia/{id}/localidades")
    public @ResponseBody List<Localidad> getLocalidades(@PathVariable("id") Long provinciaId) {
        return utilService.getLocalidades(provinciaId);
    }

}