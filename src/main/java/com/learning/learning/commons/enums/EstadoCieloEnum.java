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
    CALIMA("Calima", 21);

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
    
    