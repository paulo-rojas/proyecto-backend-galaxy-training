package com.galaxy.training.backend.services;

import java.util.List;

import com.galaxy.training.backend.entities.DepartamentoEntity;
import com.galaxy.training.backend.entities.DistritoEntity;
import com.galaxy.training.backend.entities.ProvinciaEntity;

public interface DireccionService {

    public List<DepartamentoEntity> getDepartamentos();

    public List<ProvinciaEntity> getProvinciasByDepartamentoId(Integer departamentoId);

    public List<DistritoEntity> getDistritosByProvinciaId(Integer provinciaId);
}
