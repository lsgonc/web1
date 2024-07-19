package br.ufscar.dc.dsw.domain;

public class Medico extends Usuario{
    private String crm;
    private String especialidade;

    // Construtor
    public Medico(int id, String email, String senha, String nome, String tipoUsuario, String crm, String especialidade) {
        super(id, email, senha, nome, tipoUsuario);
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
