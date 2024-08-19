package br.ufscar.dc.dsw.ClinicaMedica.controller;

import br.ufscar.dc.dsw.ClinicaMedica.dao.IConsultaDAO;
import br.ufscar.dc.dsw.ClinicaMedica.dao.IMedicoDAO;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Medico;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class MedicoController {

    private final IConsultaDAO consultaDAO;
    private final IMedicoDAO medicoDAO;

    @Autowired
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
            redirectAttributes.addFlashAttribute("message", "Médico removido com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao remover médico.");
        }

        return new RedirectView("/admin");
    }

    @GetMapping("/medico")
    public String listarMedicos(Model model) {
        try {
            List<Medico> medicos = medicoDAO.findAll();
            model.addAttribute("listaMedicos", medicos);
        } catch (Exception e) {
            model.addAttribute("error", "Erro ao listar médicos.");
        }

        return "medicos/index";
    }

    @PostMapping("/medico/insercao")
    public RedirectView inserirMedico(@RequestParam("CRM") String CRM,
            @RequestParam("nome") String nome,
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            @RequestParam("especialidade") String especialidade,
            RedirectAttributes redirectAttributes) {

        try {
            Medico medico = new Medico(CRM, email, senha, Usuario.TipoUsuario.MEDICO, nome, especialidade);

            medicoDAO.save(medico);

            redirectAttributes.addFlashAttribute("message", "Medico inserido com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao inserir medico.");
        }

        return new RedirectView("/admin");
    }

    @PostMapping("/medico/editar")
    public String editarMedico(Model model, @RequestParam("CRM") String CRM) {

        Medico medicoEdit = medicoDAO.findByCRM(CRM);

        model.addAttribute("medico", medicoEdit);

        return "/admin/editar/medico";
    }

    @Transactional
    @PostMapping("/medico/editarMedico")
    public RedirectView editarReal(Model model, @RequestParam("CRM") String CRM,
            @RequestParam("email") String email,
            @RequestParam("nome") String nome,
            @RequestParam("especialidade") String especialidade,
            @RequestParam("NOVO_CRM") String novoCrm,

            RedirectAttributes redirectAttributes) {

        try {

            Medico medicoEdit = medicoDAO.findByCRM(CRM);

            medicoEdit.setEmail(email);
            medicoEdit.setNome(nome);
            medicoEdit.setEspecialidade(especialidade);
            medicoEdit.setCRM(novoCrm);

            redirectAttributes.addFlashAttribute("message", "Medico inserido com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao inserir medico.");
        }

        return new RedirectView("/admin");
    }
}
