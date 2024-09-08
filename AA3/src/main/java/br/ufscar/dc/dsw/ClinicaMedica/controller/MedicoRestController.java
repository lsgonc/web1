package br.ufscar.dc.dsw.ClinicaMedica.controller;

import br.ufscar.dc.dsw.ClinicaMedica.domain.Medico;
import br.ufscar.dc.dsw.ClinicaMedica.service.spec.IMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RestController
public class MedicoRestController {
    @Autowired
    private IMedicoService service;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping(path = "/medicos")
    public ResponseEntity<List<Medico>> lista() {
        List<Medico> lista = service.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/medicos/{crm}")
    public ResponseEntity<Medico> lista(@PathVariable("crm") String crm) {
        Medico Medico = service.buscaPorCrm(crm);
        if (Medico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Medico);
    }

    @GetMapping(path = "/medicos/especialidades/{especialidade}")
    public ResponseEntity<List<Medico>> buscaPorEspecialidade(@PathVariable("especialidade") String especialidade) {
        List<Medico> medicos = service.buscaPorEspecialidade(especialidade);
        if (medicos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(medicos);
    }

    @PostMapping(path = "/medicos")
    @ResponseBody
    public ResponseEntity<List<Medico>> cria(@RequestBody List<Medico> medicos, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            for (Medico medico : medicos) {
                medico.setSenha(passwordEncoder.encode(medico.getSenha()));
                service.salvar(medico);
            }
            return ResponseEntity.ok(medicos);
        }
    }

    @PutMapping(path = "/medicos/{crm}")
    public ResponseEntity<Medico> atualiza(@PathVariable("crm") String crm, @RequestBody Medico medico,
                                            BindingResult result) {
        Medico e = service.buscaPorCrm(crm);

        if (e == null) {
            return ResponseEntity.notFound().build();
        } else {
            if (medico.getNome() != null) {
                e.setNome(medico.getNome());
            }
            if (medico.getEmail() != null) {
                e.setEmail(medico.getEmail());
            }
            if (medico.getEspecialidade() != null) {
                e.setEspecialidade(medico.getEspecialidade());
            }
            if (medico.getCRM() != null) {
                e.setCRM(medico.getCRM());
            }

            service.salvar(e);
            return ResponseEntity.ok(e);
        }
    }


    @DeleteMapping(path = "/medicos/{crm}")
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
