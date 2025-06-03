package com.myzoul.curriculo.controller;

import com.myzoul.curriculo.mapper.CurriculoMapper;
import com.myzoul.curriculo.model.CurriculoEnt;
import com.myzoul.curriculo.model.dto.CurriculoCreateDto;
import com.myzoul.curriculo.model.dto.CurriculoUpdateDto;
import com.myzoul.curriculo.service.CurriculoService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curriculos")
public class CurriculoController {
    private final CurriculoService service;

    public CurriculoController(CurriculoService service) {
        this.service = service;
    }

    @PostMapping
    public CurriculoEnt criar(@RequestBody @Valid CurriculoCreateDto dto) {
        CurriculoEnt entidade = CurriculoMapper.toEntity(dto);
        return service.salvar(entidade);
    }

    @GetMapping
    public Page<CurriculoEnt> listar(@ParameterObject @PageableDefault(size = 10) Pageable pageable) {
        return service.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    public CurriculoEnt buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    @PutMapping("/{id}")
    public CurriculoEnt atualizar(@PathVariable Long id, @RequestBody CurriculoUpdateDto dto) {
        return service.atualizarParcial(id, dto);
    }
}