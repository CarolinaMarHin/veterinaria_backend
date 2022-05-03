package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.mascota.modelo.dto.DtoMascota;
import com.ceiba.mascota.puerto.dao.DaoMascota;

import java.time.LocalDate;


public class ServicioCrearCita {

    private static final String EL_VETERINARIO_NO_PUEDE_SER_ASIGNADO = "El veterinario no puede ser asignado por que ya cumplio la cantidad permitida";
    private static final int CITAS_PERMITIDAS_POR_VETERINARIO_AL_DIA = 4;
    private static final int PORCENTAJE_DESCUENTO_POR_CUMPLEANIOS = 15;
    private static final int NUMERO_DE_BANIOS = 5;

    private final RepositorioCita repositorioCita;
    private final DaoMascota daoMascota;
    private final DaoCita daoCita;

    public ServicioCrearCita(RepositorioCita repositorioMascota, DaoMascota daoMascota, DaoCita daoCita) {
        this.repositorioCita = repositorioMascota;
        this.daoMascota = daoMascota;
        this.daoCita = daoCita;
    }

    public Long ejecutar(Cita cita) {
        validarCantidadDeCitasAsignadasVeterninario(cita);
        cita = validarDescuentoPorCumpleaniosMascota(cita);
        cita = validarDiaDeBanioGratis(cita);

        return this.repositorioCita.crear(cita);
    }

    private Cita validarDescuentoPorCumpleaniosMascota(Cita cita) {
        DtoMascota mascota = daoMascota.listarPorId(cita.getCodigoMascota());
        double totalPagarConDescuento = cita.getTotalPago();

        if (cita.getFecha().toLocalDate().getMonth() == mascota.getFechaNacimientoMascota().getMonth()
                && cita.getFecha().toLocalDate().getDayOfMonth() == mascota.getFechaNacimientoMascota().getDayOfMonth()) {
            totalPagarConDescuento -= (cita.getTotalPago() * PORCENTAJE_DESCUENTO_POR_CUMPLEANIOS) / 100;
        }
        return new Cita(cita.getId(),
                cita.getCodigoMascota(),
                cita.getIdVeterinario(), cita.getCodigoServicio(),
                totalPagarConDescuento,
                cita.getFecha());
    }

    private Cita validarDiaDeBanioGratis(Cita cita) {
        double totalPagarConDescuento = cita.getTotalPago();
        int numeroServicioBanio = daoCita.listarCitasPorIdMascota(cita.getCodigoMascota()).size();

        if (numeroServicioBanio == NUMERO_DE_BANIOS) {
            totalPagarConDescuento = 0;
        }
        return new Cita(cita.getId(),
                cita.getCodigoMascota(),
                cita.getIdVeterinario(), cita.getCodigoServicio(),
                totalPagarConDescuento,
                cita.getFecha());
    }

    private void validarCantidadDeCitasAsignadasVeterninario(Cita cita) {
        int cantidadCitas = daoCita.listarCitasPorVeterinario(cita.getIdVeterinario(), cita.getFecha()).size();
        if (cantidadCitas == CITAS_PERMITIDAS_POR_VETERINARIO_AL_DIA) {
            throw new ExcepcionValorInvalido(EL_VETERINARIO_NO_PUEDE_SER_ASIGNADO);
        }
    }
}
