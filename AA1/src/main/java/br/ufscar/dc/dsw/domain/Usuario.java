package br.ufscar.dc.dsw.domain;

public class Usuario {
    private int id;
    private String email;
    private String senha;
    private String nome;
    private String tipoUsuario;


    // Construtor
    public Usuario(int id)
    {
        this.id = id;
    }

    public Usuario(String email, String senha, String nome, String tipoUsuario) {
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
    }
    

    public Usuario(int id, String email, String senha, String nome, String tipoUsuario) {
        super();
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.nome = nome;
        this.tipoUsuario = tipoUsuario;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}

