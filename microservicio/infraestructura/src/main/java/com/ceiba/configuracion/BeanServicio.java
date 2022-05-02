package com.ceiba.configuracion;

import com.ceiba.mascota.puerto.repositorio.RepositorioMacota;
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
    public ServicioCrearMascota servicioCrearMascota(RepositorioMacota repositorioMacota) {
        return new ServicioCrearMascota(repositorioMacota);
    }

    @Bean
    public ServicioEliminarMascota servicioEliminarMascota(RepositorioMacota repositorioMacota) {
        return new ServicioEliminarMascota(repositorioMacota);
    }

    @Bean
    public ServicioActualizarMascota servicioActualizarMascota(RepositorioMacota repositorioMacota) {
        return new ServicioActualizarMascota(repositorioMacota);
    }


}
