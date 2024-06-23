package com.agencia.domain.dto;

import com.agencia.domain.Vaga;
import com.agencia.domain.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.awt.font.TextHitInfo;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VagaDTO implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    protected String titulo;
    protected String descricao;
    protected String requisitos;

    protected LocalDateTime dataCriacao;
    protected LocalDateTime dataAtualizacao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    protected Date dataFim;
    protected boolean exclusaoLogica = false;

    @Enumerated(EnumType.STRING)
    protected Status status;

    public VagaDTO(Vaga vaga){
        this.id = vaga.getId();
        this.titulo = vaga.getTitulo();
        this.descricao = vaga.getDescricao();
        this.requisitos = vaga.getRequisitos();
        this.dataCriacao = vaga.getDataCriacao();
        this.dataAtualizacao = vaga.getDataAtualizacao();
        this.dataFim = vaga.getDataFim();
        this.status = vaga.getStatus();
        this.exclusaoLogica = vaga.isExclusaoLogica();

    }
}
