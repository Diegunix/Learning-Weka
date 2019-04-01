package com.learning.learning.commons.enums;

public enum EstadoCieloEnum {
    
    DESPEJADO("Despejado", 1),
    POCO_NUBOSO("Poco nuboso", 2),
    INTERVALOS_NUBOSOS("Intervalos nubosos", 3),
    NUBOSO("Nuboso", 4),
    MUY_NUBOSO("Muy nuboso", 5),
    CUBIERTO("Cubierto", 6),
    NUBES_ALTAS("Nubes altas", 7),
    INTERVALOS_NUBOSOS_CON_LLUVIA("Intervalos nubosos con lluvia", 8),
    NUBOSO_CON_LLUVIA("Nuboso con lluvia", 9),
    MUY_NUBOSO_CON_LLUVIA("Muy nuboso con lluvia", 10),
    CUBIERTO_CON_LLUVIA("Cubierto con lluvia", 11),
    INTERVALOS_NUBOSOS_CON_NIEVE("Intervalos nubosos con nieve", 12),
    NUBOSO_CON_NIEVE("Nuboso con nieve", 13),
    MUY_NUBOSO_CON_NIEVE("Muy nuboso con nieve", 14),
    CUBIERTO_CON_NIEVE("Cubierto con nieve", 15),
    CHUBASCOS("Chubascos", 16),
    TORMENTA("Tormenta", 17),
    GRANIZO("Granizo", 18),
    BRUMA("Bruma", 19),
    NIEBLA("Niebla", 20),
    CALIMA("Calima", 21),
    DESPEJADO_NOCHE("Despejado noche", 22),
    POCO_NUBOSO_NOCHE("Poco nuboso noche", 23),
    INTERVALOS_NUBOSOS_NOCHE("Intervalos nubosos noche", 24),
    NUBOSO_NOCHE("Nuboso noche", 25),
    NUBES_ALTAS_NOCHE("Nubes altas noche", 26),
    INTERVALOS_NUBOSOS_CON_LLUVIA_ESCASA("Intervalos nubosos con lluvia escasa", 27),
    INTERVALOS_NUBOSOS_CON_LLUVIA_ESCASA_NOCHE("Intervalos nubosos con lluvia escasa noche", 28),
    NUBOSO_CON_LLUVIA_ESCASA("Nuboso con lluvia escasa", 29),
    NUBOSO_CON_LLUVIA_ESCASA_NOCHE("Nuboso con lluvia escasa noche", 30),
    MUY_NUBOSO_CON_LLUVIA_ESCASA("Muy nuboso con lluvia escasa", 31),
    CUBIERTO_CON_LLUVIA_ESCASA("Cubierto con lluvia escasa", 32),
    INTERVALOS_NUBOSOS_CON_LLUVIA_NOCHE("Intervalos nubosos con lluvia noche", 33),
    NUBOSO_CON_LLUVIA_NOCHE("Nuboso con lluvia noche", 34),
    INTERVALOS_NUBOSOS_CON_NIEVE_ESCASA("Intervalos nubosos con nieve escasa", 35),
    INTERVALOS_NUBOSOS_CON_NIEVE_ESCASA_NOCHE("Intervalos nubosos con nieve escasa noche", 36),
    NUBOSO_CON_NIEVE_ESCASA("Nuboso con nieve escasa", 37),
    NUBOSO_CON_NIEVE_ESCASA_NOCHE("Nuboso con nieve escasa noche", 38),
    MUY_NUBOSO_CON_NIEVE_ESCASA("Muy nuboso con nieve escasa", 39),
    CUBIERTO_CON_NIEVE_ESCASA("Cubierto con nieve escasa", 40),
    INTERVALOS_NUBOSOS_CON_NIEVE_NOCHE("Intervalos nubosos con nieve noche", 41),
    NUBOSO_CON_NIEVE_NOCHE("Nuboso con nieve noche", 42),
    INTERVALOS_NUBOSOS_CON_TORMENTA("Intervalos nubosos con tormenta", 43),
    INTERVALOS_NUBOSOS_CON_TORMENTA_NOCHE("Intervalos nubosos con tormenta noche", 44),
    NUBOSO_CON_TORMENTA("Nuboso con tormenta", 45),
    NUBOSO_CON_TORMENTA_NOCHE("Nuboso con tormenta noche", 46),
    MUY_NUBOSO_CON_TORMENTA("Muy nuboso con tormenta", 47),
    CUBIERTO_CON_TORMENTA("Cubierto con tormenta", 48),
    INTERVALOS_NUBOSOS_CON_TORMENTA_Y_LLUVIA_ESCASA("Intervalos nubosos con tormenta y lluvia escasa", 49),
    INTERVALOS_NUBOSOS_CON_TORMENTA_Y_LLUVIA_ESCASA_NOCHE("Intervalos nubosos con tormenta y lluvia escasa noche", 50),
    NUBOSO_CON_TORMENTA_Y_LLUVIA_ESCASA("Nuboso con tormenta y lluvia escasa", 51),
    NUBOSO_CON_TORMENTA_Y_LLUVIA_ESCASA_NOCHE("Nuboso con tormenta y lluvia escasa noche", 52),
    MUY_NUBOSO_CON_TORMENTA_Y_LLUVIA_ESCASA("Muy nuboso con tormenta y lluvia escasa", 53),
    CUBIERTO_CON_TORMENTA_Y_LLUVIA_ESCASA("Cubierto con tormenta y lluvia escasa", 54);
    
    private String description;

    private Integer code;

    EstadoCieloEnum(String description, Integer code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCode() {
        return code;
    }

    public static EstadoCieloEnum get(Integer code) {
        for (EstadoCieloEnum country : EstadoCieloEnum.values()) {
            if (country.getCode().equals(code)) {
                return country;
            }
        }
        return null;
    }

    public static EstadoCieloEnum get(String description) {
        for (EstadoCieloEnum country : EstadoCieloEnum.values()) {
            if (country.getDescription().equals(description)) {
                return country;
            }
        }
        return null;
    }

}
