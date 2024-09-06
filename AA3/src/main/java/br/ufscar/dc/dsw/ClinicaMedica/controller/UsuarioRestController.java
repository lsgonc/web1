package br.ufscar.dc.dsw.ClinicaMedica.controller;

import br.ufscar.dc.dsw.ClinicaMedica.domain.Usuario;
import br.ufscar.dc.dsw.ClinicaMedica.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.ClinicaMedica.service.spec.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioRestController {
    @Autowired
    private IUsuarioService service;

    @GetMapping(path = "/api/usuarios")
    public ResponseEntity<List<Usuario>> lista() {
        List<Usuario> lista = service.buscarTodos();
        if (lista.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping(path = "/api/usuarios/{id}")
    public ResponseEntity<Usuario> lista(@PathVariable("id") Integer id) {
        Optional<Usuario> Usuario = service.buscarPorId(id);
        if (Usuario == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(Usuario.orElse(null));
    }

    @PostMapping(path = "/api/usuarios")
    @ResponseBody
    public ResponseEntity<Usuario> cria(@RequestBody Usuario Usuario, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        } else {
            service.salvar(Usuario);
            return ResponseEntity.ok(Usuario);
        }
    }

    @PutMapping(path = "/api/usuarios/{id}")
    public ResponseEntity<Usuario> atualiza(@PathVariable("id") Integer id, @RequestBody Usuario Usuario,
                                           BindingResult result) {
        Optional<Usuario> e = service.buscarPorId(id);

        if (e==null) {
            return ResponseEntity.notFound().build();
        } else {
            Usuario.setId(id);
            service.salvar(Usuario);
            return ResponseEntity.ok(Usuario);
        }
    }

    @DeleteMapping(path = "/api/usuarios/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") Integer id) {

        Optional<Usuario> Usuario = service.buscarPorId(id);
        if (Usuario == null) {
            return ResponseEntity.notFound().build();
        } else {
            service.excluir(id);
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        }
    }
}
