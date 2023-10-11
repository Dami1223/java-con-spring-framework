package com.dosideas.controller.rest;

import com.dosideas.domain.Pais;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PaisRestControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getPorId_conIdValido_retornaProvincia() {
        Long id = 1L;

        Pais provinciaResponse = restTemplate.getForObject("/api/pais/{id}", Pais.class, id);

        assertThat(provinciaResponse.getNombre()).isEqualTo("Argentina");
    }

}
