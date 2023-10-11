package com.dosideas.service;

import com.dosideas.domain.Provincia;
import com.dosideas.repository.ProvinciaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepository;

    public Provincia buscarPorId(Long id) {
        return provinciaRepository.findById(id).orElse(null);
    }

    public List<Provincia> buscarPorNombreExacto(String nombre) {
        if (!esNombreValido(nombre)) {
            throw new IllegalArgumentException("El nombre no es válido.");
        }
        return provinciaRepository.findByNombre(nombre);
    }

    public List<Provincia> buscarPorCoincidencia(String palabraClave) {
        return provinciaRepository.findByNombreContainingIgnoreCase(palabraClave);
    }

    public Provincia crear(Provincia provincia) {
        return provinciaRepository.save(provincia);
    }

    private boolean esNombreValido(String nombre) {
        return nombre != null && nombre.trim().length() >= 3;
    }
    
    public List<Provincia> buscarProvinciasPorNombrePais(String nombrePais){
        return provinciaRepository.findAllByNombrePais(nombrePais);
    }
}
