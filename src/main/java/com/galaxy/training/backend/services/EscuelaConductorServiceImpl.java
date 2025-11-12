package com.galaxy.training.backend.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.galaxy.training.backend.dtos.in.EscuelaConductorRequestDto;
import com.galaxy.training.backend.dtos.out.EscuelaConductorResponseDto;
import com.galaxy.training.backend.entities.DireccionEntity;
import com.galaxy.training.backend.entities.EscuelaConductorEntity;
import com.galaxy.training.backend.exceptions.EscuelaConductorDuplicadoException;
import com.galaxy.training.backend.exceptions.EscuelaConductorNoExistenteException;
import com.galaxy.training.backend.mappers.EscuelaConductorMapper;
import com.galaxy.training.backend.repositories.EscuelaConductorRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Service
public class EscuelaConductorServiceImpl implements EscuelaConductorService {

    private final EscuelaConductorRepository escuelaConductorRepository;
    private final DireccionService direccionService;
    private final EscuelaConductorMapper escuelaConductorMapper;

    public EscuelaConductorServiceImpl(EscuelaConductorRepository escuelaConductorRepository, EscuelaConductorMapper escuelaConductorMapper, DireccionService direccionService) {
        this.escuelaConductorRepository = escuelaConductorRepository;
        this.escuelaConductorMapper = escuelaConductorMapper;
        this.direccionService = direccionService;
    }

    
    @Transactional
    @Override
    public EscuelaConductorResponseDto createEscuelaConductor(@Valid EscuelaConductorRequestDto dto) {

        if (escuelaConductorRepository.existsByRuc(dto.getRuc())) {
            throw new EscuelaConductorDuplicadoException("El RUC ya está registrado: " + dto.getRuc());
        }
        
        DireccionEntity direccion = direccionService.createDireccion(dto.getDireccion(), dto.getDistritoId());

        EscuelaConductorEntity escuelaConductorEntity = escuelaConductorMapper.toEntity(dto, direccion);
        escuelaConductorEntity = escuelaConductorRepository.save(escuelaConductorEntity);
        return escuelaConductorMapper.toDto(escuelaConductorEntity);
    }

    @Transactional
    @Override
    public void deleteEscuelaConductor(@Valid Integer escuelaId) {
        EscuelaConductorEntity escuelaConductorEntity = escuelaConductorRepository.findById(escuelaId)
            .orElseThrow(() -> new EscuelaConductorNoExistenteException("Escuela de Conductor no encontrada con ID: " + escuelaId));

        escuelaConductorEntity.setEstado("Inactivo");
    }

    @Override
    public EscuelaConductorResponseDto updateEscuelaConductor(@Valid Integer id,
            @Valid EscuelaConductorRequestDto escuelaConductorRequestDto) {
        
        EscuelaConductorEntity escuelaConductorEntity = escuelaConductorRepository.findById(id)
            .orElseThrow(() -> new EscuelaConductorNoExistenteException("Escuela de Conductor no encontrada con ID: " + id));

        escuelaConductorEntity.setNombreEstablecimiento(escuelaConductorRequestDto.getNombreEstablecimiento());
        escuelaConductorEntity.setRuc(escuelaConductorRequestDto.getRuc());
        escuelaConductorEntity.setEstado(escuelaConductorRequestDto.getEstado());
        escuelaConductorEntity = escuelaConductorRepository.save(escuelaConductorEntity);
        return escuelaConductorMapper.toDto(escuelaConductorEntity);
    }

    @Override
    public EscuelaConductorResponseDto getEscuelaConductorById(@Valid Integer id) {
        EscuelaConductorEntity escuelaConductorEntity = escuelaConductorRepository.findById(id)
            .orElseThrow(() -> new EscuelaConductorNoExistenteException("Escuela de Conductor no encontrada con ID: " + id));
        return escuelaConductorMapper.toDto(escuelaConductorEntity);
    }


    @Override
    public Page<EscuelaConductorResponseDto> getAllEscuelasConductoresPaged(Pageable pageable) {
        return escuelaConductorRepository.findAll(pageable)
            .map(escuelaConductorMapper::toDto);
    }

}
