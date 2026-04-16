package com.fag.lucasmartins.arquitetura_software.core.domain.bo;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

import com.fag.lucasmartins.arquitetura_software.core.domain.exceptions.DomainException;

public class PessoaBO {

    private UUID id;
    private String nomeCompleto;
    private String cpf;
    private LocalDate dataNascimento;
    private String email;
    private String telefone;

    public PessoaBO(UUID id, String nomeCompleto, String cpf, LocalDate dataNascimento, String email, String telefone) {
        this.id = (id == null) ? UUID.randomUUID() : id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.telefone = telefone;

        validar();
    }

    private void validar() {
        if (nomeCompleto == null || nomeCompleto.isBlank()) {
            throw new DomainException("Nome completo é obrigatório.");
        }

        if (cpf == null || cpf.isBlank()) {
            throw new DomainException("CPF é obrigatório.");
        }

        if (cpf.length() != 11) {
            throw new DomainException("CPF deve possuir 11 caracteres.");
        }

        if (telefone == null || telefone.isBlank()) {
            throw new DomainException("Telefone é obrigatório.");
        }

        if (telefone.length() != 11) {
            throw new DomainException("Telefone deve possuir 11 caracteres.");
        }

        if (email == null || email.isBlank()) {
            throw new DomainException("E-mail é obrigatório.");
        }

        if (!email.contains("@")) {
            throw new DomainException("E-mail inválido.");
        }

        if (dataNascimento == null) {
            throw new DomainException("Data de nascimento é obrigatória.");
        }

        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        if (idade < 18) {
            throw new DomainException("Pessoa deve ter no mínimo 18 anos.");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }
}