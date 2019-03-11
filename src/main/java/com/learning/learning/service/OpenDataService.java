package com.learning.learning.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.learning.learning.commons.enums.EstadoCieloEnum;
import com.learning.learning.dao.domain.Weather;
import com.learning.learning.service.bean.ResponseAemet;
import com.learning.learning.service.bean.aemet.ObjectDataAemet;
import com.learning.learning.service.bean.aemet.PredictionAemet;
import com.learning.learning.service.bean.aemet.ResponsePredictionAemet;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

@Service
public class OpenDataService {

    @Value("${apiKey}")
    private String apikey;

    private static final String URL = "https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/horaria/";

    private static final String FORMAT_NUM = "%%0%dd";

    private final DateTimeFormatter f = DateTimeFormatter.ofPattern("HH");

    public Weather getWeather(Integer provincia, Integer localidad) throws IOException {
        String inputData = String.format(String.format(FORMAT_NUM, 2), provincia) + String.format(String.format(FORMAT_NUM, 3), localidad);
        Gson gson = new GsonBuilder().create();
        String data = doRequest(URL + inputData + "?api_key=" + apikey);
        ResponseAemet aemet = gson.fromJson(data, ResponseAemet.class);
        String result = doRequest(aemet.getDatos());
        return parseWeather(gson.fromJson(result, ResponsePredictionAemet[].class));
    }

    private Weather parseWeather(ResponsePredictionAemet[] dataWeather) {
        for (ResponsePredictionAemet data : dataWeather) {
            for (PredictionAemet dia : data.getPrediccion().getDia()) {
                LocalDate date = LocalDate.parse(dia.getFecha());
                if (date.equals(LocalDate.now())) {
                    return parseDay(dia);
                }
            }
        }
        return null;
    }

    private Weather parseDay(PredictionAemet dia) {
        Weather result = new Weather();

        result.setOcaso(dia.getOcaso());
        result.setOrto(dia.getOrto());
        result.setFecha(LocalDateTime.now());

        result.setEstadoCielo(getDataCielo(dia.getEstadoCielo(), result.getFecha()));
        result.setHumedadRelativa(getData(dia.getHumedadRelativa(), result.getFecha()));
        result.setNieve(getData(dia.getNieve(), result.getFecha()));
        result.setPrecipitacion(getData(dia.getPrecipitacion(), result.getFecha()));
        result.setProbNieve(getDataPeriod(dia.getProbNieve(), result.getFecha()));
        result.setProbPrecipitacion(getDataPeriod(dia.getProbPrecipitacion(), result.getFecha()));
        result.setProbTormenta(getDataPeriod(dia.getProbTormenta(), result.getFecha()));
        result.setSensTermica(getData(dia.getSensTermica(), result.getFecha()));
        result.setTemperatura(getData(dia.getTemperatura(), result.getFecha()));
        result.setViento(getData(dia.getVientoAndRachaMax(), result.getFecha()));

        return result;
    }

    private int getData(List<ObjectDataAemet> data, LocalDateTime fecha) {
        for (ObjectDataAemet dataAemet : data) {
            if (dataAemet.getPeriodo().equals(fecha.format(f)) && dataAemet.getValue() != null) {
                return Integer.parseInt(dataAemet.getValue());
            }
        }
        return 0;
    }

    private int getDataPeriod(List<ObjectDataAemet> data, LocalDateTime fecha) {
        for (ObjectDataAemet dataAemet : data) {
            String start = dataAemet.getPeriodo().substring(0, 2);
            String end = dataAemet.getPeriodo().substring(2, 4);

            if (Integer.parseInt(fecha.format(f)) >= Integer.parseInt(start) && Integer.parseInt(fecha.format(f)) < Integer.parseInt(end)) {
                return Integer.parseInt(dataAemet.getValue());
            }
        }
        return 0;
    }

    private int getDataCielo(List<ObjectDataAemet> data, LocalDateTime fecha) {
        for (ObjectDataAemet dataAemet : data) {
            if (dataAemet.getPeriodo().equals(fecha.format(f)) && dataAemet.getValue() != null) {
                return EstadoCieloEnum.get(dataAemet.getDescripcion()).getCode();
            }
        }
        return 0;
    }

    private String doRequest(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().addHeader("cache-control", "no-cache").build();

        Response response = client.newCall(request).execute();
        return new String(response.body().string().getBytes(StandardCharsets.UTF_8));
    }
}
