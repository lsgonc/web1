package br.ufscar.dc.dsw.ClinicaMedica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.ClinicaMedica.dao.IPacienteDAO;

@Controller
public class PacienteController {

    @Autowired
    private IPacienteDAO pacienteDAO;

    @PostMapping("/paciente/remocao")
    public String removerPaciente(@RequestParam("id") String cpf, RedirectAttributes redirectAttributes) {
        try {
            pacienteDAO.deleteById(cpf);
            redirectAttributes.addFlashAttribute("message", "removeu.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "erro.");
        }
        return "/adm";
    }
}
