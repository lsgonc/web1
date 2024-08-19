package br.ufscar.dc.dsw.ClinicaMedica.controller;

import java.util.List;
import java.util.Locale;

import br.ufscar.dc.dsw.ClinicaMedica.dao.IMedicoDAO;
import br.ufscar.dc.dsw.ClinicaMedica.dao.IPacienteDAO;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Medico;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Paciente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final IMedicoDAO medicoDAO;
    private final IPacienteDAO pacienteDAO;

    public AdminController(IMedicoDAO medicoDAO, IPacienteDAO pacienteDAO) {
        this.medicoDAO = medicoDAO;
        this.pacienteDAO = pacienteDAO;
    }

    @GetMapping({ "/adm","/admin"})
    public String index(Model model, Locale locale) {
        List<Medico> listaMedicos = medicoDAO.findAll();
        List<Paciente> listaPacientes = pacienteDAO.findAll();

        model.addAttribute("listaMedicos", listaMedicos);
        model.addAttribute("listaPacientes", listaPacientes);

        return "admin/index";
    }
}