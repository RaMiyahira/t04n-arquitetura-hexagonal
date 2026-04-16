package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.mapper;

import org.springframework.stereotype.Component;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.PessoaDTO;

@Component
public class PessoaDTOMapper {

    public PessoaBO toBO(PessoaDTO dto) {
        return new PessoaBO(
                dto.getId(),
                dto.getNomeCompleto(),
                dto.getCpf(),
                dto.getDataNascimento(),
                dto.getEmail(),
                dto.getTelefone()
        );
    }

    public PessoaDTO toDTO(PessoaBO bo) {
        PessoaDTO dto = new PessoaDTO();
        dto.setId(bo.getId());
        dto.setNomeCompleto(bo.getNomeCompleto());
        dto.setCpf(bo.getCpf());
        dto.setDataNascimento(bo.getDataNascimento());
        dto.setEmail(bo.getEmail());
        dto.setTelefone(bo.getTelefone());
        return dto;
    }
}