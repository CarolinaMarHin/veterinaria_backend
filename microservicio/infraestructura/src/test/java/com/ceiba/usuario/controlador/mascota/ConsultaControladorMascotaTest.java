package com.ceiba.usuario.controlador.mascota;

import com.ceiba.ApplicationMock;
import com.ceiba.usuario.controlador.ConsultaControladorUsuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsultaControladorUsuario.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ConsultaControladorMascotaTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia listar por codigo de mascota")
    void deberiaListarPorCodigoMascota() throws Exception {
        String codigoMascota = "1234";
        mocMvc.perform(get("/mascotas/{codigoMascota}", codigoMascota).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
            .andExpect(jsonPath("$.nombreMascota", is("Titan")));
    }

    @Test
    @DisplayName("Deberia listar por id de mascota")
    void deberiaListarPorIdMascota() throws Exception {
        Long idMascota = 1L;
        mocMvc.perform(get("/mascotas/obtenerId/{idMascota}", idMascota).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreMascota", is("Titan")));
    }

    @Test
    @DisplayName("Deberia listar mascotas")
    void deberiaListarMascotas() throws Exception {
       mocMvc.perform(get("/mascotas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombreMascota", is("Titan")))
                .andExpect(jsonPath("$[0].id", is(1)));

    }
}
