package com.ceiba.cita.modelo.entidad;


import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Cita {

    private static final String SE_DEBE_INGRESAR_EL_CODIGO_DE_LA_MASCOTA = "Se debe ingresar el codigo de la mascota";
    private static final String SE_DEBE_INGRESAR_EL_CODIGO_DEL_VETERINARIO = "Se debe ingresar el codigo de veterinario";
    private static final String SE_DEBE_INGRESAR_EL_CODIGO_DE_SERVICIO = "Se debe ingresar el codigo de servicio";
    private static final String SE_DEBE_INGRESAR_LA_FECHA = "Se debe ingresar la fecha";
    private static final String LO_SENTIMOS_NO_HAY_SERVICIO_LOS_FINES_DE_SEMANA= "Lo sentimos, no hay servicio los fines de semana";

    private Long id;
    private Long codigoMascota;
    private Long idVeterinario;
    private Long codigoServicio;
    private double totalPago;
    LocalDate fecha;

    public Cita(Long id, Long codigoMascota, Long idVeterinario, Long codigoServicio, double totalPago,
                LocalDate fecha
    ) {

        validarObligatorio(codigoMascota,SE_DEBE_INGRESAR_EL_CODIGO_DE_LA_MASCOTA);
        validarObligatorio(idVeterinario,SE_DEBE_INGRESAR_EL_CODIGO_DEL_VETERINARIO);
        validarObligatorio(codigoServicio,SE_DEBE_INGRESAR_EL_CODIGO_DE_SERVICIO);
        validarObligatorio(fecha,SE_DEBE_INGRESAR_LA_FECHA);

        validarEsFinDeSenama(fecha);


        this.id = id;
        this.codigoMascota = codigoMascota;
        this.idVeterinario = idVeterinario;
        this.codigoServicio = codigoServicio;
        this.totalPago = totalPago;
        this.fecha = fecha;
    }

    private static void validarEsFinDeSenama(LocalDate fecha) {
        if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY
                || fecha.getDayOfWeek() == DayOfWeek.SUNDAY
        ) {
            throw new ExcepcionValorInvalido(LO_SENTIMOS_NO_HAY_SERVICIO_LOS_FINES_DE_SEMANA);
        }
    }
}
