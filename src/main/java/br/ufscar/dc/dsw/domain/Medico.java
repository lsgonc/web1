package br.ufscar.dc.dsw.domain;

public class Medico {
    private String crm;
    private int usuarioId;
    private String especialidade;

    // Construtor
    public Medico(String crm, int usuarioId, String especialidade) {
        this.crm = crm;
        this.usuarioId = usuarioId;
        this.especialidade = especialidade;
    }

    // Getters e Setters
    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
