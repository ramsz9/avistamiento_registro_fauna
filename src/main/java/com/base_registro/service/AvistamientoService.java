package com.base_registro.service;

import org.springframework.stereotype.Service;

import com.base_registro.dto.AvistamientoDto;
import com.base_registro.dto.AvistamientoRsDto;
import com.base_registro.repository.AvistamientoRepository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;

import com.base_registro.dto.AvistamientoConsultaDto;
import com.base_registro.model.AvistamientoModel;

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

    public List<AvistamientoConsultaDto> findAll(String especie, String ubicacion, LocalDate fecha) {
        AvistamientoModel filtro = AvistamientoModel.builder()
                .especie(especie)
                .ubicacionGeografica(ubicacion)
                .fechaAvistamiento(fecha)
                .build();
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        return avistamientoRepository
                .findAll(Example.of(filtro, matcher), Sort.by("fechaAvistamiento").descending())
                .stream().map(AvistamientoModel::toDto).toList();
    }

}
