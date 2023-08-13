package com.codeitsolution.desafiotecnicocodegroup.controllers;

import com.codeitsolution.desafiotecnicocodegroup.entities.dtos.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface HelloResource {

    public ResponseEntity<ResponseDTO> hello();

}
