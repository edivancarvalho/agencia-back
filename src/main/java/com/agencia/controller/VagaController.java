package com.agencia.controller;

import com.agencia.domain.Vaga;
import com.agencia.domain.dto.VagaDTO;
import com.agencia.services.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/vagas")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @GetMapping
    public ResponseEntity<List<VagaDTO>> findAll() {
        List<Vaga> list = vagaService.findAll();
        List<VagaDTO> listDTO = list.stream().map(obj -> new VagaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<VagaDTO> findById(@PathVariable Long id) {
        Vaga obj = vagaService.findById(id);
        return ResponseEntity.ok().body(new VagaDTO(obj));
    }

    @PostMapping("/")
    public ResponseEntity<VagaDTO> create(@Valid @RequestBody VagaDTO objDTO) {
        Vaga newObj = vagaService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
