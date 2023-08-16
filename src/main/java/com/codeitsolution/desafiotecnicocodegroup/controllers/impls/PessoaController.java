package com.codeitsolution.desafiotecnicocodegroup.controllers.impls;

import com.codeitsolution.desafiotecnicocodegroup.controllers.PessoaResource;
import com.codeitsolution.desafiotecnicocodegroup.entities.Pessoa;
import com.codeitsolution.desafiotecnicocodegroup.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Pessoa", description = "Gerenciamento de Membros")
@RestController
public class PessoaController implements PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    // O sistema não deve permitir o cadastro de um novo membro diretamente.
    // Deve ser provida funcionalidade via web service, contendo nome e atribuição (cargo).
    @Operation(
            summary = "Cria um novo membro",
            description = "Permite o cadastro de um novo membro com nome e atribuição (cargo). Não permite o cadastro direto via interface."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Membro criado com sucesso.",
                    content = {
                            @Content(schema = @Schema(implementation = Pessoa.class), mediaType = "application/json")
                    }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro de validação ou dados inválidos.",
                    content = { @Content(schema = @Schema(implementation = String.class)) }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor.",
                    content = { @Content() }
            )
    })
    @PostMapping("/membros")
    public ResponseEntity<?> createMembro(@RequestBody Pessoa pessoa) {
        try {
            if (pessoa == null || pessoa.getNome() == null || pessoa.getAtribuicao() == null) {
                // Response code 400: Bad Request
                return ResponseEntity.badRequest().body("Dados inválidos fornecidos.");
            }

            Pessoa savedPessoa = pessoaService.save(pessoa);

            // Response code 201: Created
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPessoa);
        } catch (Exception e) {
            // Response code 500: Internal Server Error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao criar o membro.");
        }
    }


}


