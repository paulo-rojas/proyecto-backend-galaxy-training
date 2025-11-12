package com.galaxy.training.backend.services;

import java.util.List;

import com.galaxy.training.backend.dtos.out.DepartamentoResponseDto;
import com.galaxy.training.backend.dtos.out.DistritoResponseDto;
import com.galaxy.training.backend.dtos.out.ProvinciaResponseDto;
import com.galaxy.training.backend.entities.DireccionEntity;

public interface DireccionService {

    public DireccionEntity createDireccion(String detalle, Integer distritoId);

    public List<DepartamentoResponseDto> getDepartamentos();

    public List<ProvinciaResponseDto> getProvinciasByDepartamentoId(Integer departamentoId);

    public List<DistritoResponseDto> getDistritosByProvinciaId(Integer provinciaId);
}
