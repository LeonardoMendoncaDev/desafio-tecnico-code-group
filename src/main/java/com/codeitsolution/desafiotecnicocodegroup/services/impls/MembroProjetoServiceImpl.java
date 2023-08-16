package com.codeitsolution.desafiotecnicocodegroup.services.impls;

import com.codeitsolution.desafiotecnicocodegroup.entities.MembroProjeto;
import com.codeitsolution.desafiotecnicocodegroup.repositories.MembroProjetoRepository;
import com.codeitsolution.desafiotecnicocodegroup.services.MembroProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembroProjetoServiceImpl implements MembroProjetoService {
    @Autowired
    private MembroProjetoRepository membroProjetoRepository;

    // O sistema deve permitir associar membros aos projetos que tem atribuição funcionário
    @Override
    public MembroProjeto save(MembroProjeto membroProjeto) {
        return membroProjetoRepository.save(membroProjeto);
    }

}

