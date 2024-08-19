package br.ufscar.dc.dsw.ClinicaMedica.controller;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import br.ufscar.dc.dsw.ClinicaMedica.dao.IConsultaDAO;
import br.ufscar.dc.dsw.ClinicaMedica.dao.IMedicoDAO;
import br.ufscar.dc.dsw.ClinicaMedica.dao.IPacienteDAO;

import br.ufscar.dc.dsw.ClinicaMedica.domain.Consulta;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Medico;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Paciente;

import br.ufscar.dc.dsw.ClinicaMedica.domain.Usuario;
import br.ufscar.dc.dsw.ClinicaMedica.security.UsuarioDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ConsultaController {

    private final IConsultaDAO consultaDAO;
    private final IMedicoDAO medicoDAO;
    private final IPacienteDAO pacienteDAO;

    @Autowired
    public ConsultaController(IConsultaDAO consultaDAO, IMedicoDAO medicoDAO, IPacienteDAO pacienteDAO) {
        this.consultaDAO = consultaDAO;
        this.medicoDAO = medicoDAO;
        this.pacienteDAO = pacienteDAO;
    }

    private Usuario getUser() {
        UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return usuarioDetails.getUser();
    }

    @GetMapping("/consultaMedico")
    public String listarConsultasMedico(Model model, Locale locale) {
        Usuario usuario = getUser();
        if (usuario instanceof Medico) {
            Medico medico = (Medico) usuario;
            List<Consulta> consultas = consultaDAO.findByMedicoCRM(medico.getCRM());
            model.addAttribute("consultas", consultas);
            model.addAttribute("usuario", usuario.getNome());
        } else {
            model.addAttribute("error", "Acesso negado. Apenas médicos podem visualizar essa página.");
            return "error/403";
        }
        return "consultas/medico";
    }

    @GetMapping("/consultaPaciente")
    public String listarConsultasPaciente(Model model, Locale locale) {
        Usuario usuario = getUser();
        if (usuario instanceof Paciente) {
            Paciente paciente = (Paciente) usuario;
            List<Consulta> consultas = consultaDAO.findByPacienteCPF(paciente.getCPF());
            List<Medico> medicos = medicoDAO.findAll();
            model.addAttribute("consultas", consultas);
            model.addAttribute("usuario", usuario.getNome());
            model.addAttribute("paciente", paciente);
            model.addAttribute("listaMedicos", medicos);
        } else {
            model.addAttribute("error", "Acesso negado. Apenas pacientes podem visualizar essa página.");
            return "error/403";
        }
        return "consultas/paciente";
    }

    @PostMapping("/consulta/insercao")
    public RedirectView inserirConsulta(@RequestParam("cpfPaciente") String cpfPaciente,
                                      @RequestParam("crmMedico") String crmMedico,
                                      @RequestParam("dataConsulta") Date dataConsulta,
                                      @RequestParam("horaConsulta") String horaConsulta,
                                      RedirectAttributes redirectAttributes) {
 
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            long ms = sdf.parse(horaConsulta).getTime();
            Time hora = new Time(ms);

            Consulta consulta = new Consulta(pacienteDAO.findByCPF(cpfPaciente), medicoDAO.findByCRM(crmMedico), dataConsulta, hora);

            consultaDAO.save(consulta);

            redirectAttributes.addFlashAttribute("message", "Medico inserido com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao inserir medico.");
        }

        return new RedirectView("/consultaPaciente");
    }
}

