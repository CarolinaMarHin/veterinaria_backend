package com.ceiba.mascota.modelo.entidad;


import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;

import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
public class Mascota {

    private static final String SE_DEBE_INGRESAR_EL_CODIGO_DE_LA_MASCOTA = "Se debe ingresar el codigo de la mascota";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_LA_MASCOTA = "Se debe ingresar el nombre de la mascota";
    private static final String SE_DEBE_INGRESAR_LA_RAZA_DE_LA_MASCOTA = "Se debe ingresar la raza de la mascota";
    private static final String SE_DEBESE_DEBE_INGRESAR_LA_FECHA_DE_NACIMIENTO_DE_LA_MASCOTA = "Se debe ingresar la fecha de nacimiento de la mascota";
    private static final String SE_DEBE_INGRESAR_EL_PESO_DE_LA_MASCOTA = "Se debe ingresar el peso de la mascota";

    private Long id;
    private String codigoMascota;
    private String nombreMascota;
    private String raza;
    private LocalDate fechaNacimientoMascota;
    private int peso;

    public Mascota(Long id, String codigoMascota,
                   String nombreMascota, String raza,
                   LocalDate fechaNacimientoMascota, int peso
    ) {

        validarObligatorio(codigoMascota, SE_DEBE_INGRESAR_EL_CODIGO_DE_LA_MASCOTA);
        validarObligatorio(nombreMascota, SE_DEBE_INGRESAR_EL_NOMBRE_DE_LA_MASCOTA);
        validarObligatorio(raza, SE_DEBE_INGRESAR_LA_RAZA_DE_LA_MASCOTA);
        validarObligatorio(fechaNacimientoMascota, SE_DEBESE_DEBE_INGRESAR_LA_FECHA_DE_NACIMIENTO_DE_LA_MASCOTA);
        validarPeso(peso, SE_DEBE_INGRESAR_EL_PESO_DE_LA_MASCOTA);

        this.id = id;
        this.codigoMascota = codigoMascota;
        this.nombreMascota = nombreMascota;
        this.raza = raza;
        this.fechaNacimientoMascota = fechaNacimientoMascota;
        this.peso = peso;
    }

    private static void validarPeso(int peso, String mensaje) {
        if (peso == 0) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

}
