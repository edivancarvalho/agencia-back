package com.agencia.repository;

import com.agencia.domain.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
}
