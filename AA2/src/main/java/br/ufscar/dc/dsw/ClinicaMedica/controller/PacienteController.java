package br.ufscar.dc.dsw.ClinicaMedica.controller;

import br.ufscar.dc.dsw.ClinicaMedica.dao.IConsultaDAO;
import br.ufscar.dc.dsw.ClinicaMedica.dao.IUsuarioDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.ClinicaMedica.dao.IPacienteDAO;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class PacienteController {

    @Autowired
    private IPacienteDAO pacienteDAO;
    private IUsuarioDAO usuarioDAO;
    private IConsultaDAO consultaDAO;

    public PacienteController(IUsuarioDAO usuarioDAO, IPacienteDAO pacienteDAO, IConsultaDAO consultaDAO) {
        this.usuarioDAO = usuarioDAO;
        this.pacienteDAO = pacienteDAO;
        this.consultaDAO = consultaDAO;
    }

    @Transactional
    @PostMapping("/paciente/remocao")
    public RedirectView removerPaciente(@RequestParam("CPF") String CPF, RedirectAttributes redirectAttributes) {

        try {
            consultaDAO.deleteByPacienteCPF(CPF);


            pacienteDAO.deleteByCPF(CPF);


            redirectAttributes.addFlashAttribute("message", "removeu.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "erro.");
        }

        return new RedirectView("/admin");
    }
}
