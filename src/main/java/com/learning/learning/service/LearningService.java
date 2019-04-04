package com.learning.learning.service;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.learning.learning.commons.utils.FileUtils;
import com.learning.learning.dao.domain.Localidad;
import com.learning.learning.dao.domain.Provincia;
import com.learning.learning.dao.domain.Weather;
import com.learning.learning.dao.repository.WeatherRepository;

import lombok.extern.slf4j.Slf4j;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.experiment.InstanceQuery;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.NominalToString;
import weka.filters.unsupervised.attribute.Remove;

@Service
@Slf4j
public class LearningService {

    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String pass;
    @Value("${spring.datasource.url}")
    private String database;
    
    private static final String QUERY = "SELECT  cast(id as numeric), to_char(fecha, 'HH24:MI') hora, orto, ocaso, humedad_relativa, sens_termica, temperatura, prob_nieve, nieve, "
            + "prob_tormenta, prob_precipitacion, precipitacion, estado_cielo, viento, action FROM db.weather";

    private WeatherRepository weatherRepository;
    private OpenDataService dataService;

    public LearningService(WeatherRepository weatherRepository, OpenDataService dataService) {
        this.weatherRepository = weatherRepository;
        this.dataService = dataService;
    }

    public Weather updateWeather(Weather data) {
        Optional<Weather> weather = weatherRepository.findById(data.getId());
        Weather dataWeather;
        if (weather.isPresent()) {
            dataWeather = weather.get();
            dataWeather.setAction(data.getAction());
        } else {
            throw new EntityNotFoundException();
        }
        return weatherRepository.save(dataWeather);
    }

    public Weather predicePlay(int provincia, int localidad) throws Exception {
        Weather dataWeather = dataService.getWeather(provincia, localidad);
        dataWeather.setLocalidad(Localidad.builder().id(localidad).provincia(Provincia.builder().id(provincia).build()).build());

        Instances instances = getQuery();
        instances.setClassIndex(instances.numAttributes() - 1);

        Remove remove = new Remove();
        remove.setAttributeIndices("1");
        remove.setInputFormat(instances);
        instances = Filter.useFilter(instances, remove);

        J48 tree = new J48();
        tree.setUnpruned(true);
        tree.buildClassifier(instances);

        log.debug(instances.toString());
        log.info(tree.toString());

        int result = (int) tree.classifyInstance(getInstance(instances, dataWeather));

        log.info("Resultado de clasificar la nueva instancia: " + result);

        dataWeather.setAction(result != 0);

        addStatistis(instances);

        return weatherRepository.save(dataWeather);
    }

    private void addStatistis(Instances instances) throws Exception {
        Classifier treeClassifier = new J48();
        Evaluation treeEvaluation = new Evaluation(instances);
        int numFolds = instances.size() / 3;
        Random random = new Random(1);
        treeEvaluation.crossValidateModel(treeClassifier, instances, numFolds, random);
        log.info(treeEvaluation.toSummaryString());
        log.info(treeEvaluation.toMatrixString());
    }

    private Instance getInstance(Instances instances, Weather dataWeather) throws Exception {
        NominalToString nominalToBinary = new NominalToString();
        nominalToBinary.setInputFormat(instances);
        String[] options = { "-C", "1-2-3" };
        nominalToBinary.setOptions(options);
        Instances newInstances = Filter.useFilter(instances, nominalToBinary);
        Instance instance = new DenseInstance(13);
        instance.setDataset(newInstances);
        instance.setValue(0, getTime(dataWeather.getFecha()));
        instance.setValue(1, dataWeather.getOrto());
        instance.setValue(2, dataWeather.getOcaso());
        instance.setValue(3, dataWeather.getHumedadRelativa());
        instance.setValue(4, dataWeather.getSensTermica());
        instance.setValue(5, dataWeather.getTemperatura());
        instance.setValue(6, dataWeather.getProbNieve());
        instance.setValue(7, dataWeather.getNieve());
        instance.setValue(8, dataWeather.getProbTormenta());
        instance.setValue(9, dataWeather.getProbPrecipitacion());
        instance.setValue(10, dataWeather.getPrecipitacion());
        instance.setValue(11, dataWeather.getEstadoCielo());
        instance.setValue(12, dataWeather.getViento());
        return instance;
    }

    private String getTime(LocalDateTime fecha) {
        return fecha.getHour()+":"+fecha.getMinute();
    }

    private Instances getQuery() throws Exception {
        FileUtils fileUtils = new FileUtils();
        File file = fileUtils.getFile("DatabaseUtils.props");
        InstanceQuery query = new InstanceQuery();
        query.setCustomPropsFile(file);
        query.setDatabaseURL(database);
        query.setUsername(user);
        query.setPassword(pass);
        query.setQuery(QUERY);
        return query.retrieveInstances();
    }

}
