package br.ufscar.dc.dsw.ClinicaMedica.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "Paciente")
public class Paciente extends Usuario {

    @Column(nullable = false, length = 14)
    private String cpf;

    @Column(nullable = false, length = 15)
    private String telefone;

    @Column(nullable = false, length = 1)
    private String sexo;

    @Column(nullable = false)
    private String dataNascimento;

    // Construtor padrão
    public Paciente() {
        super();
    }

    // Construtor com argumentos
    public Paciente(String cpf, String email, String senha, TipoUsuario tipoUsuario, String nome, String telefone, String sexo, String dataNascimento) {
        super(email, senha, nome, tipoUsuario);
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}

