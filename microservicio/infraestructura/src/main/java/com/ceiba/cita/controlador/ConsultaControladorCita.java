package com.ceiba.cita.controlador;

import com.ceiba.cita.consulta.ManejadorListarCita;
import com.ceiba.cita.consulta.ManejadorListarCitaPorId;
import com.ceiba.cita.modelo.dto.DtoCita;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/citas")
@Api(tags = {"Controlador consulta cita"})
public class ConsultaControladorCita {

    private final ManejadorListarCita manejadorListarCita;
    private final ManejadorListarCitaPorId manejadorListarCitaPorId;

    public ConsultaControladorCita(ManejadorListarCita manejadorListarCita, ManejadorListarCitaPorId manejadorListarCitaPorId) {
        this.manejadorListarCita = manejadorListarCita;
        this.manejadorListarCitaPorId = manejadorListarCitaPorId;
    }


    @GetMapping
    @ApiOperation("Listar cita")
    public List<DtoCita> listar() {
        return this.manejadorListarCita.ejecutar();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation("Listar cita")
    public DtoCita listarPorCodigoMascota(@PathVariable Long id) {
        DtoCita cita = this.manejadorListarCitaPorId.ejecutar(id);
        return this.manejadorListarCitaPorId.ejecutar(id);
    }


}
