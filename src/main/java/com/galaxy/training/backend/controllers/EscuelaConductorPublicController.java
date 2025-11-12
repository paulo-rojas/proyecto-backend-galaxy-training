package com.galaxy.training.backend.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.training.backend.dtos.out.EscuelaConductorResponseDto;
import com.galaxy.training.backend.services.EscuelaConductorService;

@RestController
@RequestMapping("/api/public/escuelas")
public class EscuelaConductorPublicController {

    private final EscuelaConductorService escuelaConductorService;

    public EscuelaConductorPublicController(EscuelaConductorService escuelaConductorService) {
        this.escuelaConductorService = escuelaConductorService;
    }

    @GetMapping
    public ResponseEntity<Page<EscuelaConductorResponseDto>> getAllEscuelasConductoresPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getAllEscuelasConductoresPaged(pageable);
        return ResponseEntity.ok(dtos);
    }
    
}