package br.ufscar.dc.dsw.ClinicaMedica.dao;

import java.util.Optional;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Usuario;

@SuppressWarnings("unchecked")
public interface IUsuarioDAO extends CrudRepository<Usuario, Integer> {
    Optional<Usuario> findById(Integer id);
    Usuario findByEmail(String email);

    List<Usuario> findAll();
    Usuario save(Usuario usuario);
    void deleteById(Integer id);


}


