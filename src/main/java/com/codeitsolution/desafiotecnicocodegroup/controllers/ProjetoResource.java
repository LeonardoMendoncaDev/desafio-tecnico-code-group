package com.codeitsolution.desafiotecnicocodegroup.controllers;

import com.codeitsolution.desafiotecnicocodegroup.entities.Projeto;
import com.codeitsolution.desafiotecnicocodegroup.entities.enums.Risco;
import com.codeitsolution.desafiotecnicocodegroup.services.impls.ProjetoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface ProjetoResource {

    ResponseEntity<?> getAllProjetos();

    ResponseEntity<?> getProjetoById(@PathVariable Long id);

    ResponseEntity<?> createProjeto(@RequestBody Projeto projeto);

    ResponseEntity<?> updateProjeto(@PathVariable Long id, @RequestBody Projeto projeto);

    ResponseEntity<?> deleteProjeto(@PathVariable Long id);

    ResponseEntity<Risco> getRiscoDoProjeto(@PathVariable Long id);

    ResponseEntity<String> handleIllegalStateException(IllegalStateException e);

}

