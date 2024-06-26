package com.agencia.controller;

import com.agencia.domain.Vaga;
import com.agencia.domain.dto.VagaDTO;
import com.agencia.services.VagaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@PreAuthorize("hasRole('o_admin')")
@RequestMapping(value = "/api/vagas")
public class VagaController {

    @Autowired
    private VagaService vagaService;

    @GetMapping
    @PreAuthorize("hasAnyRole('o_vagas')")
    public ResponseEntity<List<VagaDTO>> findAll() {
        List<Vaga> list = vagaService.findAll();
        List<VagaDTO> listDTO = list.stream().map(obj -> new VagaDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('o_vagas')")
    public ResponseEntity<VagaDTO> findById(@PathVariable Long id) {
        Vaga obj = vagaService.findById(id);
        return ResponseEntity.ok().body(new VagaDTO(obj));
    }

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('o_vagas')")
    public ResponseEntity<VagaDTO> create(@Valid @RequestBody VagaDTO objDTO) {
        Vaga newObj = vagaService.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('o_vagas')")
    public ResponseEntity<VagaDTO> update(@PathVariable Long id, @Valid @RequestBody VagaDTO objDTO) {
        Vaga obj = vagaService.update(id, objDTO);
        return ResponseEntity.ok().body(new VagaDTO(obj));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAnyRole('o_vagas')")
    public ResponseEntity<VagaDTO> delete(@PathVariable Long id) {
        vagaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
