package com.agencia.services;

import com.agencia.domain.Candidatura;
import com.agencia.domain.Usuario;
import com.agencia.domain.Vaga;
import com.agencia.domain.dto.CandidaturaDTO;
import com.agencia.domain.dto.UsuarioDTO;
import com.agencia.domain.dto.VagaDTO;
import com.agencia.repository.CandidaturaRepository;
import com.agencia.repository.UsuarioRepository;
import com.agencia.repository.VagaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidaturaService {

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VagaRepository vagaRepository;

    public List<CandidaturaDTO> findAll() {
        List<Candidatura> candidaturas = candidaturaRepository.findAll();
        return candidaturas.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public Candidatura create(CandidaturaDTO candidaturaDTO) {
        // Buscar o usuário pelo ID
        Usuario usuario = usuarioRepository.findById(candidaturaDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID: " + candidaturaDTO.getUsuarioId()));

        // Buscar a vaga pelo ID
        Vaga vaga = vagaRepository.findById(candidaturaDTO.getVaga().getId())
                .orElseThrow(() -> new RuntimeException("Vaga não encontrada com o ID: " + candidaturaDTO.getVaga().getId()));

        // Criar a entidade Candidatura
        Candidatura candidatura = new Candidatura();
        candidatura.setUsuario(usuario);
        candidatura.setVaga(vaga);
        candidatura.setDataCandidatura(LocalDateTime.now());
        candidatura.setDataAtualizacao(LocalDateTime.now());
        candidatura.setExclusaoLogica(false);

        // Salvar a candidatura
        return candidaturaRepository.save(candidatura);
    }

    private CandidaturaDTO mapToDTO(Candidatura candidatura) {
        return CandidaturaDTO.builder()
                .id(candidatura.getId())
                .usuarioId(candidatura.getUsuario().getId())
                .usuario(new UsuarioDTO(candidatura.getUsuario()))
                .vaga(new VagaDTO(candidatura.getVaga()))
                .dataCandidatura(candidatura.getDataCandidatura())
                .dataAtualizacao(candidatura.getDataAtualizacao())
                .exclusaoLogica(candidatura.isExclusaoLogica())
                .build();
    }
}