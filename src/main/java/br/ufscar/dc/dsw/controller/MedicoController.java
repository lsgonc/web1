package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.domain.Medico;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/medico/*")
public class MedicoController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private MedicoDAO dao;

    @Override
    public void init() {
        dao = new MedicoDAO();
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
        List<Medico> listaMedicos = dao.getAll();
        request.setAttribute("listaMedicos", listaMedicos);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/medico/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

    }

    private void atualize(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

}
