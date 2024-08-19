package br.ufscar.dc.dsw.ClinicaMedica.controller;

import java.util.List;
import java.util.Locale;

import br.ufscar.dc.dsw.ClinicaMedica.dao.IConsultaDAO;
import br.ufscar.dc.dsw.ClinicaMedica.dao.IMedicoDAO;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Consulta;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Medico;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Usuario;
import br.ufscar.dc.dsw.ClinicaMedica.security.UsuarioDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsultaController {

    private final IConsultaDAO consultaDAO;
    private final IMedicoDAO medicoDAO;

    @Autowired
    public ConsultaController(IConsultaDAO consultaDAO, IMedicoDAO medicoDAO) {
        this.consultaDAO = consultaDAO;
        this.medicoDAO = medicoDAO;
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
        model.addAttribute("usuario", usuario.getNome());
        return "consultas/paciente";
    }
}

