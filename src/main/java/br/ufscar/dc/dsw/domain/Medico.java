package br.ufscar.dc.dsw.domain;

public class Medico {
    private String email;
    private String senha;
    private String crm;
    private String nome;
    private String especialidade;

    
    public Medico(String crm, String email, String senha, String nome, String especialidade) {
        this.crm = crm;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.especialidade = especialidade;
    }
    
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
