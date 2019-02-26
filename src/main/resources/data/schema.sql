  CREATE TABLE `db`.`provincia` (
  `id` BIGINT(20) NOT NULL,
  `descripcion` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`));
  
  
    CREATE TABLE `db`.`localidad` (
  `id` BIGINT(20) NOT NULL,
  `id_provincia` BIGINT(20)NOT NULL,
  `descripcion` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`,`id_provincia`),
  CONSTRAINT `FK_LOCALIDAD_POVINCIA_ID` FOREIGN KEY (`id_provincia`) REFERENCES `provincia` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION);
  
  
  
  CREATE TABLE `db`.`weather` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
  `id_provincia` BIGINT(20)NOT NULL,
  `id_localidad` BIGINT(20)NOT NULL,
  `fecha` DATETIME NOT NULL,
  `orto` VARCHAR(50)NULL,
  `ocaso` VARCHAR(50)  NULL,
  `humedad_relativa` Integer(8)  NULL,
  `sens_termica` Integer(8) NULL,
  `temperatura` Integer(8)  NULL,
  `prob_nieve` Integer(8) NULL,
  `nieve` Integer(8)  NULL,
  `prob_tormenta` Integer(8) NULL,
  `prob_precipitacion` Integer(8)  NULL,
  `precipitacion` Integer(8) NULL,
  `estado_cielo` Integer(8)  NULL,
  `viento` Integer(8) NULL,
  `action` BOOL NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_WEATHER_LOCALIDAD_ID` FOREIGN KEY (`id_localidad`,`id_provincia`) REFERENCES `localidad` (`id`,`id_provincia`) ON UPDATE NO ACTION ON DELETE NO ACTION);
  
  

  
  