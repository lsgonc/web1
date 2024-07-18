package br.ufscar.dc.dsw.domain;

public class Medico {
    private String crm;
    private Usuario usuario;
    private String especialidade;

    // Construtor
    public Medico(String crm, Usuario usuario, String especialidade) {
        this.crm = crm;
        this.usuario = usuario;
        this.especialidade = especialidade;
    }

    // Getters e Setters
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
