package com.dosideas.repository;

import com.dosideas.domain.Personaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class RickAndMortyRepository {
    
    @Autowired
    private RestTemplate restTemplate = new RestTemplate();
    
    private static final String PERSONAJE_API="https://rickandmortyapi.com/api/character/{id}";
    
    public Personaje obtenerPersonajePorId(Integer id){
        Personaje personaje= restTemplate.getForObject(PERSONAJE_API, Personaje.class,id);
        return personaje;
    }
}
