package com.codeitsolution.desafiotecnicocodegroup.controllers;

import com.codeitsolution.desafiotecnicocodegroup.entities.Pessoa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface PessoaResource {

    ResponseEntity<?> createMembro(@RequestBody Pessoa pessoa);

}


