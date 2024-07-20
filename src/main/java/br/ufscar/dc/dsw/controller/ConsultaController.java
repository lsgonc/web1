package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.dao.PacienteDAO;
import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Consulta;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
                case "/listaPaciente":
                    listaPaciente(request, response);
                    break;
                case "listaConsultaMedico":
                    listaMedico(request, response);
                    break;
                default:
                    break;
            }
        } catch (RuntimeException | IOException | ServletException e) {
            throw new ServletException(e);
        }
    }

    private void listaPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cpf = request.getParameter("cpf");
        List<Consulta> listaConsultas = dao.getAllPaciente(cpf);
        request.setAttribute("listaConsultasPaciente", listaConsultas);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/consulta/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void listaMedico(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String crm = request.getParameter("crm");
        List<Consulta> listaConsultas = dao.getAllMedicos(crm);
        request.setAttribute("listaConsultasMedico", listaConsultas);
        request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/consulta/lista.jsp");
        dispatcher.forward(request, response);
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String paciente_cpf = request.getParameter("paciente_cpf");
        String medico_crm = request.getParameter("medico_crm");
        String stringData = request.getParameter("data_consulta");
        String stringHora = request.getParameter("hora_consulta");

        LocalDate dataLocalDate = LocalDate.parse(stringData);
        LocalTime horaLocalTime = LocalTime.parse(stringHora);
        Date data_consulta = Date.valueOf(dataLocalDate);
        Time hora_consulta = Time.valueOf(horaLocalTime);

        Paciente paciente = new PacienteDAO().get(paciente_cpf);
        Medico medico = new MedicoDAO().get(medico_crm);
        Consulta novaConsulta = new Consulta(paciente, medico, data_consulta, hora_consulta);

        dao.insert(novaConsulta);
        response.sendRedirect("lista");
    }

    private void atualize(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String paciente_cpf = request.getParameter("paciente_cpf");
        String medico_crm = request.getParameter("medico_crm");
        String stringData = request.getParameter("data_consulta");
        String stringHora = request.getParameter("hora_consulta");

        LocalDate dataLocalDate = LocalDate.parse(stringData);
        LocalTime horaLocalTime = LocalTime.parse(stringHora);
        Date data_consulta = Date.valueOf(dataLocalDate);
        Time hora_consulta = Time.valueOf(horaLocalTime);

        Paciente paciente = new PacienteDAO().get(paciente_cpf);
        Medico medico = new MedicoDAO().get(medico_crm);
        Consulta consulta = new Consulta(id, paciente, medico, data_consulta, hora_consulta);

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
