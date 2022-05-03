package com.ceiba.configuracion;

import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioActualizarCita;
import com.ceiba.cita.servicio.ServicioCrearCita;
import com.ceiba.cita.servicio.ServicioEliminarCita;
import com.ceiba.mascota.puerto.dao.DaoMascota;
import com.ceiba.mascota.puerto.repositorio.RepositorioMascota;
import com.ceiba.mascota.servicio.ServicioActualizarMascota;
import com.ceiba.mascota.servicio.ServicioCrearMascota;
import com.ceiba.mascota.servicio.ServicioEliminarMascota;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioActualizarUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.ServicioEliminarUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearUsuario servicioCrearUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioCrearUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioEliminarUsuario servicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioEliminarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioActualizarUsuario servicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        return new ServicioActualizarUsuario(repositorioUsuario);
    }

    @Bean
    public ServicioCrearMascota servicioCrearMascota(RepositorioMascota repositorioMascota) {
        return new ServicioCrearMascota(repositorioMascota);
    }

    @Bean
    public ServicioEliminarMascota servicioEliminarMascota(RepositorioMascota repositorioMascota) {
        return new ServicioEliminarMascota(repositorioMascota);
    }

    @Bean
    public ServicioActualizarMascota servicioActualizarMascota(RepositorioMascota repositorioMascota) {
        return new ServicioActualizarMascota(repositorioMascota);
    }

    @Bean
    public ServicioCrearCita servicioCrearCita(RepositorioCita repositorioCita, DaoMascota daoMascota, DaoCita daoCita) {
        return new ServicioCrearCita(repositorioCita, daoMascota, daoCita);
    }

    @Bean
    public ServicioEliminarCita servicioEliminarCita(RepositorioCita repositorioCita) {
        return new ServicioEliminarCita(repositorioCita);
    }

    @Bean
    public ServicioActualizarCita servicioActualizarCita(RepositorioCita repositorioCita) {
        return new ServicioActualizarCita(repositorioCita);
    }

}
