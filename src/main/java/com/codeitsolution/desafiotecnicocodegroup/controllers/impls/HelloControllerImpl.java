package com.codeitsolution.desafiotecnicocodegroup.controllers.impls;

import com.codeitsolution.desafiotecnicocodegroup.controllers.HelloResource;
import com.codeitsolution.desafiotecnicocodegroup.entities.dtos.ResponseDTO;
import com.codeitsolution.desafiotecnicocodegroup.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@Tag(name = "Hello", description = "Project management APIs")
@RestController
public class HelloControllerImpl implements HelloResource {

    @Autowired
    private PessoaService pessoaService;

    @Operation(
            summary = "Retrieve a Tutorial by Id",
            description = "Get a Tutorial object by specifying its id. The response is Tutorial object with id, title, description and published status.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = HelloControllerImpl.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/")
    @Override
    public ResponseEntity<ResponseDTO> hello() {
        ResponseDTO response = new ResponseDTO();
        response.setMessage(pessoaService.hello());
        response.setStatus(200);
        return ResponseEntity.ok(response);
    }

}
