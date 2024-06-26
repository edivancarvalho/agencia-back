package com.agencia.repository;

import com.agencia.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    List<Usuario> findByExclusaoLogicaFalse();

    Optional<Usuario> findByIdAndExclusaoLogicaFalse(Long id);

}