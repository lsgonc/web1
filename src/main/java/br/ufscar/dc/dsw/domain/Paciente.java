package br.ufscar.dc.dsw.domain;

import java.sql.Date;

public class Paciente extends Usuario{
    private String cpf;
    private String telefone;
    private String sexo;
    private Date dataNascimento;

    public Paciente (int id)
    {
        super(id);
    }

    // Construtor
    public Paciente(int id, String email, String senha, String nome, String tipoUsuario, String cpf, String telefone, String sexo, Date dataNascimento) {
        super(id, email, senha, nome, tipoUsuario);
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
