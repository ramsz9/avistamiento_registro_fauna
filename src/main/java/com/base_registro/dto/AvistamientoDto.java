package com.base_registro.dto;

import java.time.LocalDate;

import com.base_registro.model.AvistamientoModel;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// id y fechaRegistro no van aquí: los genera la base de datos, no el cliente
public record AvistamientoDto(
        @NotBlank String especie,
        @NotBlank String ubicacionGeografica,
        @NotNull LocalDate fechaAvistamiento,
        String observaciones) {

    public AvistamientoModel toModel() {
        return AvistamientoModel.builder()
                .especie(especie)
                .ubicacionGeografica(ubicacionGeografica)
                .fechaAvistamiento(fechaAvistamiento)
                .observaciones(observaciones)
                .build();
    }
}
