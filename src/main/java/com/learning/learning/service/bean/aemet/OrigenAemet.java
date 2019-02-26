package com.learning.learning.service.bean.aemet;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect
public class OrigenAemet {

    private String productor;
    private String web;
    private String enlace;
    private String language;
    private String copyright;
    private String notaLegal;

}
