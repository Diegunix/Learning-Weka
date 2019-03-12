package com.learning.learning.dao.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "weather", schema="db")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_provincia", referencedColumnName = "id_provincia")
    @JoinColumn(name = "id_localidad", referencedColumnName = "id")
    private Localidad localidad;
    @Column
    @NotNull
    private LocalDateTime fecha;
    @Column(nullable = true)
    private String orto;
    @Column(nullable = true)
    private String ocaso;
    @Column(nullable = true)
    private int humedadRelativa;
    @Column(nullable = true)
    private int sensTermica;
    @Column(nullable = true)
    private int temperatura;
    @Column(nullable = true)
    private int probNieve;
    @Column(nullable = true)
    private int nieve;
    @Column(nullable = true)
    private int probTormenta;
    @Column(nullable = true)
    private int probPrecipitacion;
    @Column(nullable = true)
    private int precipitacion;
    @Column(nullable = true)
    private int estadoCielo;
    @Column(nullable = true)
    private int viento;
    @Column(nullable = false)
    private Boolean action;

}
