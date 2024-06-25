package com.agencia.repository;

import com.agencia.domain.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VagaRepository  extends JpaRepository<Vaga, Long> {

    List<Vaga> findByExclusaoLogicaFalse();

    Optional<Vaga> findByIdAndExclusaoLogicaFalse(Long id);
}
