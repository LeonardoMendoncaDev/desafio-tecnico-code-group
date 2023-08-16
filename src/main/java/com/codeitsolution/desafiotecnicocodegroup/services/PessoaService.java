package com.codeitsolution.desafiotecnicocodegroup.services;

import com.codeitsolution.desafiotecnicocodegroup.entities.Pessoa;
import org.springframework.stereotype.Service;

public interface PessoaService {
    public Pessoa save(Pessoa pessoa);

    Pessoa findById(Long pessoaId);
}
