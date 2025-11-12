package com.galaxy.training.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.training.backend.dtos.in.EscuelaConductorRequestDto;
import com.galaxy.training.backend.dtos.out.EscuelaConductorResponseDto;
import com.galaxy.training.backend.services.EscuelaConductorService;

@RestController
@RequestMapping("/api/escuelas-conductores")
public class EscuelaConductorController {

    private final EscuelaConductorService escuelaConductorService;

    public EscuelaConductorController(EscuelaConductorService escuelaConductorService) {
        this.escuelaConductorService = escuelaConductorService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EscuelaConductorResponseDto> getEscuelaConductorById(@PathVariable Integer id) {
        return ResponseEntity.ok(escuelaConductorService.getEscuelaConductorById(id));
    }

    @PostMapping
    public ResponseEntity<EscuelaConductorResponseDto> createEscuelaConductor(@RequestBody EscuelaConductorRequestDto dto) {
        return ResponseEntity.ok(escuelaConductorService.createEscuelaConductor(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEscuelaConductor(@PathVariable Integer id) {
        escuelaConductorService.deleteEscuelaConductor(id);
        return ResponseEntity.noContent().build();
    }    

    @PutMapping("/{id}")
    public ResponseEntity<EscuelaConductorResponseDto> updateEscuelaConductor(@PathVariable Integer id, @RequestBody EscuelaConductorRequestDto dto) {
        return ResponseEntity.ok(escuelaConductorService.updateEscuelaConductor(id, dto));
    }
}
