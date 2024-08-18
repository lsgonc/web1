package br.ufscar.dc.dsw.ClinicaMedica.domain;

import jakarta.persistence.*;


@Entity
@DiscriminatorValue("paciente")

public class Paciente extends Usuario {

    @Id
    @Column(nullable = false, length = 14, unique=true)
    private String CPF;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 1)
    private Sexo sexo;

    @Column(nullable = false)
    private String dataNascimento;

    // Construtor padrão
    public Paciente() {
        super();
    }

    // Construtor com argumentos
    public Paciente(String CPF, String email, String senha, TipoUsuario tipoUsuario, String nome, String telefone, Sexo sexo, String dataNascimento) {
        super(email, senha, nome, tipoUsuario);
        this.CPF = CPF;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    // Getters e Setters
    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public enum Sexo {
        Masculino, Feminino, Outro
    }
}

