package com.galaxy.training.backend.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galaxy.training.backend.dtos.in.EscuelaConductorRequestDto;
import com.galaxy.training.backend.dtos.out.EscuelaConductorResponseDto;
import com.galaxy.training.backend.hateoas.EscuelaConductorAssembler;
import com.galaxy.training.backend.services.EscuelaConductorService;
@RestController
@RequestMapping("/api/escuelas-conductores")
public class EscuelaConductorController {

    private final EscuelaConductorService escuelaConductorService;
    private final EscuelaConductorAssembler assembler;
    private final PagedResourcesAssembler<EscuelaConductorResponseDto> pagedResourcesAssembler;

    public EscuelaConductorController(EscuelaConductorService escuelaConductorService,
                                     EscuelaConductorAssembler assembler,
                                     PagedResourcesAssembler<EscuelaConductorResponseDto> pagedResourcesAssembler) {
        this.escuelaConductorService = escuelaConductorService;
        this.assembler = assembler;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<EscuelaConductorResponseDto>> getEscuelaConductorById(@PathVariable Integer id) {
        EscuelaConductorResponseDto dto = escuelaConductorService.getEscuelaConductorById(id);
        return ResponseEntity.ok(assembler.toModel(dto));
    }

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<EscuelaConductorResponseDto>>> getAllEscuelasConductoresPaged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<EscuelaConductorResponseDto> dtos = escuelaConductorService.getAllEscuelasConductoresPaged(pageable);
        PagedModel<EntityModel<EscuelaConductorResponseDto>> pagedModel = assembler.toPagedModel(dtos, pagedResourcesAssembler);
        return ResponseEntity.ok(pagedModel);
    }

    @PostMapping
    public ResponseEntity<EntityModel<EscuelaConductorResponseDto>> createEscuelaConductor(@RequestBody EscuelaConductorRequestDto dto) {
        EscuelaConductorResponseDto createdDto = escuelaConductorService.createEscuelaConductor(dto);
        return ResponseEntity.ok(assembler.toModel(createdDto));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteEscuelaConductor(@PathVariable Integer id) {
        escuelaConductorService.deleteEscuelaConductor(id);
        return ResponseEntity.noContent().build();
    }    

    @PutMapping("/{id}/update")
    public ResponseEntity<EntityModel<EscuelaConductorResponseDto>> updateEscuelaConductor(@PathVariable Integer id, @RequestBody EscuelaConductorRequestDto dto) {
        EscuelaConductorResponseDto updatedDto = escuelaConductorService.updateEscuelaConductor(id, dto);
        return ResponseEntity.ok(assembler.toModel(updatedDto));
    }
}
