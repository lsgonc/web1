package br.ufscar.dc.dsw.domain;

import java.time.LocalDate;

public class Paciente {
    private String cpf;
    private Usuario usuario;
    private String telefone;
    private LocalDate dataNascimento;

    // Construtor
    public Paciente(String cpf, Usuario usuario, String telefone, LocalDate dataNascimento) {
        this.cpf = cpf;
        this.usuario = usuario;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario= usuario;
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
