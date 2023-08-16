package com.codeitsolution.desafiotecnicocodegroup.services;

import com.codeitsolution.desafiotecnicocodegroup.entities.MembroProjeto;
import com.codeitsolution.desafiotecnicocodegroup.repositories.MembroProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface MembroProjetoService {

    public MembroProjeto save(MembroProjeto membroProjeto);
    
}

