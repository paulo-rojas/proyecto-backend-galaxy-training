package com.galaxy.training.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxy.training.backend.entities.EscuelaConductorEntity;

@Repository
public interface EscuelaConductorRepository extends JpaRepository<EscuelaConductorEntity, Integer> {

    public boolean existsByRuc(String ruc);

}
