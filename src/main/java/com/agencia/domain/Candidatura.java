package com.agencia.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;


    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    protected LocalDateTime dataAtualizacao;

    protected boolean exclusaoLogica = false;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCandidatura;

    @PrePersist
    protected void prePersist() {
        this.dataCandidatura = LocalDateTime.now();
    }
}
