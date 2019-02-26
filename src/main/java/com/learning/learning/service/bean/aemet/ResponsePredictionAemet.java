package com.learning.learning.service.bean.aemet;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect
public class ResponsePredictionAemet {

    private OrigenAemet origen;
    private String elaborado;
    private String nombre;
    private String provincia;
    private String id;
    private String version;
    private DayAemet prediccion;
}
