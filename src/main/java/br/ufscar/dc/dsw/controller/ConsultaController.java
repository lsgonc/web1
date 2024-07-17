package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.domain.Consulta;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/consulta/*")
public class ConsultaController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ConsultaDAO dao;

    @Override
    public void init() {
        dao = new ConsultaDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                case "/remocao":
                    remove(request, response);
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
        List<Consulta> listaConsultas = dao.getAll();
        request.setAttribute("listaConsultas", listaConsultas);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/consulta/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String paciente_cpf = request.getParameter("paciente_cpf");
        String medico_crm = request.getParameter("medico_crm");
        String stringDataHora = request.getParameter("data_hora");
        LocalDateTime data_hora = LocalDateTime.parse(stringDataHora);

        Consulta novaConsulta = new Consulta(paciente_cpf, medico_crm, data_hora);
        dao.insert(novaConsulta);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String paciente_cpf = request.getParameter("paciente_cpf");
        String medico_crm = request.getParameter("medico_crm");
        String stringDataHora = request.getParameter("data_hora");
        LocalDateTime data_hora = LocalDateTime.parse(stringDataHora);

        Consulta consulta = new Consulta(id, paciente_cpf, medico_crm, data_hora);
        dao.update(consulta);
        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Consulta consulta = new Consulta(id);
        dao.delete(consulta);
        response.sendRedirect("lista");
    }
}
