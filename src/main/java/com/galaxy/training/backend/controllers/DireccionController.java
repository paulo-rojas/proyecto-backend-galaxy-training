package com.galaxy.training.backend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.training.backend.entities.DepartamentoEntity;
import com.galaxy.training.backend.entities.DistritoEntity;
import com.galaxy.training.backend.entities.ProvinciaEntity;
import com.galaxy.training.backend.services.DireccionService;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionController {

    private final DireccionService direccionService;

    public DireccionController(DireccionService direccionService) {
        this.direccionService = direccionService;
    }

    @GetMapping("/departamentos")
    public ResponseEntity<List<DepartamentoEntity>> getDepartamentos() {
        return ResponseEntity.ok(direccionService.getDepartamentos());
    }

    @GetMapping("/provincias")
    public ResponseEntity<List<ProvinciaEntity>> getProvinciasByDepartamentoId(@RequestParam(name = "departamentoId") Integer departamentoId) {
        List <ProvinciaEntity> provincias = direccionService.getProvinciasByDepartamentoId(departamentoId);
        return provincias.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(provincias);
    }

    @GetMapping("/distritos")
    public ResponseEntity<List<DistritoEntity>> getDistritosByProvinciaId(@RequestParam(name = "provinciaId") Integer provinciaId) {
        List<DistritoEntity> distritos = direccionService.getDistritosByProvinciaId(provinciaId);
        return distritos.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(distritos);
    }
}