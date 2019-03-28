package com.learning.learning.controller;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.learning.learning.controller.dto.AddFormDTO;
import com.learning.learning.controller.dto.mappers.WeatherMapper;
import com.learning.learning.dao.domain.Weather;
import com.learning.learning.service.LearningService;
import com.learning.learning.service.UtilService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class NavController {

    private UtilService utilService;
    private LearningService learningService;

    public NavController(UtilService utilService, LearningService learningService) {
        this.utilService = utilService;
        this.learningService = learningService;
    }

    @GetMapping(value = "/listar")
    public ModelAndView listar() {
        ModelAndView mav = new ModelAndView();
        Pageable pageable = PageRequest.of(0, 20, Sort.by(Sort.Direction.DESC,"id"));
        mav.addObject("weathers", this.utilService.getWeather(pageable));
        mav.setViewName("lista");
        return mav;
    }

    @GetMapping(value = "/actualizar/{id}")
    public ModelAndView actualizar(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("weather", this.utilService.findWeatherById(id));
        mav.setViewName("update");
        return mav;
    }

    @GetMapping(value = "/crear")
    public ModelAndView crear() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("addForm", AddFormDTO.builder().build());
        mav.addObject("provincias", this.utilService.getProvincias());
        mav.setViewName("add");
        return mav;
    }

    @GetMapping(value = "/weather/{id}")
    public ModelAndView actualizar(@PathVariable("id") Long id, @RequestParam("action") Boolean action) {
        WeatherMapper.map(learningService.updateWeather(Weather.builder().id(id).action(action).build()));
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }

    @GetMapping(value = "/index")
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
    
    @GetMapping(value = "/createWeather")
    public ModelAndView actualizar(@RequestParam("provincia") int provincia, @RequestParam("localidad") int localidad) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("result", WeatherMapper.map(learningService.predicePlay(provincia, localidad)));
        mav.setViewName("result");
        return mav;
    }
    
}