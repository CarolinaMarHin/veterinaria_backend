package com.ceiba.usuario.controlador.cita;

import com.ceiba.ApplicationMock;
import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.mascota.comando.ComandoMascota;
import com.ceiba.mascota.controlador.ComandoControladorMascota;
import com.ceiba.usuario.servicio.testdatabuilder.cita.ComandoCitaTestDataBuilder;
import com.ceiba.usuario.servicio.testdatabuilder.mascota.ComandoMascotaTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorMascota.class)
@ContextConfiguration(classes = ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorCitaTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia crear una cita")
    void deberiaCrearUnCitaa() throws Exception {
        ComandoCita cita = new ComandoCitaTestDataBuilder().build();
        mocMvc.perform(post("/citas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cita)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));
    }

    @Test
    @DisplayName("Deberia actualizar una cita")
    void deberiaActualizarUnaCita() throws Exception {
        Long id = 1L;
        ComandoCita cita = new ComandoCitaTestDataBuilder().build();
        mocMvc.perform(put("/citas/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cita)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deberia eliminar una cita")
    void deberiaEliminarUnaCita() throws Exception {
        Long idCita = 1L;
        mocMvc.perform(delete("/citas/{idCita}", idCita)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mocMvc.perform(get("/citas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

}
