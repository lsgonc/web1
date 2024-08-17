package br.ufscar.dc.dsw.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.dao.PacienteDAO;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Usuario;

@WebServlet(urlPatterns = "/admin")
public class AdminController extends HttpServlet {

    private static final long serialVersionUID = 1L;
	private MedicoDAO medicoDAO;
	private PacienteDAO pacienteDAO;

	public void init() {
        medicoDAO = new MedicoDAO();
		pacienteDAO = new PacienteDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	Usuario usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");
    	
    	if (usuario == null) {
    		response.sendRedirect(request.getContextPath());
    	} else if (usuario.getTipoUsuario().equals("admin")) {
			List<Medico> listaMedicos = medicoDAO.getAll();
        	request.setAttribute("listaMedicos", listaMedicos);

			List<Paciente> listaPacientes = pacienteDAO.getAll();
        	request.setAttribute("listaPacientes", listaPacientes);
        

    		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/index.jsp");
            dispatcher.forward(request, response);
    	} else {
    		response.sendRedirect("/ClinicaMedica/");
    	}
    }
}