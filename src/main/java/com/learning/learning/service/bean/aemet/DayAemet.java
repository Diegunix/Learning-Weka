package com.learning.learning.service.bean.aemet;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect
public class DayAemet {
    
    private List<PredictionAemet> dia; 

}
