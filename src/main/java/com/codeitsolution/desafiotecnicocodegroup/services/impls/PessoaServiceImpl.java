package com.codeitsolution.desafiotecnicocodegroup.services.impls;

import com.codeitsolution.desafiotecnicocodegroup.services.PessoaService;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Override
    public String hello() {
        return "Hello World";
    }
}
