package com.ceiba.usuario.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;

public class ServicioActualizarUsuario {

    private static final String EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El no usuario existe en el sistema";

    private final RepositorioUsuario repositorioUsuario;

    public ServicioActualizarUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public void ejecutar(Usuario usuario) {
        validarExistenciaPrevia(usuario);
        this.repositorioUsuario.actualizar(usuario);
    }

    private void validarExistenciaPrevia(Usuario usuario) {
        boolean existe = this.repositorioUsuario.existePorId(usuario.getId());
        if (!existe) {
            throw new ExcepcionDuplicidad(EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
