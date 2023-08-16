package com.codeitsolution.desafiotecnicocodegroup.repositories;

import com.codeitsolution.desafiotecnicocodegroup.entities.MembroProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembroProjetoRepository extends JpaRepository<MembroProjeto, Long> {
}

