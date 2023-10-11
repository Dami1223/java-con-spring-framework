package com.dosideas.controller.rest;

import com.dosideas.domain.Provincia;
import com.dosideas.exception.ProvinciaNoEncontradoException;
import com.dosideas.service.ProvinciaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/provincia")
public class ProvinciaRestController {

    @Autowired
    private ProvinciaService provinciaService;

    @GetMapping("/{id}")
    public Provincia buscarPorId(@PathVariable Long id) throws ProvinciaNoEncontradoException {
        Provincia provincia = provinciaService.buscarPorId(id);
        if (provincia == null) {
            throw new ProvinciaNoEncontradoException("Provincia no encontrada");
        }
        return provincia;
    }

    @GetMapping("/nombre/{nombre}")
    public List<Provincia> buscarPorNombreExacto(@PathVariable String nombre) throws ProvinciaNoEncontradoException {
        return provinciaService.buscarPorNombreExacto(nombre);
    }

    @GetMapping("/coincidencia/{nombre}")
    public List<Provincia> buscarPorCoincidencia(@PathVariable String nombre) throws ProvinciaNoEncontradoException {
        return provinciaService.buscarPorCoincidencia(nombre);
    }
    
    @GetMapping("/pais/{nombrePais}")
    public List<Provincia> buscarProvinciasPorNombrePais(@PathVariable String nombrePais) throws ProvinciaNoEncontradoException {
        return provinciaService.buscarProvinciasPorNombrePais(nombrePais);
    }

    @PostMapping("/crear")
    public Provincia crear(@RequestBody Provincia provincia) {
        return provinciaService.crear(provincia);
    }

}
