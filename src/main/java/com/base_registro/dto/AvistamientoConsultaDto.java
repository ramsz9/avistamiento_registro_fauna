package com.base_registro.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record AvistamientoConsultaDto(
        Long id,
        String especie,
        String ubicacionGeografica,
        LocalDate fechaAvistamiento,
        String observaciones,
        LocalDateTime fechaRegistro) {
}