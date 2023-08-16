package com.codeitsolution.desafiotecnicocodegroup.services;

import com.codeitsolution.desafiotecnicocodegroup.entities.Projeto;
import com.codeitsolution.desafiotecnicocodegroup.entities.enums.Risco;
import com.codeitsolution.desafiotecnicocodegroup.entities.enums.StatusProjeto;
import com.codeitsolution.desafiotecnicocodegroup.repositories.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProjetoService {

    public List<Projeto> findAll();

    public Projeto findById(Long id);

    public Projeto save(Projeto projeto);

    public Risco determinarRisco(Projeto projeto);

    public Projeto mudarStatus(Long id, StatusProjeto novoStatus);

    public void delete(Long id);

    public boolean existsById(Long id);


}

