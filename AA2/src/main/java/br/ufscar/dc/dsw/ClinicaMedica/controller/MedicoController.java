package br.ufscar.dc.dsw.ClinicaMedica.controller;

import br.ufscar.dc.dsw.ClinicaMedica.dao.IConsultaDAO;
import br.ufscar.dc.dsw.ClinicaMedica.dao.IMedicoDAO;
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
public class MedicoController {

    @Autowired

    private IConsultaDAO consultaDAO;
    private IMedicoDAO medicoDAO;

    public MedicoController(IConsultaDAO consultaDAO, IMedicoDAO medicoDAO) {

        this.consultaDAO = consultaDAO;
        this.medicoDAO = medicoDAO;
    }

    @Transactional
    @PostMapping("/medico/remocao")
    public RedirectView removerMedico(@RequestParam("CRM") String CRM, RedirectAttributes redirectAttributes) {

        try {
            consultaDAO.deleteByMedicoCRM(CRM);


            medicoDAO.deleteByCRM(CRM);


            redirectAttributes.addFlashAttribute("message", "removeu.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "erro.");
        }

        return new RedirectView("/admin");
    }
}
