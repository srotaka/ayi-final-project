package com.ayi.academy.app.enums;

public enum Province {
    BUENOS_AIRES("Buenos Aires"),
    CABA("CABA"),
    CATAMARCA("Catamarca"),
    CHACO("Chaco"),
    CHUBUT("Chubut"),
    CORDOBA("Córdoba"),
    CORRIENTES("Corrientes"),
    ENTRE_RIOS("Entre Ríos"),
    FORMOSA("Formosa"),
    JUJUY("Jujuy"),
    LA_PAMPA("La Pampa"),
    LA_RIOJA("La Rioja"),
    MENDOZA("Mendoza"),
    MISIONES("Misiones"),
    NEUQUEN("Neuquén"),
    RIO_NEGRO("Río Negro"),
    SALTA("Salta"),
    SAN_JUAN("San Juan"),
    SAN_LUIS("San Luis"),
    SANTA_CRUZ("Santa Cruz"),
    SANTA_FE("Santa Fe"),
    SANTIAGO_DEL_ESTERO("Santiago del Estero"),
    TIERRA_DEL_FUEGO("Tierra del Fuego"),
    TUCUMAN("Tucumán");

    private String provincia;
    Province(String provincia) {
        this.provincia = provincia;
    }
}
