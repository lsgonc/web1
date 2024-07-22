package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.dao.ConsultaDAO;
import br.ufscar.dc.dsw.dao.PacienteDAO;
import br.ufscar.dc.dsw.dao.MedicoDAO;
import br.ufscar.dc.dsw.domain.Paciente;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Consulta;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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
    private ConsultaDAO consultaDAO;
    private PacienteDAO pacienteDAO;
    private MedicoDAO medicoDAO;

    private Usuario usuario;

    @Override
    public void init() {
        consultaDAO = new ConsultaDAO();
        pacienteDAO = new PacienteDAO();
        medicoDAO = new MedicoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getPathInfo();

        usuario = (Usuario) request.getSession().getAttribute("usuarioLogado");

        if (usuario == null)
        {
            response.sendRedirect("/ClinicaMedica/login");
            return;
        }

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

        if(usuario.getTipoUsuario().equals("paciente"))
        {
            Paciente paciente = pacienteDAO.getById(usuario.getId()) ;

            List<Consulta> listaConsultas = consultaDAO.getAllPaciente(paciente.getCpf());
            request.setAttribute("listaConsultas", listaConsultas);
            request.setAttribute("pacienteInf", paciente);
            request.setAttribute("listaMedicos", medicoDAO.getAll());
            request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/consultas/paciente.jsp");
            dispatcher.forward(request, response);
        }
        else if (usuario.getTipoUsuario().equals("medico"))
        {
            Medico medico = medicoDAO.getById(usuario.getId());

            List<Consulta> listaConsultas = consultaDAO.getAllMedicos(medico.getCrm());
            request.setAttribute("listaConsultas", listaConsultas);
            request.setAttribute("contextPath", request.getContextPath().replace("/", ""));
            RequestDispatcher dispatcher = request.getRequestDispatcher("/consultas/medico.jsp");
            dispatcher.forward(request, response);
        }


        
    }

    private void insere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String paciente_cpf = request.getParameter("cpfPaciente");
        String medico_crm = request.getParameter("crmMedico");
        String stringData = request.getParameter("dataConsulta");
        String stringHora = request.getParameter("horaConsulta");

        LocalDate dataLocalDate = LocalDate.parse(stringData);
        LocalTime horaLocalTime = LocalTime.parse(stringHora);
        Date data_consulta = Date.valueOf(dataLocalDate);
        Time hora_consulta = Time.valueOf(horaLocalTime);

        Paciente paciente = pacienteDAO.get(paciente_cpf);
        Medico medico = medicoDAO.get(medico_crm);

        if (consultaDAO.medicoTemConsulta(medico_crm, data_consulta, hora_consulta)) {
            request.setAttribute("mensagemErro", "O médico já possui uma consulta neste horário.");
            request.setAttribute("pacienteInf", paciente);
            request.setAttribute("listaConsultas", consultaDAO.getAllPaciente(paciente_cpf));
            request.setAttribute("listaMedicos", medicoDAO.getAll()); 
            request.getRequestDispatcher("/consultas/paciente.jsp").forward(request, response);
            return;
        }

        if (consultaDAO.pacienteTemConsulta(paciente_cpf, data_consulta, hora_consulta)) {
            request.setAttribute("mensagemErro", "O paciente já possui uma consulta neste horário.");
            request.setAttribute("pacienteInf", paciente);
            request.setAttribute("listaConsultas", consultaDAO.getAllPaciente(paciente_cpf)); 
            request.setAttribute("listaMedicos", medicoDAO.getAll()); 
            request.getRequestDispatcher("/consultas/paciente.jsp").forward(request, response);
            return;
        }

        Consulta novaConsulta = new Consulta(paciente, medico, data_consulta, hora_consulta);
        consultaDAO.insert(novaConsulta);

        response.sendRedirect(request.getContextPath() + "/consulta/lista");
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

        consultaDAO.update(consulta);

        response.sendRedirect("lista");
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Consulta consulta = new Consulta(id);
        consultaDAO.delete(consulta);
        response.sendRedirect("/ClinicaMedica/consulta");
    }
}
