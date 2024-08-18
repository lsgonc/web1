package br.ufscar.dc.dsw.ClinicaMedica.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Medico;

@SuppressWarnings("unchecked")
public interface IMedicoDAO extends CrudRepository<Medico, String> {
    Medico findByCrm(String crm);
    List<Medico> findAll();
    Medico save(Medico medico);
    void deleteById(String crm);

    List<Medico> findByEspecialidade(String especialidade);
}
