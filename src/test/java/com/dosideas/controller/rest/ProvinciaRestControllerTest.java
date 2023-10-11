package com.dosideas.controller.rest;

import com.dosideas.builder.ProvinciaBuilder;
import com.dosideas.domain.Provincia;
import com.dosideas.service.ProvinciaService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProvinciaRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private ProvinciaService provinciaService;

    @Test
    public void getPorId_conIdValido_retornaProvincia() {
        Provincia provincia = ProvinciaBuilder.idExistente().build();
        when(provinciaService.buscarPorId(provincia.getId())).thenReturn(provincia);

        Provincia provinciaResponse = restTemplate.getForObject("/api/provincia/{id}", Provincia.class, provincia.getId());

        assertThat(provinciaResponse.getNombre()).isEqualTo("Provincia");
    }

    @Test
    public void getPorPais_conPaisValido_retornaProvinciasDelPais() {
        Provincia provincia = ProvinciaBuilder.provinciaBrasil().build();
        String nombrePais = "Brasil";
        when(provinciaService.buscarProvinciasPorNombrePais(nombrePais)).thenReturn(List.of(provincia));
        
        String provinciaResponse = restTemplate.getForObject("/api/provincia//pais/{nombrePais}", String.class, nombrePais);
        
        assertThat(provinciaResponse).isEqualTo("[{\"id\":21,\"nombre\":\"Rio du Janeiro\",\"id_pais\":2}]");
        verify(provinciaService).buscarProvinciasPorNombrePais(nombrePais);
    }
}
