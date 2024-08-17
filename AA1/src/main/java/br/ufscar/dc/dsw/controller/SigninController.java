package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.dao.PacienteDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.utils.Erro;

@WebServlet("/signin")
public class SigninController extends HttpServlet{

    private static final long serialVersionUID = 1L;
    private UsuarioDAO daoUsuario;
    private PacienteDAO daoPaciente;
    private MedicoDAO daoMedico;

    @Override
    public void init() {
        daoUsuario = new UsuarioDAO();
        daoPaciente = new PacienteDAO();
        daoMedico = new MedicoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
        
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/insercao":
                    insere(request, response);
                    break;
                default:
                    insere(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Erro errosEmail = new Erro();
        Erro errosSenha = new Erro();
        Erro errosNome = new Erro();
        Erro errosCPF = new Erro();
        Erro errosData = new Erro();

        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String tipoUsuario = request.getParameter("tipoUsuario");

        String CPF = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        Date dataNascimento = Date.valueOf(request.getParameter("dataNascimento"));

        String CRM = request.getParameter("crm");
        String especialidade = request.getParameter("especialidade");
        

        // Verificação do nome
        if (nome == null || nome.isEmpty()) {
            errosNome.add("Nome não informado!");
        }

        // Verificação do email
        if (email == null || email.isEmpty()) {
            errosEmail.add("Email não informado!");
        } else if (daoUsuario.get(email) != null) {
            errosEmail.add("Email já está em uso!");
        }

        // Verificação da senha
        if (senha == null || senha.isEmpty()) {
            errosSenha.add("Senha não informada!");
        }

        // Verificação do CPF
        if (CPF == null || CPF.isEmpty()) {
            errosCPF.add("CPF não informado!");
        } else if (daoPaciente.get(CPF) != null) {
            errosCPF.add("CPF já está em uso!");
        }

        if (!errosEmail.isExisteErros() && !errosSenha.isExisteErros() && !errosNome.isExisteErros()
                && tipoUsuario == "paciente") {
            Usuario user = new Usuario(email, senha, nome, tipoUsuario);
            daoUsuario.insert(user);

            Paciente paciente = new Paciente(daoUsuario.get(email).getId(), email, senha, nome, "paciente", CPF, telefone, sexo, dataNascimento);

            daoPaciente.insert(paciente);
        }
        
        if (!errosEmail.isExisteErros() && !errosSenha.isExisteErros() && !errosNome.isExisteErros()
                && tipoUsuario == "medico") {
            Usuario user = new Usuario(email, senha, nome, tipoUsuario);
            daoUsuario.insert(user);

            Medico medico = new Medico(daoUsuario.get(email).getId(), email, senha, nome, "medico", CRM, especialidade);
            daoMedico.insert(medico);
        }

        request.setAttribute("mensagensNome", errosNome);
        request.setAttribute("mensagensEmail", errosEmail);
        request.setAttribute("mensagensSenha", errosSenha);
        request.setAttribute("mensagensCPF", errosCPF);
        request.setAttribute("mensagensData", errosData);

        String URL = "/signin";
        RequestDispatcher rd = request.getRequestDispatcher(URL);
        rd.forward(request, response);
    }
}
