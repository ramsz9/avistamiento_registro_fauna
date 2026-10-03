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
}
