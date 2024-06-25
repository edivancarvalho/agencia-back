package com.agencia.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.Date;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String senha;

//    @Enumerated(EnumType.STRING)
//    private Role role;

    private LocalDateTime dataCadastro;
    private LocalDateTime ultimaAtualizazao;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    protected Date dataFim;

    @Column(nullable = false)
    protected boolean exclusaoLogica = false;

    @JsonGetter
    public boolean isAtivo() {
        return getDataFim() == null || getDataFim().compareTo(new Date()) > 0;
    }

    @PrePersist
    protected void prePersist() {
        this.dataCadastro = LocalDateTime.now();
        this.ultimaAtualizazao = LocalDateTime.now();
    }

    @PreUpdate
    protected void preUpdate() {
        this.ultimaAtualizazao = LocalDateTime.now();
    }
}

//public enum Role {
//    ADMIN, USER
//}

