package com.agencia.services;

import com.agencia.domain.Vaga;
import com.agencia.domain.dto.VagaDTO;
import com.agencia.domain.enums.Status;
import com.agencia.repository.VagaRepository;
import com.agencia.services.exceptions.ObjectnotFoundException;
import com.agencia.services.exceptions.VagaNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class VagaService {

    @Autowired
    private VagaRepository vagaRepository;

    public Vaga findById(Long id){
        Optional<Vaga> obj = vagaRepository.findById(id);
        return obj.orElseThrow(() -> new VagaNaoEncontradaException(id));
    }

    public List<Vaga> findAll() {
        return vagaRepository.findAll();
    }

    public Vaga create(VagaDTO obj) {
        return vagaRepository.save(newVaga(obj));
    }

    private Vaga newVaga(VagaDTO obj){
        Vaga vaga = new Vaga();

        String titulo = obj.getTitulo();
        String descricao = obj.getDescricao();
        String requisitos = obj.getRequisitos();
        LocalDateTime dataCriacao = obj.getDataCriacao();
        LocalDateTime dataAtualizacao = obj.getDataAtualizacao();
        Status status = obj.getStatus();

        if (obj.getId() != null){
            vaga.setId(obj.getId());
        }

        vaga.setTitulo(obj.getTitulo());
        vaga.setDescricao(obj.getDescricao());
        vaga.setRequisitos(obj.getRequisitos());
        vaga.setDataCriacao(obj.getDataCriacao());
        vaga.setDataAtualizacao(obj.getDataAtualizacao());
        vaga.setStatus(obj.getStatus());
        return vaga;
    }

    public Vaga update(Long id, VagaDTO objDTO){
        Vaga existeVaga = vagaRepository.findById(id)
                .orElseThrow(() -> new ObjectnotFoundException("Vaga não ecnotrado! ID: " + id));

        existeVaga.setTitulo(objDTO.getTitulo());
        existeVaga.setDescricao(objDTO.getDescricao());
        existeVaga.setRequisitos(objDTO.getRequisitos());
        existeVaga.setDataCriacao(objDTO.getDataCriacao());
        existeVaga.setDataAtualizacao(objDTO.getDataAtualizacao());

        return vagaRepository.save(existeVaga);
    }
}
