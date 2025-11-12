package com.galaxy.training.backend.services;

import com.galaxy.training.backend.dtos.in.EscuelaConductorRequestDto;
import com.galaxy.training.backend.dtos.out.EscuelaConductorResponseDto;


public interface EscuelaConductorService {

    public EscuelaConductorResponseDto createEscuelaConductor(EscuelaConductorRequestDto escuelaConductorRequestDto);

    public void deleteEscuelaConductor(Integer id);

    public EscuelaConductorResponseDto updateEscuelaConductor(Integer id, EscuelaConductorRequestDto escuelaConductorRequestDto);

    public EscuelaConductorResponseDto getEscuelaConductorById(Integer id);
}
