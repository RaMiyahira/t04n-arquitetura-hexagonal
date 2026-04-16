package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.repository;

import org.springframework.stereotype.Component;

import com.fag.lucasmartins.arquitetura_software.application.ports.out.persistence.h2.PessoaRepositoryPort;
import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.entity.PessoaEntity;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.jpa.PessoaJpaRepository;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.mapper.PessoaEntityMapper;

@Component
public class PessoaRepositoryAdapter implements PessoaRepositoryPort {

    private final PessoaJpaRepository pessoaJpaRepository;
    private final PessoaEntityMapper pessoaEntityMapper;

    public PessoaRepositoryAdapter(PessoaJpaRepository pessoaJpaRepository,
                                   PessoaEntityMapper pessoaEntityMapper) {
        this.pessoaJpaRepository = pessoaJpaRepository;
        this.pessoaEntityMapper = pessoaEntityMapper;
    }

    @Override
    public PessoaBO salvar(PessoaBO bo) {
        PessoaEntity entity = pessoaEntityMapper.toEntity(bo);
        PessoaEntity salvo = pessoaJpaRepository.save(entity);
        return pessoaEntityMapper.toBO(salvo);
    }
}