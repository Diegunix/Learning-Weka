package com.learning.learning.service.bean.aemet;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect
public class PredictionAemet {

    private String fecha;
    private String orto;
    private String ocaso;
    private List<ObjectDataAemet> humedadRelativa;
    private List<ObjectDataAemet> sensTermica;
    private List<ObjectDataAemet> temperatura;
    private List<ObjectDataAemet> probNieve;
    private List<ObjectDataAemet> nieve;
    private List<ObjectDataAemet> probTormenta;
    private List<ObjectDataAemet> probPrecipitacion;
    private List<ObjectDataAemet> precipitacion;
    private List<ObjectDataAemet> estadoCielo;
    private List<ObjectDataAemet> vientoAndRachaMax;

}
