package com.codeitsolution.desafiotecnicocodegroup.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class MembrosId implements Serializable {
    private Long idProjeto;
    private Long idPessoa;

}
