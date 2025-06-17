package com.myzoul.curriculo.service;

import com.myzoul.curriculo.mapper.CurriculoMapper;
import com.myzoul.curriculo.model.dto.CurriculoResponseDto;
import org.springframework.stereotype.Service;

@Service
public class AdminReportService {
    private final CurriculoService curriculoService;

    public AdminReportService(CurriculoService curriculoService) {
        this.curriculoService = curriculoService;
    }

    public CurriculoResponseDto atualizarStatus(Long id, String status) {
        var atualizado = curriculoService.atualizarStatus(id, status);
        return CurriculoMapper.toResponseDto(atualizado);
    }
}