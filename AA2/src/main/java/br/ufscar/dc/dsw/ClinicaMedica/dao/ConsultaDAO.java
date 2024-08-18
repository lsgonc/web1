package br.ufscar.dc.dsw.ClinicaMedica.dao;

import java.util.Optional;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Consulta;

@SuppressWarnings("unchecked")
public interface ConsultaDAO extends CrudRepository<Consulta, Integer> {
    
    Optional<Consulta> findById(Integer id);
    List<Consulta> findAll();
    Consulta save(Consulta consulta);
    void deleteById(Integer id);

    List<Consulta> findByPacienteCpf(String cpf);

    List<Consulta> findByMedicoCrm(String crm);

    boolean existsByMedicoCrmAndDataConsultaAndHoraConsulta(String crm, java.sql.Date dataConsulta, java.sql.Time horaConsulta);

    boolean existsByPacienteCpfAndDataConsultaAndHoraConsulta(String cpf, java.sql.Date dataConsulta, java.sql.Time horaConsulta);

}

