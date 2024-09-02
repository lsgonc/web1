package br.ufscar.dc.dsw.ClinicaMedica.controller;

import br.ufscar.dc.dsw.ClinicaMedica.dao.IConsultaDAO;
import br.ufscar.dc.dsw.ClinicaMedica.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Medico;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Paciente;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.ClinicaMedica.dao.IPacienteDAO;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;

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

            redirectAttributes.addFlashAttribute("message", "Paciente removido com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao remover paciente.");
        }

        return new RedirectView("/admin");
    }

    @PostMapping("/paciente/insercao")
    public RedirectView inserirPaciente(@RequestParam("CPF") String CPF,
            @RequestParam("nome") String nome,
            @RequestParam("email") String email,
            @RequestParam("senha") String senha,
            @RequestParam("telefone") String telefone,
            @RequestParam("sexo") String sexo,
            @RequestParam("dataNascimento") String dataNascimento,
            RedirectAttributes redirectAttributes) {

        try {
            Paciente.Sexo sexoEnum;

            if (Objects.equals(sexo, "Masculino")) {
                sexoEnum = Paciente.Sexo.Masculino;
            } else if (Objects.equals(sexo, "Feminino")) {
                sexoEnum = Paciente.Sexo.Feminino;
            } else {
                sexoEnum = Paciente.Sexo.Outro;
            }

            Paciente paciente = new Paciente(CPF, email, senha, Usuario.TipoUsuario.PACIENTE, nome, telefone, sexoEnum,
                    dataNascimento);

            pacienteDAO.save(paciente);                                       
                                      

            redirectAttributes.addFlashAttribute("message", "Paciente inserido.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao inserir paciente.");
        }

        return new RedirectView("/admin");
    }

    @PostMapping("/paciente/editar")
    public String editarMedico(Model model, @RequestParam("CPF") String CPF) {

        Paciente pacienteEdit = pacienteDAO.findByCPF(CPF);

        model.addAttribute("paciente", pacienteEdit);

        return "/admin/editar/paciente";
    }

    @Transactional
    @PostMapping("/paciente/editarPaciente")
    public RedirectView editarReal(Model model, @RequestParam("CPF") String CPF,
            @RequestParam("nome") String nome,
            @RequestParam("email") String email,
            @RequestParam("NOVO_CPF") String novoCPF,
            @RequestParam("telefone") String telefone,
            @RequestParam("sexo") String sexo,
            @RequestParam("dataNascimento") String dataNascimento,

            RedirectAttributes redirectAttributes) {

        try {

            Paciente.Sexo sexoEnum;

            if (Objects.equals(sexo, "Masculino")) {
                sexoEnum = Paciente.Sexo.Masculino;
            } else if (Objects.equals(sexo, "Feminino")) {
                sexoEnum = Paciente.Sexo.Feminino;
            } else {
                sexoEnum = Paciente.Sexo.Outro;
            }
            Paciente paciente = pacienteDAO.findByCPF(CPF);

            paciente.setCPF(novoCPF);
            paciente.setNome(nome);
            paciente.setEmail(email);
            paciente.setTelefone(telefone);
            paciente.setSexo(sexoEnum);
            paciente.setDataNascimento(dataNascimento);

            redirectAttributes.addFlashAttribute("message", "Paciente editado com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao editar paciente.");
        }

        return new RedirectView("/admin");
    }
}
