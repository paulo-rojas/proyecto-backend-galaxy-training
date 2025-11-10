package com.galaxy.training.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxy.training.backend.entities.DistritoEntity;

@Repository
public interface DistritoRepository extends JpaRepository<DistritoEntity, Integer> {
   
    public List<DistritoEntity> findByProvinciaId(Integer provinciaId);
}
