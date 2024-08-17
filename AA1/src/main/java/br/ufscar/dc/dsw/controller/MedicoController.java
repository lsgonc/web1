package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.dao.UsuarioDAO;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Usuario;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/medico/*")
public class MedicoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MedicoDAO medicoDAO;
    private UsuarioDAO userDAO;

    @Override
    public void init() {
        medicoDAO = new MedicoDAO();
        userDAO = new UsuarioDAO();
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
        List<Medico> listaMedicos = medicoDAO.getAll();
        request.setAttribute("listaMedicos", listaMedicos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/medicos/medico.jsp");
        dispatcher.forward(request, response); 
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario user = new Usuario(email, senha, nome, "medico");

        userDAO.insert(user);

        String crm = request.getParameter("crm");
        String especialidade = request.getParameter("especialidade");

        Medico medico = new Medico(userDAO.get(email).getId(), email, senha, nome, "medico", crm, especialidade);

        medicoDAO.insert(medico);

        response.sendRedirect("/ClinicaMedica/admin");
        request.setCharacterEncoding("UTF-8");

    }

    private void apresentaFormEdicao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
            String crm = request.getParameter("crm");

            Medico medico = medicoDAO.get(crm);

            HttpSession session = request.getSession();

            session.setAttribute("medicoEdit", medico);
         
            response.sendRedirect("/ClinicaMedica/admin?showDocModal=true");
	}

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario user = new Usuario(id,email, senha, nome, "medico");

        userDAO.update(user);

        String crm = request.getParameter("crm");
        String especialidade = request.getParameter("especialidade");

        Medico medico = new Medico(userDAO.get(email).getId(), email, senha, nome, "medico", crm, especialidade);

        medicoDAO.update(medico);

        HttpSession session = request.getSession();
        session.removeAttribute("medicoEdit");

        response.sendRedirect("/ClinicaMedica/admin");
        request.setCharacterEncoding("UTF-8");

    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));

		Medico medico = new Medico(id);
		medicoDAO.delete(medico);

		response.sendRedirect("/ClinicaMedica/admin");
    }

}
