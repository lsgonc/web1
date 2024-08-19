package br.ufscar.dc.dsw.ClinicaMedica.controller;

import java.util.List;
import java.util.Locale;

import br.ufscar.dc.dsw.ClinicaMedica.dao.IMedicoDAO;
import br.ufscar.dc.dsw.ClinicaMedica.dao.IPacienteDAO;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Medico;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MedicoController {

    private final IMedicoDAO medicoDAO;

    public MedicoController(IMedicoDAO medicoDAO, IPacienteDAO pacienteDAO) {
        this.medicoDAO = medicoDAO;
    }

    @GetMapping("/medico")
    public String index(Model model, Locale locale) {
        List<Medico> listaMedicos = medicoDAO.findAll();

        model.addAttribute("listaMedicos", listaMedicos);

        return "medicos/index";
    }
}