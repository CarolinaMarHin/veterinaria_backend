package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.mascota.modelo.dto.DtoMascota;
import com.ceiba.mascota.puerto.dao.DaoMascota;

import java.time.LocalDate;


public class ServicioCrearCita {

    private static final String LA_CITA_YA_EXISTE_EN_EL_SISTEMA = "La Cita ya existe en el sistema";
    private static final String EL_VETERINARIO_NO_PUEDE_SER_ASIGNADO = "El veterinario no puede ser asignado por que ya cumplio la cantidad permitida";

    private static final int PORCENTAJE_DESCUENTO_POR_CUMPLEANIOS = 15;

    private final RepositorioCita repositorioCita;
    private final DaoMascota daoMascota;

    public ServicioCrearCita(RepositorioCita repositorioMascota, DaoMascota daoMascota) {
        this.repositorioCita = repositorioMascota;
        this.daoMascota = daoMascota;

    }

    public Long ejecutar(Cita cita) {
        validarExistenciaPrevia(cita);
        validarCantidadDeCitasAsignadasVeterninario(cita.getIdVeterinario());
        cita = validarDescuentoPorCumpleaniosMascota(cita);
        cita = validarDiaDeBanioGratis(cita);

        return this.repositorioCita.crear(cita);
    }

    private Cita validarDescuentoPorCumpleaniosMascota(Cita cita) {
        DtoMascota mascota = daoMascota.listarPorId(cita.getCodigoMascota());
        double totalPagarConDescuento = cita.getTotalPago();

        if (mascota.getFechaNacimientoMascota().isEqual(LocalDate.now())) {
            totalPagarConDescuento -= (cita.getTotalPago() * PORCENTAJE_DESCUENTO_POR_CUMPLEANIOS) / 100;
        }
        return new Cita(cita.getId(),
                cita.getCodigoMascota(),
                cita.getIdVeterinario(),cita.getCodigoServicio(),
                totalPagarConDescuento,
                cita.getFecha());
    }

    private Cita validarDiaDeBanioGratis(Cita cita) {
        double totalPagarConDescuento = cita.getTotalPago();

        if (repositorioCita.aplicaBanioGratis(cita.getCodigoServicio())) {
            totalPagarConDescuento = 0;
        }
        return new Cita(cita.getId(),
                cita.getCodigoMascota(),
                cita.getIdVeterinario(),cita.getCodigoServicio(),
                totalPagarConDescuento,
                cita.getFecha());
    }

    private void validarExistenciaPrevia(Cita cita) {
        boolean existe = this.repositorioCita.existe(cita.getCodigoMascota());
        if (existe) {
            throw new ExcepcionDuplicidad(LA_CITA_YA_EXISTE_EN_EL_SISTEMA);
        }
    }

    private void validarCantidadDeCitasAsignadasVeterninario(Long idCita) {

        if (repositorioCita.cantidadCitasAsignadasVeterinario(idCita)) {
            throw new ExcepcionValorInvalido(EL_VETERINARIO_NO_PUEDE_SER_ASIGNADO);
        }
    }
}
