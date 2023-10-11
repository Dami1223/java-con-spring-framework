package com.dosideas.controller.rest;

import com.dosideas.domain.Personaje;
import com.dosideas.exception.ProvinciaNoEncontradoException;
import com.dosideas.repository.RickAndMortyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rick-and-morty")
public class RickAndMortyController {

    @Autowired
    private RickAndMortyRepository rickAndMortyRepository;

    @GetMapping("/personaje/{id}")
    public Personaje obtenerPersonajePorId(@PathVariable Integer id) throws ProvinciaNoEncontradoException {
        return rickAndMortyRepository.obtenerPersonajePorId(id);
    }
}
