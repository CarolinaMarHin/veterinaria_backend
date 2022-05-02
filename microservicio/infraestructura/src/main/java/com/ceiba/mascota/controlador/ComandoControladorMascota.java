package com.ceiba.mascota.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.mascota.comando.ComandoMascota;
import com.ceiba.mascota.comando.manejador.ManejadorActualizarMascota;
import com.ceiba.mascota.comando.manejador.ManejadorCrearMascota;
import com.ceiba.mascota.comando.manejador.ManejadorEliminarMascota;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mascotas")
@Api(tags = {"Controlador comando mascota"})
public class ComandoControladorMascota {

    private final ManejadorCrearMascota manejadorCrearMascota;
    private final ManejadorEliminarMascota manejadorEliminarMascota;
    private final ManejadorActualizarMascota manejadorActualizarMascota;

    @Autowired
    public ComandoControladorMascota(ManejadorCrearMascota manejadorCrearMascota,
                                     ManejadorEliminarMascota manejadorEliminarMascota,
                                     ManejadorActualizarMascota manejadorActualizarMascota) {
        this.manejadorCrearMascota = manejadorCrearMascota;
        this.manejadorEliminarMascota = manejadorEliminarMascota;
        this.manejadorActualizarMascota = manejadorActualizarMascota;
    }

    @PostMapping
    @ApiOperation("Crear mascota")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoMascota comandoMascota) {
        return manejadorCrearMascota.ejecutar(comandoMascota);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Eliminar mascota")
    public void eliminar(@PathVariable Long id) {
        manejadorEliminarMascota.ejecutar(id);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation("Actualizar mascota")
    public void actualizar(@RequestBody ComandoMascota comandoMascota, @PathVariable Long id) {
        comandoMascota.setId(id);
        manejadorActualizarMascota.ejecutar(comandoMascota);
    }
}
