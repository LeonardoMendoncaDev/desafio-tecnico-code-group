package com.codeitsolution.desafiotecnicocodegroup.controllers.impls;

import com.codeitsolution.desafiotecnicocodegroup.controllers.MembroProjetoResource;
import com.codeitsolution.desafiotecnicocodegroup.entities.MembroProjeto;
import com.codeitsolution.desafiotecnicocodegroup.entities.Pessoa;
import com.codeitsolution.desafiotecnicocodegroup.entities.Projeto;
import com.codeitsolution.desafiotecnicocodegroup.entities.dtos.MembroProjetoDTO;
import com.codeitsolution.desafiotecnicocodegroup.services.PessoaService;
import com.codeitsolution.desafiotecnicocodegroup.services.ProjetoService;
import com.codeitsolution.desafiotecnicocodegroup.services.impls.MembroProjetoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Projeto", description = "Gerenciamento de Membro Projeto")
@RestController
public class MembroProjetoController implements MembroProjetoResource {

    @Autowired
    private MembroProjetoServiceImpl membroProjetoService;

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private PessoaService pessoaService;

    // O sistema deve permitir associar membros aos projetos que tem atribuição funcionário
    @Operation(
            summary = "Adiciona um membro ao projeto",
            description = "Associa um membro com atribuição de 'funcionário' a um projeto específico."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Membro adicionado com sucesso ao projeto.",
                    content = {
                            @Content(schema = @Schema(implementation = MembroProjeto.class), mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro de validação ou membro não possui atribuição de 'funcionário'.",
                    content = { @Content(schema = @Schema(implementation = String.class)) }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Projeto ou Pessoa não encontrados.",
                    content = { @Content() }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor.",
                    content = { @Content() }
            )
    })
    @PostMapping("/projetos/{projetoId}/membros")
    public ResponseEntity<?> adicionarMembro(@PathVariable Long projetoId, @RequestBody MembroProjetoDTO membroDTO) {
        Projeto projeto = projetoService.findById(projetoId);
        if (projeto == null) {
            return ResponseEntity.notFound().build();
        }

        Pessoa pessoa = pessoaService.findById(membroDTO.getPessoaId());
        if (pessoa == null) {
            return ResponseEntity.badRequest().body("Pessoa não encontrada com ID: " + membroDTO.getPessoaId());
        }

        if (pessoa.getAtribuicao() == null || !pessoa.getAtribuicao().equals("funcionario")) {
            return ResponseEntity.badRequest().body("A pessoa deve ter atribuição de funcionário");
        }

        MembroProjeto membroProjeto = new MembroProjeto();
        membroProjeto.setProjeto(projeto);
        membroProjeto.setPessoa(pessoa);

        MembroProjeto savedMembroProjeto = membroProjetoService.save(membroProjeto);
        return ResponseEntity.ok(savedMembroProjeto);
    }

}

