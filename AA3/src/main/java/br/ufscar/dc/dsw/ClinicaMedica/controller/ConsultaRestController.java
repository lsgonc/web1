package br.ufscar.dc.dsw.ClinicaMedica.controller;

import br.ufscar.dc.dsw.ClinicaMedica.domain.Consulta;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Usuario;
import br.ufscar.dc.dsw.ClinicaMedica.service.impl.ConsultaService;
import br.ufscar.dc.dsw.ClinicaMedica.service.spec.IConsultaService;
import br.ufscar.dc.dsw.ClinicaMedica.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.ClinicaMedica.service.spec.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@RestController
public class ConsultaRestController {
    @Autowired
    private IConsultaService service;
    @Autowired
    private ConsultaService consultaService;

    @GetMapping(path = "/consultas")
    public ResponseEntity<List<Consulta>> lista() {
        List<Consulta> lista = service.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/consultas/{id}")
    public ResponseEntity<Consulta> lista(@PathVariable("id") Integer id) {
        Optional<Consulta> Consulta = service.buscaPorId(id);
        if (Consulta == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Consulta.orElse(null));
    }

    @GetMapping(path = "/consultas/pacientes/{cpf}")
    public ResponseEntity<List<Consulta>> listaPaciente(@PathVariable("cpf") String cpf) {
        List<Consulta> list = service.buscarPeloCpf(cpf);
        if (list == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(list);
    }

    @GetMapping(path = "/consultas/medicos/{crm}")
    public ResponseEntity<List<Consulta>> listaMedico(@PathVariable("crm") String crm) {
        List<Consulta> list = service.buscarPeloCrm(crm);
        if (list == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(list);
    }



    @PostMapping(path = "/consultas")
    @ResponseBody
    public ResponseEntity<Consulta> cria(@RequestBody Consulta consulta, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            service.salvar(consulta);
            return ResponseEntity.ok(consulta);
        }
    }

    @PutMapping(path = "/consultas/{id}")
    public ResponseEntity<Consulta> atualiza(@PathVariable("id") Integer id, @RequestBody Consulta consulta,
                                            BindingResult result) {
        Optional<Consulta> e = service.buscaPorId(id);

        if (e==null) {
            return ResponseEntity.notFound().build();
        } else {
            consulta.setId(id);
            service.salvar(consulta);
            return ResponseEntity.ok(consulta);
        }
    }

    @DeleteMapping(path = "/consultas/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") Integer id) {

        Optional<Consulta> consulta = service.buscaPorId(id);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        } else {
            service.excluir(id);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/consultas/pacientes/exlcuirTodas/{cpf}")
    public ResponseEntity<Boolean> removePaciente(@PathVariable("cpf") String cpf) {

        List<Consulta> consulta = service.buscarPeloCpf(cpf);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        } else {
            service.excluirPorCpf(cpf);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/consultas/medicos/excluirTodas/{crm}")
    public ResponseEntity<Boolean> removeMedico(@PathVariable("crm") String crm) {

        List<Consulta> consulta = service.buscarPeloCrm(crm);
        if (consulta == null) {
            return ResponseEntity.notFound().build();
        } else {
            service.excluirPorCpf(crm);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/consultas/medicos/excluir/")
    public ResponseEntity<Boolean> removeDataHoraMedico(@RequestBody String crm, Date data, Time hora) {
        boolean e = service.existePeloCrmEData(crm, data, hora);

        if (e != false) {
            service.existePeloCrmEData(crm,data,hora);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(path = "/consultas/pacientes/excluir/")
    public ResponseEntity<Boolean> removeDataHoraPaciente(@RequestBody String cpf, Date data, Time hora) {
        boolean e = service.existePeloCpfEData(cpf, data, hora);

        if (e != false) {
            service.existePeloCpfEData(cpf,data,hora);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
