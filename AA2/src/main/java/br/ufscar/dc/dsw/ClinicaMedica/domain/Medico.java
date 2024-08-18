package br.ufscar.dc.dsw.ClinicaMedica.domain;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("medico")

public class Medico extends Usuario {

    @Id
   @Column(nullable = false, length = 20,unique=true)
    private String CRM;

    @Column(nullable = false, length = 100)
    private String especialidade;

    // Construtor padrão
    public Medico() {
        super();
    }

    // Construtor com argumentos
    public Medico(String CRM, String email, String senha, TipoUsuario tipoUsuario, String nome, String especialidade) {
        super(email, senha, nome, tipoUsuario);
        this.CRM = CRM;
        this.especialidade = especialidade;
    }

    // Getters e Setters
    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}

