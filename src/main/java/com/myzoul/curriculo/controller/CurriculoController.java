package com.myzoul.curriculo.controller;

import com.myzoul.curriculo.infra.security.CurrentUser;
import com.myzoul.curriculo.model.UserEnt;
import com.myzoul.curriculo.model.dto.CurriculoCreateDto;
import com.myzoul.curriculo.model.dto.CurriculoResponseDto;
import com.myzoul.curriculo.model.dto.CurriculoStatusDto;
import com.myzoul.curriculo.model.dto.CurriculoUpdateDto;
import com.myzoul.curriculo.service.CurriculoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curriculos")
public class CurriculoController {
    private final CurriculoService service;

    public CurriculoController(CurriculoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CurriculoResponseDto> criar(@RequestBody @Valid CurriculoCreateDto dto, @CurrentUser UserEnt user) {
        return ResponseEntity.ok(service.criarCurriculoResponse(dto, user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CurriculoResponseDto> buscarPorId(@PathVariable Long id, @CurrentUser UserEnt user) {
        return ResponseEntity.ok(service.buscarCurriculoResponsePorIdUsuario(id, user.getCpf()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CurriculoResponseDto> atualizar(@PathVariable Long id, @RequestBody CurriculoUpdateDto dto, @CurrentUser UserEnt user) {
        return ResponseEntity.ok(service.atualizarCurriculoResponseParcial(id, dto, user.getCpf()));
    }

    @GetMapping("/status")
    public ResponseEntity<CurriculoStatusDto> getStatusCurriculo(@CurrentUser UserEnt user) {
        return ResponseEntity.ok(service.buscarStatusPorCpf(user.getCpf()));
    }

    @GetMapping("/meu")
    public ResponseEntity<CurriculoResponseDto> listarDoUsuario(@CurrentUser UserEnt user) {
        return service.buscarCurriculoResponsePorCpf(user.getCpf())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}