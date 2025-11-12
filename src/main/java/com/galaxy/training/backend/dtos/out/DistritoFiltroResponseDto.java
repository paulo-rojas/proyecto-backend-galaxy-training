package com.galaxy.training.backend.dtos.out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistritoFiltroResponseDto {

    private Integer distritoId;
    private String posibleDireccion; // "La Libertad, Trujillo, Trujillo"

}
