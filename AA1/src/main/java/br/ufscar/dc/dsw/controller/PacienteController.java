package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.PacienteDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.utils.Erro;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = {"/paciente/*"})
public class PacienteController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private PacienteDAO daoPaciente;
    private UsuarioDAO daoUsuario;

    private Erro erros;

    @Override
    public void init() {
        daoPaciente = new PacienteDAO();
        daoUsuario = new UsuarioDAO();
        erros = new Erro();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        String action = request.getPathInfo();
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "/insercao":
                    insere(request, response);
                    break;
                case "/remocao":
                    remove(request, response);
                    break;
                case "/edicao":
                    apresentaFormEdicao(request, response);
                    break;
                case "/atualizacao":
                    atualize(request, response);
                    break;
                default:
                    lista(request, response);
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void lista(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Paciente> listaPacientes = daoPaciente.getAll();
        request.setAttribute("listaPacientes", listaPacientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/lista/paciente.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario user;

        if(daoUsuario.get(email) == null) {
            user = new Usuario(email, senha, nome, "paciente");
            daoUsuario.insert(user);

            String cpf = request.getParameter("cpf");
            String telefone = request.getParameter("telefone");
            String sexo = request.getParameter("sexo");
            Date dataNascimento = Date.valueOf(request.getParameter("dataNascimento"));

            Paciente paciente = new Paciente(daoUsuario.get(email).getId(), email, senha, nome,"paciente", cpf, telefone, sexo, dataNascimento);

            daoPaciente.insert(paciente);

            response.sendRedirect("/ClinicaMedica/admin");
            return;
        } 
        else 
        {
            erros.add("Email já cadastrado!");
        }
        
        request.setAttribute("errosPaciente", erros);

        RequestDispatcher rd = request.getRequestDispatcher("/admin");
		rd.forward(request, response);

        
        request.setCharacterEncoding("UTF-8");

    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cpf = request.getParameter("cpf");

        Paciente paciente = daoPaciente.get(cpf);

        HttpSession session = request.getSession();
        session.setAttribute("pacienteEdit", paciente);

        response.sendRedirect("/ClinicaMedica/admin?showDocModal=true");
    }


    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario user = new Usuario(id,email, senha, nome, "paciente");

        daoUsuario.update(user);

        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String sexo = request.getParameter("sexo");
        Date dataNascimento = Date.valueOf(request.getParameter("dataNascimento"));

        Paciente paciente = new Paciente(daoUsuario.get(email).getId(), email, senha, nome,"paciente", cpf, telefone, sexo, dataNascimento);

        daoPaciente.update(paciente);
        HttpSession session = request.getSession();
        session.removeAttribute("pacienteEdit");

        response.sendRedirect("/ClinicaMedica/admin");
        request.setCharacterEncoding("UTF-8");

    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

		Paciente paciente = new Paciente(id);
		daoPaciente.delete(paciente);

		response.sendRedirect("/ClinicaMedica/admin");
    }

}
