package com.codeitsolution.desafiotecnicocodegroup.controllers.impls;

import com.codeitsolution.desafiotecnicocodegroup.controllers.ProjetoResource;
import com.codeitsolution.desafiotecnicocodegroup.entities.Projeto;
import com.codeitsolution.desafiotecnicocodegroup.entities.enums.Risco;
import com.codeitsolution.desafiotecnicocodegroup.services.impls.ProjetoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Projeto", description = "Gerenciamento de Projetos")
@RestController
@RequestMapping("/projetos")
public class ProjetoController implements ProjetoResource {

    @Autowired
    private ProjetoServiceImpl projetoService;

    @Operation(
            summary = "Retorna todos os projetos",
            description = "Lista todos os projetos disponíveis no sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Projeto.class), mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Projeto.class))) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }) })
    @GetMapping
    public ResponseEntity<?> getAllProjetos() {
        try {
            List<Projeto> projetos = projetoService.findAll();
            if (projetos.isEmpty()) {
                return ResponseEntity.noContent().build(); // Retorna 204 No Content se a lista estiver vazia.
            }
            return ResponseEntity.ok(projetos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao buscar os projetos.");
        }
    }

    @Operation(
            summary = "Retorna um projeto específico",
            description = "Busca e retorna um projeto com base no ID fornecido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Projeto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }) })
    @GetMapping("/{id}")
    public ResponseEntity<?> getProjetoById(@PathVariable Long id) {
        try {
            Projeto projeto = projetoService.findById(id);
            if (projeto == null) {
                return ResponseEntity.notFound().build(); // Retorna 404 Not Found se o projeto não for encontrado.
            }
            return ResponseEntity.ok(projeto);
        } catch (Exception e) {
            // Aqui, você pode querer registrar a exceção ou outras ações com base na exceção.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao buscar o projeto com ID: " + id);
        }
    }

    @Operation(
            summary = "Cria um novo projeto",
            description = "Adiciona um novo projeto ao sistema")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = Projeto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }) })
    @PostMapping
    public ResponseEntity<?> createProjeto(@RequestBody Projeto projeto) {
        try {
            Projeto createdProjeto = projetoService.save(projeto);
            return new ResponseEntity<>(createdProjeto, HttpStatus.CREATED); // Retorna 201 Created se o projeto for criado com sucesso.
        } catch (DataIntegrityViolationException e) {
            // Esta exceção pode ser lançada se, por exemplo, houver uma violação de constraint no banco de dados.
            return ResponseEntity.badRequest().body("Erro de integridade de dados: " + e.getMessage());
        } catch (Exception e) {
            // Aqui, você pode querer registrar a exceção ou outras ações com base na exceção.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao criar o projeto.");
        }
    }

    @Operation(
            summary = "Atualiza um projeto existente",
            description = "Modifica os detalhes de um projeto existente com base no ID fornecido")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Projeto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }) })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateProjeto(@PathVariable Long id, @RequestBody Projeto projeto) {
        try {
            if (!projetoService.existsById(id)) {
                return ResponseEntity.notFound().build();
            }

            projeto.setId(id); // Garantir que o ID correto seja usado
            Projeto updatedProjeto = projetoService.save(projeto);
            return ResponseEntity.ok(updatedProjeto); // Retorna 200 OK com o projeto atualizado

        } catch (DataIntegrityViolationException e) {
            // Esta exceção pode ser lançada se, por exemplo, houver uma violação de constraint no banco de dados.
            return ResponseEntity.badRequest().body("Erro de integridade de dados: " + e.getMessage());
        } catch (Exception e) {
            // Aqui, você pode querer registrar a exceção ou outras ações com base na exceção.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao atualizar o projeto.");
        }
    }

    @Operation(
            summary = "Exclui um projeto",
            description = "Permite a exclusão de um projeto pelo ID"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Projeto excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrado para o ID fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjeto(@PathVariable Long id) {
        if (!projetoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        try {
            projetoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir o projeto: " + e.getMessage());
        }
    }

    // Os projetos devem ser classificados em: baixo risco, médio risco e alto risco.
    // A classificação de risco não é cadastrada no sistema.
    @Operation(
            summary = "Obtem o risco de um projeto",
            description = "Classifica o risco em baixo, médio e alto"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Risco.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrado"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/{id}/risco")
    public ResponseEntity<Risco> getRiscoDoProjeto(@PathVariable Long id) {
        Projeto projeto = projetoService.findById(id);
        if (projeto == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            Risco risco = projetoService.determinarRisco(projeto);
            return ResponseEntity.ok(risco);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }


}

