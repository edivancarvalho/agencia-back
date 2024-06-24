package com.agencia.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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
}

//public enum Role {
//    ADMIN, USER
//}

