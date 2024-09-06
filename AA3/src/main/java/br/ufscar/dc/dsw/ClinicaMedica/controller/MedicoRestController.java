package br.ufscar.dc.dsw.ClinicaMedica.controller;

import br.ufscar.dc.dsw.ClinicaMedica.domain.Medico;
import br.ufscar.dc.dsw.ClinicaMedica.service.spec.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicoRestController {
    @Autowired
    private IMedicoService service;

    @GetMapping(path = "/api/medicos")
    public ResponseEntity<List<Medico>> lista() {
        List<Medico> lista = service.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/api/medicos/{crm}")
    public ResponseEntity<Medico> lista(@PathVariable("crm") String crm) {
        Medico Medico = service.buscaPorCrm(crm);
        if (Medico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Medico);
    }

    @PostMapping(path = "/api/medicos")
    @ResponseBody
    public ResponseEntity<Medico> cria(@RequestBody Medico Medico, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            service.salvar(Medico);
            return ResponseEntity.ok(Medico);
        }
    }

    @PutMapping(path = "/api/medicos/{crm}")
    public ResponseEntity<Medico> atualiza(@PathVariable("crm") String crm, @RequestBody Medico Medico,
                                             BindingResult result) {
        Medico e = service.buscaPorCrm(crm);

        if (e==null) {
            return ResponseEntity.notFound().build();
        } else {
            Medico.setCRM(crm);
            service.salvar(Medico);
            return ResponseEntity.ok(Medico);
        }
    }

    @DeleteMapping(path = "/api/medicos/{crm}")
    public ResponseEntity<Boolean> remove(@PathVariable("crm") String crm) {

        Medico Medico = service.buscaPorCrm(crm);
        if (Medico == null) {
            return ResponseEntity.notFound().build();
        } else {
            service.excluir(crm);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
    }
}
