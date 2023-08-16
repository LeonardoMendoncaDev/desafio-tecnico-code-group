package com.codeitsolution.desafiotecnicocodegroup.controllers;

import com.codeitsolution.desafiotecnicocodegroup.controllers.impls.PessoaController;
import com.codeitsolution.desafiotecnicocodegroup.entities.Pessoa;
import com.codeitsolution.desafiotecnicocodegroup.services.PessoaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PessoaControllerTest {

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private PessoaController pessoaController;

    /**
     * Testa o método {@code createMembro} com dados válidos.
     *
     * Cenário:
     *   Dado um objeto {@code Pessoa} válido com nome e atribuicao definidos.
     *   Quando o método é invocado com esse objeto.
     *   Então ele deve retornar uma resposta com status 201 (Criado).
     *   E o corpo da resposta deve conter o mesmo objeto {@code Pessoa}.
     *
     * @see PessoaController#createMembro(Pessoa)
     */
    @Test
    public void testCreateMembroWithValidData() {
        // Preparação
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setAtribuicao("Developer");

        when(pessoaService.save(pessoa)).thenReturn(pessoa);

        // Ação
        ResponseEntity<?> response = pessoaController.createMembro(pessoa);

        // Verificações
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(pessoa, response.getBody());
    }

    /**
     * Testa o método {@code createMembro} com dados nulos.
     *
     * Cenário:
     *     Dado um objeto {@code Pessoa} nulo.
     *     Quando o método é invocado com esse objeto nulo.
     *     Então ele deve retornar uma resposta com status 400 (Requisição Inválida).
     *     E o corpo da resposta deve conter uma mensagem indicando dados inválidos.
     *
     * @see PessoaController#createMembro(Pessoa)
     */
    @Test
    public void testCreateMembroWithNullData() {
        // Ação
        ResponseEntity<?> response = pessoaController.createMembro(null);

        // Verificações
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Dados inválidos fornecidos.", response.getBody());
    }

    /**
     * Testa o método {@code createMembro} com o nome nulo e atribuição válida.
     *
     * Cenário:
     *     Dado um objeto {@code Pessoa} com nome definido como nulo e uma atribuição válida.
     *     Quando o método é invocado com esse objeto.
     *     Então ele deve retornar uma resposta com status 400 (Requisição Inválida).
     *     E o corpo da resposta deve conter uma mensagem indicando dados inválidos.
     *
     * @see PessoaController#createMembro(Pessoa)
     */
    @Test
    public void testCreateMembroWithNullName() {
        // Preparação
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(null);
        pessoa.setAtribuicao("Developer");

        // Ação
        ResponseEntity<?> response = pessoaController.createMembro(pessoa);

        // Verificações
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Dados inválidos fornecidos.", response.getBody());
    }

    /**
     * Testa o método {@code createMembro} com atribuição nula e nome válido.
     *
     * Cenário:
     *     Dado um objeto {@code Pessoa} com atribuição definida como nula e um nome válido.
     *     Quando o método é invocado com esse objeto.
     *     Então ele deve retornar uma resposta com status 400 (Requisição Inválida).
     *     E o corpo da resposta deve conter uma mensagem indicando dados inválidos.
     *
     * @see PessoaController#createMembro(Pessoa)
     */
    @Test
    public void testCreateMembroWithNullAtribuicao() {
        // Preparação
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setAtribuicao(null);

        // Ação
        ResponseEntity<?> response = pessoaController.createMembro(pessoa);

        // Verificações
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Dados inválidos fornecidos.", response.getBody());
    }

    /**
     * Testa o método {@code createMembro} quando ocorre uma exceção durante o salvamento da {@code Pessoa}.
     *
     * Cenário:
     *     Dado um objeto {@code Pessoa} válido.
     *     Quando o método é invocado com esse objeto e o serviço lança uma exceção.
     *     Então ele deve retornar uma resposta com status 500 (Erro Interno do Servidor).
     *     E o corpo da resposta deve conter uma mensagem indicando o erro ao criar o membro.
     *
     * @see PessoaController#createMembro(Pessoa)
     */
    @Test
    public void testCreateMembroWithServiceException() {
        // Preparação
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setAtribuicao("Developer");

        // Simula uma exceção ao tentar salvar a Pessoa
        when(pessoaService.save(pessoa)).thenThrow(new RuntimeException("Erro simulado ao salvar"));

        // Ação
        ResponseEntity<?> response = pessoaController.createMembro(pessoa);

        // Verificações
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Ocorreu um erro ao criar o membro.", response.getBody());
    }

    /**
     * Testa se o método {@code createMembro} chama o serviço {@code pessoaService.save(pessoa)} com a {@code Pessoa} fornecida.
     *
     * Cenário:
     *     Dado um objeto {@code Pessoa} válido.
     *     Quando o método é invocado com esse objeto.
     *     Então o serviço {@code pessoaService.save(pessoa)} deve ser chamado com o mesmo objeto {@code Pessoa}.
     *
     * @see PessoaController#createMembro(Pessoa)
     */
    @Test
    public void testCreateMembroServiceCall() {
        // Preparação
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setAtribuicao("Developer");

        when(pessoaService.save(pessoa)).thenReturn(pessoa);

        // Ação
        pessoaController.createMembro(pessoa);

        // Verificações
        verify(pessoaService).save(pessoa);
    }



}
