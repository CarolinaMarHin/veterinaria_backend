package com.ceiba.veterinario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoVeterinario {

    private Long id;
    private String nombreVeterinario;
    private String cedulaVeterinario;

}
