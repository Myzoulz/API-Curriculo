package com.myzoul.curriculo.controller;

import com.myzoul.curriculo.mapper.CurriculoMapper;
import com.myzoul.curriculo.model.dto.CurriculoCreateDto;
import com.myzoul.curriculo.model.dto.CurriculoResponseDto;
import com.myzoul.curriculo.model.dto.CurriculoStatusDto;
import com.myzoul.curriculo.model.dto.CurriculoUpdateDto;
import com.myzoul.curriculo.service.CurriculoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@RestController
@RequestMapping("/curriculos")
public class CurriculoController {
    private final CurriculoService service;

    public CurriculoController(CurriculoService service) {
        this.service = service;
    }

    @PostMapping
    public CurriculoResponseDto criar(@RequestBody @Valid CurriculoCreateDto dto, Authentication authentication) {
        var user = (com.myzoul.curriculo.model.UserEnt) authentication.getPrincipal();
        var entidade = CurriculoMapper.toEntity(dto);
        entidade.setCpf(user.getCpf());
        return CurriculoMapper.toResponseDto(service.salvar(entidade));
    }

    @GetMapping("/{id}")
    public CurriculoResponseDto buscarPorId(@PathVariable Long id, Authentication authentication) {
        var user = (com.myzoul.curriculo.model.UserEnt) authentication.getPrincipal();
        var entidade = service.buscarPorIdUsuario(id, user.getCpf());
        return CurriculoMapper.toResponseDto(entidade);
    }

    @PutMapping("/{id}")
    public CurriculoResponseDto atualizar(@PathVariable Long id, @RequestBody CurriculoUpdateDto dto, Authentication authentication) {
        var user = (com.myzoul.curriculo.model.UserEnt) authentication.getPrincipal();
        var entidade = service.atualizarParcial(id, dto, user.getCpf());
        return CurriculoMapper.toResponseDto(entidade);
    }

    @GetMapping("/status")
    public ResponseEntity<CurriculoStatusDto> getStatusCurriculo(Authentication authentication) {
        var user = (com.myzoul.curriculo.model.UserEnt) authentication.getPrincipal();
        CurriculoStatusDto statusDto = service.buscarStatusPorCpf(user.getCpf());
        return ResponseEntity.ok(statusDto);
    }

    @GetMapping("/meu")
    public ResponseEntity<CurriculoResponseDto> listarDoUsuario(Authentication authentication) {
        var user = (com.myzoul.curriculo.model.UserEnt) authentication.getPrincipal();
        return service.buscarCurriculoPorCpf(user.getCpf())
                .map(CurriculoMapper::toResponseDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}