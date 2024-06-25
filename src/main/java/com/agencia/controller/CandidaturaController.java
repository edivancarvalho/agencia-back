package com.agencia.controller;

import com.agencia.domain.Candidatura;
import com.agencia.domain.dto.CandidaturaDTO;
import com.agencia.services.CandidaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidaturas")
public class CandidaturaController {

    @Autowired
    private CandidaturaService candidaturaService;

    @GetMapping
    public ResponseEntity<List<CandidaturaDTO>> findAll() {
        List<CandidaturaDTO> candidaturas = candidaturaService.findAll();
        return ResponseEntity.ok(candidaturas);
    }

    @PostMapping("/")
    public ResponseEntity<CandidaturaDTO> create(@RequestBody CandidaturaDTO candidaturaDTO) {
        Candidatura candidatura = candidaturaService.create(candidaturaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new CandidaturaDTO(candidatura));
    }


}
