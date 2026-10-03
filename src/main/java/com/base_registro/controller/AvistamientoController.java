package com.base_registro.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.base_registro.dto.AvistamientoDto;
import com.base_registro.dto.AvistamientoRsDto;
import com.base_registro.service.AvistamientoService;

import jakarta.validation.Valid;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.base_registro.dto.AvistamientoConsultaDto;

@RestController
@RequestMapping("/avistamientos")
public class AvistamientoController {

    private final AvistamientoService avistamientoService;

    public AvistamientoController(AvistamientoService avistamientoService) {
        this.avistamientoService = avistamientoService;
    }

    @PostMapping
    public ResponseEntity<AvistamientoRsDto> save(@Valid @RequestBody AvistamientoDto dto) {
        return new ResponseEntity<>(avistamientoService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AvistamientoConsultaDto>> findAll(
            @RequestParam(required = false) String especie,
            @RequestParam(required = false) String ubicacion,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        return ResponseEntity.ok(avistamientoService.findAll(especie, ubicacion, fecha));
    }
}
