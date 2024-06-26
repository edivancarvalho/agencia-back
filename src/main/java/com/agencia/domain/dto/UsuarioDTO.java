package com.agencia.domain.dto;

import com.agencia.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioDTO implements Serializable {

    private Long id;
    private String nome;
    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCadastro;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime ultimaAtualizazao;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date dataFim;

    private boolean exclusaoLogica = false;

    public UsuarioDTO(Usuario usuario){
        this.id = usuario.getId();
        this.nome = usuario.getNome();
        this.email = usuario.getEmail();
        this.dataCadastro = usuario.getDataCadastro();
        this.ultimaAtualizazao = usuario.getUltimaAtualizazao();
        this.dataFim = usuario.getDataFim();
        this.exclusaoLogica = usuario.isExclusaoLogica();
    }
}