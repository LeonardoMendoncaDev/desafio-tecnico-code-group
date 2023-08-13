package com.codeitsolution.desafiotecnicocodegroup.controllers.impls;

import com.codeitsolution.desafiotecnicocodegroup.entities.Projeto;
import com.codeitsolution.desafiotecnicocodegroup.services.impls.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
public class ProjetoController {
    @Autowired
    private ProjetoService projetoService;

    @GetMapping
    public List<Projeto> getAllProjetos() {
        return projetoService.findAll();
    }

    @GetMapping("/{id}")
    public Projeto getProjetoById(@PathVariable Long id) {
        return projetoService.findById(id);
    }

    @PostMapping
    public Projeto createProjeto(@RequestBody Projeto projeto) {
        return projetoService.save(projeto);
    }

    @PutMapping("/{id}")
    public Projeto updateProjeto(@PathVariable Long id, @RequestBody Projeto projeto) {
        projeto.setId(id); // Garantir que o ID correto seja usado
        return projetoService.save(projeto);
    }

    @DeleteMapping("/{id}")
    public void deleteProjeto(@PathVariable Long id) {
        projetoService.delete(id);
    }
}

