package br.ufscar.dc.dsw.ClinicaMedica.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.ClinicaMedica.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Usuario;
import br.ufscar.dc.dsw.ClinicaMedica.service.spec.IUsuarioService;

@Service
@Transactional(readOnly = false)
public class UsuarioService implements IUsuarioService {
    @Autowired
    IUsuarioDAO dao;

    public void salvar(Usuario usuario) {
        dao.save(usuario);
    }

    public void excluir(Integer id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorId(Integer id) {
        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarTodos() {
        return dao.findAll();
    }
}
