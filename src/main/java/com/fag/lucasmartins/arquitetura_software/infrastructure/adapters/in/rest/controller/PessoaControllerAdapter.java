package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fag.lucasmartins.arquitetura_software.application.ports.in.service.PessoaServicePort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.PessoaDTO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.mapper.PessoaDTOMapper;

@RestController
@RequestMapping("/pessoas")
public class PessoaControllerAdapter {

    private final PessoaServicePort pessoaServicePort;
    private final PessoaDTOMapper pessoaDTOMapper;

    public PessoaControllerAdapter(PessoaServicePort pessoaServicePort, PessoaDTOMapper pessoaDTOMapper) {
        this.pessoaServicePort = pessoaServicePort;
        this.pessoaDTOMapper = pessoaDTOMapper;
    }

    @PostMapping
    public ResponseEntity<PessoaDTO> cadastrarPessoa(@RequestBody PessoaDTO pessoaDTO) {
        PessoaBO pessoaBO = pessoaDTOMapper.toBO(pessoaDTO);
        PessoaBO pessoaCriadaBO = pessoaServicePort.cadastrar(pessoaBO);
        PessoaDTO pessoaCriadaDTO = pessoaDTOMapper.toDTO(pessoaCriadaBO);

        return ResponseEntity.status(201).body(pessoaCriadaDTO);
    }
}