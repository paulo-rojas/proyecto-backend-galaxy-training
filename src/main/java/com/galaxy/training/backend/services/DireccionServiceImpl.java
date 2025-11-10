package com.galaxy.training.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.galaxy.training.backend.entities.DepartamentoEntity;
import com.galaxy.training.backend.entities.DistritoEntity;
import com.galaxy.training.backend.entities.ProvinciaEntity;
import com.galaxy.training.backend.repositories.DepartamentoRepository;
import com.galaxy.training.backend.repositories.DistritoRepository;
import com.galaxy.training.backend.repositories.ProvinciaRepository;

@Service
public class DireccionServiceImpl implements DireccionService {

    private final DepartamentoRepository departamentoRepository;
    private final ProvinciaRepository provinciaRepository;
    private final DistritoRepository distritoRepository;

    public DireccionServiceImpl(DepartamentoRepository departamentoRepository, ProvinciaRepository provinciaRepository, DistritoRepository distritoRepository) {
        this.departamentoRepository = departamentoRepository;
        this.provinciaRepository = provinciaRepository;
        this.distritoRepository = distritoRepository;
    }

    @Override
    public List<DepartamentoEntity> getDepartamentos() {
        return departamentoRepository.findAll();
    }

    @SuppressWarnings("null")
    @Override
    public List<ProvinciaEntity> getProvinciasByDepartamentoId(Integer departamentoId) {
        DepartamentoEntity departamento = departamentoRepository.findById(departamentoId)
            .orElseThrow(() -> new IllegalArgumentException("Departamento no encontrado con ID: " + departamentoId));

        List<ProvinciaEntity> provincias = provinciaRepository.findByDepartamentoId(departamento.getId());
        return provincias;
    }

    @SuppressWarnings("null")
    @Override
    public List<DistritoEntity> getDistritosByProvinciaId(Integer provinciaId) {
        ProvinciaEntity provincia = provinciaRepository.findById(provinciaId)
            .orElseThrow(() -> new IllegalArgumentException("Provincia no encontrada con ID: " + provinciaId));
        List<DistritoEntity> distritos = distritoRepository.findByProvinciaId(provincia.getId());
        return distritos;
    }

}
