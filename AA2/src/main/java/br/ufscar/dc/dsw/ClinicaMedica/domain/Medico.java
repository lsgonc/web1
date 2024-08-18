package br.ufscar.dc.dsw.ClinicaMedica.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Medico")
public class Medico extends Usuario {


   @Column(nullable = false, length = 20)
    private String crm;

    @Column(nullable = false, length = 100)
    private String especialidade;

    // Construtor padrão
    public Medico() {
        super();
    }

    // Construtor com argumentos
    public Medico(String crm, String email, String senha, TipoUsuario tipoUsuario, String nome, String especialidade) {
        super(email, senha, nome, tipoUsuario);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    // Getters e Setters
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}

