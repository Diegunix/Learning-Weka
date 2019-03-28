package com.learning.learning.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.learning.learning.dao.domain.Weather;

public interface WeatherRepository extends PagingAndSortingRepository <Weather, Long> {
}
