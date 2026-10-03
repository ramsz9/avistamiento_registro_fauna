package com.base_registro.service;

import org.springframework.stereotype.Service;

import com.base_registro.dto.AvistamientoDto;
import com.base_registro.dto.AvistamientoRsDto;
import com.base_registro.repository.AvistamientoRepository;

@Service
public class AvistamientoService {

    private final AvistamientoRepository avistamientoRepository;

    public AvistamientoService(AvistamientoRepository avistamientoRepository) {
        this.avistamientoRepository = avistamientoRepository;
    }

    public AvistamientoRsDto create(AvistamientoDto dto) {
        Long id = avistamientoRepository.save(dto.toModel()).getId();
        return new AvistamientoRsDto("Avistamiento registrado", id);
    }
}
