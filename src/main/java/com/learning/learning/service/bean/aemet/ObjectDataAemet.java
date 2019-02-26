package com.learning.learning.service.bean.aemet;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;

@Data
@JsonAutoDetect
public class ObjectDataAemet {
    
    private String value;
    private String periodo;
    private String descripcion;

}
