package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.entity.PessoaEntity;

public interface PessoaJpaRepository extends JpaRepository<PessoaEntity, UUID> {
}