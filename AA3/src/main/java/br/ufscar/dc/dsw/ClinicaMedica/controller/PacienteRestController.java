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

import jakarta.*;

@RestController
public class PacienteRestController {

    @Autowired
    private IPacienteService service;

    @GetMapping(path = "/api/pacientes")
    public ResponseEntity<List<Paciente>> lista() {
        List<Paciente> lista = service.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/api/pacientes/{cpf}")
    public ResponseEntity<Paciente> lista(@PathVariable("cpf") String cpf) {
        Paciente paciente = service.buscaPorCPF(cpf);
        if (paciente == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(paciente);
    }

    @PostMapping(path = "/api/pacientes")
    @ResponseBody
    public ResponseEntity<Paciente> cria(@RequestBody Paciente paciente, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            service.salvar(paciente);
            return ResponseEntity.ok(paciente);
        }
    }

    @PutMapping(path = "/api/pacientes/{cpf}")
    public ResponseEntity<Paciente> atualiza(@PathVariable("cpf") String cpf, @RequestBody Paciente paciente,
                                            BindingResult result) {
        Paciente e = service.buscaPorCPF(cpf);

        if (e==null) {
            return ResponseEntity.notFound().build();
        } else {
                paciente.setCPF(cpf);
                service.salvar(paciente);
                return ResponseEntity.ok(paciente);
        }
    }

    @DeleteMapping(path = "/api/pacientes/{cpf}")
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