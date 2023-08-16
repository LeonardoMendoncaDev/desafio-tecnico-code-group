package com.codeitsolution.desafiotecnicocodegroup.controllers;

import com.codeitsolution.desafiotecnicocodegroup.controllers.impls.ProjetoController;
import com.codeitsolution.desafiotecnicocodegroup.entities.Projeto;
import com.codeitsolution.desafiotecnicocodegroup.entities.enums.Risco;
import com.codeitsolution.desafiotecnicocodegroup.services.ProjetoService;
import com.codeitsolution.desafiotecnicocodegroup.services.impls.ProjetoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjetoControllerTest {

    @Mock
    private ProjetoServiceImpl projetoService;

    @InjectMocks
    private ProjetoController projetoController;

    /**
     * Testa o método {@code getAllProjetos} quando há projetos disponíveis.
     *
     * Cenário:
     *     Dado que o serviço retorna uma lista de projetos.
     *     Quando o método é invocado.
     *     Então ele deve retornar uma resposta com status 200 (OK).
     *     E o corpo da resposta deve conter a lista de projetos.
     *
     * @see ProjetoController#getAllProjetos()
     */
    @Test
    public void testGetAllProjetosWithAvailableProjetos() {
        // Preparação
        List<Projeto> projetos = Arrays.asList(new Projeto(), new Projeto());
        when(projetoService.findAll()).thenReturn(projetos);

        // Ação
        ResponseEntity<?> response = projetoController.getAllProjetos();

        // Verificação adicional: imprime a exceção se o status for 500
        if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            System.out.println(response.getBody());
        }

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(projetos, response.getBody());
    }

    /**
     * Testa o método {@code getAllProjetos} quando não há projetos disponíveis.
     *
     * Cenário:
     *     Dado que o serviço retorna uma lista vazia de projetos.
     *     Quando o método é invocado.
     *     Então ele deve retornar uma resposta com status 204 (No Content).
     *     E o corpo da resposta deve estar vazio.
     *
     * @see ProjetoController#getAllProjetos()
     */
    @Test
    public void testGetAllProjetosWithNoAvailableProjetos() {
        // Preparação
        when(projetoService.findAll()).thenReturn(Collections.emptyList());

        // Ação
        ResponseEntity<?> response = projetoController.getAllProjetos();

        // Verificações
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    /**
     * Testa o método {@code getAllProjetos} quando ocorre uma exceção ao buscar os projetos.
     *
     * Cenário:
     *     Dado que o serviço lança uma exceção ao tentar buscar os projetos.
     *     Quando o método é invocado.
     *     Então ele deve retornar uma resposta com status 500 (Internal Server Error).
     *     E o corpo da resposta deve conter a mensagem de erro.
     *
     * @see ProjetoController#getAllProjetos()
     */
    @Test
    public void testGetAllProjetosWhenServiceThrowsException() {
        // Preparação
        when(projetoService.findAll()).thenThrow(new RuntimeException("Erro inesperado ao buscar projetos."));

        // Ação
        ResponseEntity<?> response = projetoController.getAllProjetos();

        // Verificações
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Ocorreu um erro ao buscar os projetos.", response.getBody());
    }

    /**
     * Testa o método {@code getProjetoById} quando o projeto é encontrado.
     *
     * Cenário:
     *     Dado que o serviço retorna um projeto para o ID fornecido.
     *     Quando o método é invocado com esse ID.
     *     Então ele deve retornar uma resposta com status 200 (OK).
     *     E o corpo da resposta deve conter o projeto.
     *
     * @see ProjetoController#getProjetoById(Long)
     */
    @Test
    public void testGetProjetoByIdWhenProjetoIsFound() {
        // Preparação
        Long projetoId = 1L;
        Projeto projeto = new Projeto();
        projeto.setId(projetoId);
        when(projetoService.findById(projetoId)).thenReturn(projeto);

        // Ação
        ResponseEntity<?> response = projetoController.getProjetoById(projetoId);

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(projeto, response.getBody());
    }

    /**
     * Testa o método {@code getProjetoById} quando o projeto não é encontrado.
     *
     * Cenário:
     *     Dado que o serviço retorna null para o ID fornecido.
     *     Quando o método é invocado com esse ID.
     *     Então ele deve retornar uma resposta com status 404 (Not Found).
     *     E o corpo da resposta deve estar vazio.
     *
     * @see ProjetoController#getProjetoById(Long)
     */
    @Test
    public void testGetProjetoByIdWhenProjetoIsNotFound() {
        // Preparação
        Long projetoId = 1L;
        when(projetoService.findById(projetoId)).thenReturn(null);

        // Ação
        ResponseEntity<?> response = projetoController.getProjetoById(projetoId);

        // Verificações
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    /**
     * Testa o método {@code getProjetoById} quando ocorre uma exceção ao buscar o projeto pelo ID fornecido.
     *
     * Cenário:
     *     Dado que o serviço lança uma exceção ao tentar buscar o projeto pelo ID.
     *     Quando o método é invocado com esse ID.
     *     Então ele deve retornar uma resposta com status 500 (Internal Server Error).
     *     E o corpo da resposta deve conter a mensagem de erro.
     *
     * @see ProjetoController#getProjetoById(Long)
     */
    @Test
    public void testGetProjetoByIdWhenServiceThrowsException() {
        // Preparação
        Long projetoId = 1L;
        when(projetoService.findById(projetoId)).thenThrow(new RuntimeException("Erro inesperado ao buscar projeto pelo ID."));

        // Ação
        ResponseEntity<?> response = projetoController.getProjetoById(projetoId);

        // Verificações
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Ocorreu um erro ao buscar o projeto com ID: " + projetoId, response.getBody());
    }

    /**
     * Testa o método {@code createProjeto} quando o projeto é criado com sucesso.
     *
     * Cenário:
     *     Dado que o serviço salva e retorna o projeto criado com sucesso.
     *     Quando o método é invocado com um projeto.
     *     Então ele deve retornar uma resposta com status 201 (Created).
     *     E o corpo da resposta deve conter o projeto criado.
     *
     * @see ProjetoController#createProjeto(Projeto)
     */
    @Test
    public void testCreateProjetoWhenProjetoIsCreatedSuccessfully() {
        // Preparação
        Projeto projeto = new Projeto();
        Projeto createdProjeto = new Projeto();
        createdProjeto.setId(1L);
        when(projetoService.save(projeto)).thenReturn(createdProjeto);

        // Ação
        ResponseEntity<?> response = projetoController.createProjeto(projeto);

        // Verificações
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdProjeto, response.getBody());
    }

    /**
     * Testa o método {@code updateProjeto} quando o projeto é atualizado com sucesso.
     *
     * Cenário:
     *     Dado que o serviço confirma a existência do projeto pelo ID fornecido.
     *     E o serviço salva e retorna o projeto atualizado com sucesso.
     *     Quando o método é invocado com um ID e um projeto.
     *     Então ele deve retornar uma resposta com status 200 (OK).
     *     E o corpo da resposta deve conter o projeto atualizado.
     *
     * @see ProjetoController#updateProjeto(Long, Projeto)
     */
    @Test
    public void testUpdateProjetoWhenProjetoIsUpdatedSuccessfully() {
        // Preparação
        Long projetoId = 1L;
        Projeto projeto = new Projeto();
        Projeto updatedProjeto = new Projeto();
        updatedProjeto.setId(projetoId);

        when(projetoService.existsById(projetoId)).thenReturn(true);
        when(projetoService.save(projeto)).thenReturn(updatedProjeto);

        // Ação
        ResponseEntity<?> response = projetoController.updateProjeto(projetoId, projeto);

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProjeto, response.getBody());
    }

    /**
     * Testa o método {@code deleteProjeto} quando o projeto é excluído com sucesso.
     *
     * Cenário:
     *     Dado que o serviço confirma a existência do projeto pelo ID fornecido.
     *     E o serviço exclui o projeto com sucesso.
     *     Quando o método é invocado com o ID do projeto.
     *     Então ele deve retornar uma resposta com status 204 (No Content).
     *
     * @see ProjetoController#deleteProjeto(Long)
     */
    @Test
    public void testDeleteProjetoWhenProjetoIsDeletedSuccessfully() {
        // Preparação
        Long projetoId = 1L;
        when(projetoService.existsById(projetoId)).thenReturn(true);

        // Ação
        ResponseEntity<?> response = projetoController.deleteProjeto(projetoId);

        // Verificações
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    /**
     * Testa o método {@code getRiscoDoProjeto} quando o projeto é encontrado e seu risco é determinado.
     *
     * Cenário:
     *     Dado que o serviço retorna um projeto pelo ID fornecido.
     *     E o risco do projeto é determinado como "médio".
     *     Quando o método é invocado com o ID do projeto.
     *     Então ele deve retornar uma resposta com status 200 (OK) e com o risco "médio".
     *
     * @see ProjetoController#getRiscoDoProjeto(Long)
     */
    @Test
    public void testGetRiscoDoProjetoWhenProjetoIsFoundAndRiscoIsDetermined() {
        // Preparação
        Long projetoId = 1L;
        Projeto mockProjeto = new Projeto();
        Risco mockRisco = Risco.MEDIO; // Supondo que Risco é um Enum com valores BAIXO, MEDIO e ALTO

        when(projetoService.findById(projetoId)).thenReturn(mockProjeto);
        when(projetoService.determinarRisco(mockProjeto)).thenReturn(mockRisco);

        // Ação
        ResponseEntity<Risco> response = projetoController.getRiscoDoProjeto(projetoId);

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRisco, response.getBody());
    }


}
