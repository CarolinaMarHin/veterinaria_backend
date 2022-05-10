package com.ceiba.veterinario.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DtoVeterinario {

    private Long id;
    private String nombreVeterinario;
    private String cedulaVeterinario;

}
