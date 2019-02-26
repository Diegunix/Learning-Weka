package com.learning.learning.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.learning.dao.domain.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
}
