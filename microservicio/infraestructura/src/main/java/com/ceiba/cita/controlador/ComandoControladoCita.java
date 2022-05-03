package com.ceiba.cita.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.cita.comando.manejador.ManejadorActualizarCita;
import com.ceiba.cita.comando.manejador.ManejadorCrearCita;
import com.ceiba.cita.comando.manejador.ManejadorEliminarCita;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/citas")
@Api(tags = {"Controlador comando cita"})
public class ComandoControladoCita {

    private final ManejadorCrearCita manejadorCrearCita;
    private final ManejadorEliminarCita manejadorEliminarCita;
    private final ManejadorActualizarCita manejadorActualizarCita;

    @Autowired
    public ComandoControladoCita(ManejadorCrearCita manejadorCrearCita,
                                 ManejadorEliminarCita manejadorEliminarCita,
                                 ManejadorActualizarCita manejadorActualizarCita) {
        this.manejadorCrearCita = manejadorCrearCita;
        this.manejadorEliminarCita = manejadorEliminarCita;
        this.manejadorActualizarCita = manejadorActualizarCita;
    }


    @PostMapping
    @ApiOperation("Crear cita")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoCita comandoCita) {
        return manejadorCrearCita.ejecutar(comandoCita);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Eliminar cita")
    public void eliminar(@PathVariable Long id) {
        manejadorEliminarCita.ejecutar(id);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Actualizar cita")
    public void actualizar(@RequestBody ComandoCita comandoCita, @PathVariable Long id) {
        comandoCita.setId(id);
        manejadorActualizarCita.ejecutar(comandoCita);
    }
}
