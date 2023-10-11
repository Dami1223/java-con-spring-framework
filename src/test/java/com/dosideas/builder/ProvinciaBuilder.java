package com.dosideas.builder;

import com.dosideas.domain.Provincia;
import com.somospnt.test.builder.AbstractBuilder;

public class ProvinciaBuilder extends AbstractBuilder<Provincia>{

    public static ProvinciaBuilder idNull() {
        ProvinciaBuilder builder = new ProvinciaBuilder();
        builder.instance.setId(null);
        builder.instance.setNombre("Provincia");
        builder.instance.setId_pais(1L);
        return builder;
    }
     private ProvinciaBuilder() {
        instance = new Provincia();
    }

    public static ProvinciaBuilder idValido() {
        ProvinciaBuilder builder = new ProvinciaBuilder();
        builder.instance.setId(null);
        builder.instance.setNombre("Provincia");
        builder.instance.setId_pais(1L);
        return builder;
    }
    
    public static ProvinciaBuilder idExistente() {
        ProvinciaBuilder builder = new ProvinciaBuilder();
        builder.instance.setId(99L);
        builder.instance.setNombre("Provincia");
        builder.instance.setId_pais(1L);
        return builder;
    }
    
    public static ProvinciaBuilder provinciaBrasil() {
        ProvinciaBuilder builder = new ProvinciaBuilder();
        builder.instance.setId(21L);
        builder.instance.setNombre("Rio du Janeiro");
        builder.instance.setId_pais(2L);
        return builder;
    }
}
