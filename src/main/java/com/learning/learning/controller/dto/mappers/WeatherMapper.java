package com.learning.learning.controller.dto.mappers;

import com.learning.learning.controller.dto.DataDTO;
import com.learning.learning.dao.domain.Weather;

public class WeatherMapper {
    
    private WeatherMapper() {
        throw new IllegalStateException("Utility class");
    }
    
    public static DataDTO map(Weather bean) {
        DataDTO dto = null;
        if (bean != null) {
            dto = new DataDTO();
            dto.setAction(bean.getAction());
        }
        return dto;
    }
    
    

}
