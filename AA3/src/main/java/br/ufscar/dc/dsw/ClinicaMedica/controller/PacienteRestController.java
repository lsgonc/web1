package br.ufscar.dc.dsw.ClinicaMedica.controller;

import java.util.List;

import br.ufscar.dc.dsw.ClinicaMedica.domain.Paciente;
import br.ufscar.dc.dsw.ClinicaMedica.service.spec.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RestController
public class PacienteRestController {

    @Autowired
    private IPacienteService service;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(path = "/pacientes")
    public ResponseEntity<List<Paciente>> lista() {
        List<Paciente> lista = service.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/pacientes/{cpf}")
    public ResponseEntity<Paciente> lista(@PathVariable("cpf") String cpf) {
        Paciente paciente = service.buscaPorCPF(cpf);
        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paciente);
    }

    @PostMapping(path = "/pacientes")
    @ResponseBody
    public ResponseEntity<List<Paciente>> cria(@RequestBody List<Paciente> pacientes, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            for (Paciente paciente : pacientes) {
                paciente.setSenha(passwordEncoder.encode(paciente.getSenha()));
                service.salvar(paciente);
            }
            return ResponseEntity.ok(pacientes);
        }
    }

    @PutMapping(path = "/pacientes/{cpf}")
    public ResponseEntity<Paciente> atualiza(@PathVariable("cpf") String cpf, @RequestBody Paciente pacienteAtualizado,
                                            BindingResult result) {
        Paciente pacienteExistente = service.buscaPorCPF(cpf);

        if (pacienteExistente == null) {
            return ResponseEntity.notFound().build();
        }

        if (pacienteAtualizado.getNome() != null) {
            pacienteExistente.setNome(pacienteAtualizado.getNome());
        }
        if (pacienteAtualizado.getTelefone() != null) {
            pacienteExistente.setTelefone(pacienteAtualizado.getTelefone());
        }
        if (pacienteAtualizado.getSexo() != null) {
            pacienteExistente.setSexo(pacienteAtualizado.getSexo());
        }
        if (pacienteAtualizado.getDataNascimento() != null) {
            pacienteExistente.setDataNascimento(pacienteAtualizado.getDataNascimento());
        }
        if (pacienteAtualizado.getSenha() != null) {
            pacienteExistente.setSenha(passwordEncoder.encode(pacienteAtualizado.getSenha()));
        }

        service.salvar(pacienteExistente);
        return ResponseEntity.ok(pacienteExistente);
    }


    @DeleteMapping(path = "/pacientes/{cpf}")
    public ResponseEntity<Boolean> remove(@PathVariable("cpf") String cpf) {

        Paciente paciente = service.buscaPorCPF(cpf);
        if (paciente == null) {
            return ResponseEntity.notFound().build();
        } else {
                service.excluir(cpf);
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
    }
}