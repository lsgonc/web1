package br.ufscar.dc.dsw.ClinicaMedica.controller;

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

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsultaController {

    private final IConsultaDAO consultaDAO;

    public ConsultaController(IConsultaDAO consultaDAO) {
        this.consultaDAO = consultaDAO;
    }

    private Usuario getUser() {
		UsuarioDetails usuarioDetails = (UsuarioDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return usuarioDetails.getUser();
	}

    @GetMapping("/consultaMedico")
    public String cmedico(Model model, Locale locale) {
        Usuario usuario = getUser();
        
        model.addAttribute("usuario", usuario.getNome());

        return "consultas/medico";
    }

    @GetMapping("/consultaPaciente")
    public String cpaciente(Model model, Locale locale) {
        Usuario usuario = getUser();
        
        model.addAttribute("usuario", usuario.getNome());

        return "consultas/paciente";
    }
}