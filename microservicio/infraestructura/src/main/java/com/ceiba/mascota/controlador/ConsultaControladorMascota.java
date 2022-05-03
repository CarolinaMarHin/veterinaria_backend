package com.ceiba.mascota.controlador;

import com.ceiba.mascota.consulta.ManejadorListarMascotas;
import com.ceiba.mascota.consulta.ManejadorListarMascotasCodigoMascota;
import com.ceiba.mascota.consulta.ManejadorListarMascotasPorId;
import com.ceiba.mascota.modelo.dto.DtoMascota;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mascotas")
@Api(tags = {"Controlador consulta mascota"})
public class ConsultaControladorMascota {

    private final ManejadorListarMascotas manejadorListarMascotas;
    private final ManejadorListarMascotasCodigoMascota manejadorListarMascotasCodigoMascota;

    private final ManejadorListarMascotasPorId manejadorListarMascotasPorId;

    public ConsultaControladorMascota(ManejadorListarMascotas manejadorListarMascotas,
                                      ManejadorListarMascotasCodigoMascota manejadorListarMascotasCodigoMascota,
                                      ManejadorListarMascotasPorId manejadorListarMascotasPorId) {
        this.manejadorListarMascotas = manejadorListarMascotas;
        this.manejadorListarMascotasCodigoMascota = manejadorListarMascotasCodigoMascota;
        this.manejadorListarMascotasPorId = manejadorListarMascotasPorId;
    }

    @GetMapping
    @ApiOperation("Listar mascota")
    public List<DtoMascota> listar() {
        return this.manejadorListarMascotas.ejecutar();
    }

    @GetMapping(value = "/{codigoMascota}")
    @ApiOperation("Listar mascota por codigo")
    public DtoMascota listarPorCodigoMascota(@PathVariable String codigoMascota) {
        return this.manejadorListarMascotasCodigoMascota.ejecutar(codigoMascota);
    }

    @GetMapping(value = "/obtenerId/{idMascota}")
    @ApiOperation("Listar mascota por Id")
    public DtoMascota listarPorIdMascota(@PathVariable Long idMascota) {
        return this.manejadorListarMascotasPorId.ejecutar(idMascota);
    }
}
