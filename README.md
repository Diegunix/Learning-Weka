# learning-application
## Spring Boot, Hibernate, JPA, Mysql, Weka, Gson, OkHttp, Swagger.

[![Build Status](https://travis-ci.org/Diegunix/Learning-Weka.svg?branch=master)](https://travis-ci.org/Diegunix/Learning-Weka)
[![Total alerts](https://img.shields.io/lgtm/alerts/g/Diegunix/Learning-Weka.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/Diegunix/Learning-Weka/alerts/)
[![codecov](https://codecov.io/gh/Diegunix/Learning-Weka/branch/master/graph/badge.svg)](https://codecov.io/gh/Diegunix/Learning-Weka)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Diegunix_Learning-Weka&metric=alert_status)](https://sonarcloud.io/dashboard?id=Diegunix_Learning-Weka)
[![License: GPL v2](https://img.shields.io/badge/License-GPL%20v2-blue.svg)](https://github.com/Diegunix/Learning-Weka/blob/master/LICENSE)
[![Documentation Status](https://readthedocs.org/projects/ansicolortags/badge/?version=latest)](https://app-diego-learning.herokuapp.com/api-doc.html)

### Objetivo
En este ejemplo se ha construido un pequeño proyecto de machine learning que dependiendo de unos datos climáticos calcula si se puede hacer una acción o no.
Para esto hay que lanzar una petición get sobre weather con codigos de una provincia y de una localidad.
Con estos códigos se hará la consulta a Aemet OpenData, para recibir los datos meteorológicos, del objeto recibido de Aemet nos quedamos con los datos de la hora
actual del sistema y creamos una instancia que con weka comparamos con los datos almacenados en BBDD y se calcula si es posible realizar la acción.
Esta instancia es guardada en la BBDD para que en el futuro la predicción del dato de accion sea mas real.
Si el modelo devolviese una acción que creemos que no es real se podría modificar el valor de la acción de ese registro para que en el futuro la predicción tenga mas
porcentaje de acierto.


### Claves
En el proyecto hay scripts para crear las tablas necesarias e importar los datos básicos.
Para poder utilizar el proyecto es necesario tener una apiKey de Aemet OpenData que sera configurada en el application.propeties. 
Para obtener la apiKey ir a la web de [Aemet](https://opendata.aemet.es/centrodedescargas/inicio).

---
