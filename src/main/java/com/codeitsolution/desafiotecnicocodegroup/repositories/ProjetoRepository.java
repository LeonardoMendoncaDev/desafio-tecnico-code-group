package com.codeitsolution.desafiotecnicocodegroup.repositories;

import com.codeitsolution.desafiotecnicocodegroup.entities.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}

