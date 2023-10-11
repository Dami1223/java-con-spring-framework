package com.dosideas.service;

import com.dosideas.builder.ProvinciaBuilder;
import com.dosideas.domain.Provincia;
import com.dosideas.repository.ProvinciaRepository;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.dao.InvalidDataAccessApiUsageException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProvinciaServiceTest {

    @Autowired
    private ProvinciaService provinciaService;

    @SpyBean
    private ProvinciaRepository provinciaRepository;

    @Test
    public void crearConBuilder_conIdValido_retornaProvinciaCreada() {
        Provincia provincia = ProvinciaBuilder.idValido().build();
        assertThat(provincia.getId()).isEqualTo(null);

        provinciaService.crear(provincia);
        assertThat(provincia.getId()).isEqualTo(22L);
        verify(provinciaRepository,times(1)).save(provincia);
    }

    @Test
    public void buscarPorNombreExactoMockeado_conNombreExistente_retornaProvinciasConEseNombre() {
        Provincia provincia = ProvinciaBuilder.idExistente().build();
        List<Provincia> provinciasMock = List.of(provincia);
        when(provinciaRepository.findByNombre(provincia.getNombre())).thenReturn(provinciasMock);

        List<Provincia> provincias = provinciaService.buscarPorNombreExacto(provincia.getNombre());

        assertThat(provincias).isNotNull();
        assertThat(provincias).isEqualTo(provinciasMock);
        verify(provinciaRepository).findByNombre(provincia.getNombre());
    }

    @Test
    public void buscarPorId_conIdExistente_retornaProvinciaConEseId() {
        Long id = 1L;

        Provincia provincia = provinciaService.buscarPorId(id);

        assertThat(provincia).isNotNull();
        assertThat(provincia.getId()).isEqualTo(id);
    }

    @Test
    public void buscarPorIdConBuilder_conIdInexistente_retornaNull() {
        Long id = 22L;

        Provincia provincia = provinciaService.buscarPorId(id);
        assertThat(provincia).isNull();
    }

    @Test
    public void buscarPorId_conIdInexistente_retornaNull() {
        Long id = 22L;

        Provincia provincia = provinciaService.buscarPorId(id);
        assertThat(provincia).isNull();
    }

    @Test
    public void buscarPorId_conIdNull_lanzaExcepcion() {
        assertThatExceptionOfType(InvalidDataAccessApiUsageException.class)
                .isThrownBy(() -> provinciaService.buscarPorId(null));
    }

    @Test
    public void buscarPorNombreExacto_conNombreValido_devuelveListadoProvincia() {
        String nombre = "Buenos Aires";
        List<Provincia> provincias = provinciaService.buscarPorNombreExacto(nombre);
        assertThat(provincias.get(0).getNombre()).isEqualTo(nombre);
    }

    @Test
    public void buscarPorNombreExacto_conNombreNull_lanzaExcepcion() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> provinciaService.buscarPorNombreExacto(null));
    }

    @Test
    public void buscarPorNombreExacto_conNombreMenorATresCaracteres_lanzaExcepcion() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> provinciaService.buscarPorNombreExacto("La"));
    }

    @Test
    public void buscarPorCoincidencia_conPalabraClave_retornaProvinciasQueCoinciden() {
        String palabraClave = "San";
        List<Provincia> provincias = provinciaService.buscarPorCoincidencia(palabraClave);
        assertThat(provincias.size()).isEqualTo(3);
    }

    @Test
    public void buscarPorCoincidencia_conPalabraClaveSinMayuscula_retornaProvinciasQueCoinciden() {
        String palabraClave = "san";
        List<Provincia> provincias = provinciaService.buscarPorCoincidencia(palabraClave);
        assertThat(provincias.size()).isEqualTo(3);
    }
}
