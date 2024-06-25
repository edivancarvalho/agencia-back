package com.agencia.domain.dto;

import com.agencia.domain.Candidatura;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CandidaturaDTO {

    protected Long id;

    protected Long usuarioId; // Atributo para o ID do usuário

    protected VagaDTO vaga;

    protected UsuarioDTO usuario; // DTO do usuário

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime dataCandidatura;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime dataAtualizacao;

    protected boolean exclusaoLogica = false;

    public CandidaturaDTO(Candidatura candidatura) {
        this.id = candidatura.getId();
        this.usuarioId = candidatura.getUsuario().getId(); // Preenche o ID do usuário
        this.usuario = new UsuarioDTO(candidatura.getUsuario()); // Preenche o DTO do usuário
        this.vaga = new VagaDTO(candidatura.getVaga());
        this.dataCandidatura = candidatura.getDataCandidatura();
        this.dataAtualizacao = candidatura.getDataAtualizacao();
        this.exclusaoLogica = candidatura.isExclusaoLogica();
    }
}