package br.ufscar.dc.dsw.domain;

import java.time.LocalDate;

public class Paciente {
    private String cpf;
    private int usuarioId;
    private String telefone;
    private LocalDate dataNascimento;

    // Construtor
    public Paciente(String cpf, int usuarioId, String telefone, LocalDate dataNascimento) {
        this.cpf = cpf;
        this.usuarioId = usuarioId;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
