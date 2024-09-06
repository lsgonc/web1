package br.ufscar.dc.dsw.ClinicaMedica.service.spec;

import java.util.List;
import java.util.Optional;

import br.ufscar.dc.dsw.ClinicaMedica.domain.Usuario;

public interface IUsuarioService {
    Optional<Usuario> buscarPorId(Integer id);

    List<Usuario> buscarTodos();

    void salvar(Usuario usuario);

    void excluir(Integer id);
}
