package com.agencia.domain.dto;

import com.agencia.domain.Vaga;
import com.agencia.domain.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.awt.font.TextHitInfo;
import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VagaDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private String requisitos;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    @Enumerated(EnumType.STRING)
    private Status status;

    public VagaDTO(Vaga vaga){
        this.id = vaga.getId();
        this.titulo = vaga.getTitulo();
        this.descricao = vaga.getDescricao();
        this.requisitos = vaga.getRequisitos();
        this.dataCriacao = vaga.getDataCriacao();
        this.dataAtualizacao = vaga.getDataAtualizacao();
        this.status = vaga.getStatus();
    }
}
