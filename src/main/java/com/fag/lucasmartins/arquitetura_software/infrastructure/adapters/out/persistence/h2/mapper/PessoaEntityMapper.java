package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.mapper;

import org.springframework.stereotype.Component;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.entity.PessoaEntity;

@Component
public class PessoaEntityMapper {

    public PessoaEntity toEntity(PessoaBO bo) {
        PessoaEntity entity = new PessoaEntity();
        entity.setId(bo.getId());
        entity.setNomeCompleto(bo.getNomeCompleto());
        entity.setCpf(bo.getCpf());
        entity.setDataNascimento(bo.getDataNascimento());
        entity.setEmail(bo.getEmail());
        entity.setTelefone(bo.getTelefone());
        return entity;
    }

    public PessoaBO toBO(PessoaEntity entity) {
        return new PessoaBO(
                entity.getId(),
                entity.getNomeCompleto(),
                entity.getCpf(),
                entity.getDataNascimento(),
                entity.getEmail(),
                entity.getTelefone()
        );
    }
}