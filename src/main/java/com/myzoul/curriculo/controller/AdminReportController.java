package com.myzoul.curriculo.controller;

import com.myzoul.curriculo.mapper.CurriculoMapper;
import com.myzoul.curriculo.model.dto.ContagemDto;
import com.myzoul.curriculo.model.dto.CurriculoResponseDto;
import com.myzoul.curriculo.service.CurriculoService;
import com.myzoul.curriculo.service.AdminReportService;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/relatorios")
public class AdminReportController {
    private final CurriculoService service;
    private final AdminReportService adminReportService;

    public AdminReportController(CurriculoService service, AdminReportService adminReportService) {
        this.service = service;
        this.adminReportService = adminReportService;
    }

    @GetMapping("/escolaridade")
    public ContagemDto escolaridade() {
        return new ContagemDto(service.contarPorEscolaridade());
    }

    @GetMapping("/situacao")
    public ContagemDto situacao() {
        return new ContagemDto(service.contarPorSituacao());
    }

    @GetMapping("/candidatos")
    public Page<CurriculoResponseDto> listar(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return service.listarTodos(pageable).map(CurriculoMapper::toResponseDto);
    }

    @PutMapping("/candidatos/{id}/aprovar")
    public ResponseEntity<CurriculoResponseDto> aprovar(@PathVariable Long id) {
        return ResponseEntity.ok(adminReportService.atualizarStatus(id, "Aprovado"));
    }

    @PutMapping("/candidatos/{id}/reprovar")
    public ResponseEntity<CurriculoResponseDto> reprovar(@PathVariable Long id) {
        return ResponseEntity.ok(adminReportService.atualizarStatus(id, "Reprovado"));
    }
}