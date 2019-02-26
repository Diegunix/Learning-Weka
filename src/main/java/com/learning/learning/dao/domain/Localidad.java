package com.learning.learning.dao.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "localidad")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Localidad implements Serializable {
    
    private static final long serialVersionUID = -7361332706110947529L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_provincia")
    @JsonIgnore
    private Provincia provincia;
    
    @Column @NotNull
    private String descripcion;

}
