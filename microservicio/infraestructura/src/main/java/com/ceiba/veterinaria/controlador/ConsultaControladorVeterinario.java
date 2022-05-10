package com.ceiba.veterinaria.controlador;

import com.ceiba.servicio.consulta.ManejadorListarServicio;
import com.ceiba.servicio.modelo.dto.DtoServicio;
import com.ceiba.veterinario.consulta.ManejadorListarVeterinario;
import com.ceiba.veterinario.dto.DtoVeterinario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/veterinarios")
@Api(tags = {"Controlador consulta servicios"})
public class ConsultaControladorVeterinario {

    private final ManejadorListarVeterinario manejadorListarVeterinario;

    public ConsultaControladorVeterinario(ManejadorListarVeterinario manejadorListarVeterinario) {
        this.manejadorListarVeterinario = manejadorListarVeterinario;
    }

    @GetMapping
    @ApiOperation("Listar veterinarios")
    public List<DtoVeterinario> listar() {
        return this.manejadorListarVeterinario.ejecutar();
    }
}
