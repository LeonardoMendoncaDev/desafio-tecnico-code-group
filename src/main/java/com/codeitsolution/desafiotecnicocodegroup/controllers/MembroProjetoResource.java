package com.codeitsolution.desafiotecnicocodegroup.controllers;

import com.codeitsolution.desafiotecnicocodegroup.entities.MembroProjeto;
import com.codeitsolution.desafiotecnicocodegroup.entities.Pessoa;
import com.codeitsolution.desafiotecnicocodegroup.entities.Projeto;
import com.codeitsolution.desafiotecnicocodegroup.entities.dtos.MembroProjetoDTO;
import com.codeitsolution.desafiotecnicocodegroup.services.PessoaService;
import com.codeitsolution.desafiotecnicocodegroup.services.ProjetoService;
import com.codeitsolution.desafiotecnicocodegroup.services.impls.MembroProjetoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface MembroProjetoResource {
    ResponseEntity<?> adicionarMembro(@PathVariable Long projetoId, @RequestBody MembroProjetoDTO membroDTO);
}

