package br.ufscar.dc.dsw.ClinicaMedica.dao;

import java.util.Optional;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Consulta;

@SuppressWarnings("unchecked")
public interface IConsultaDAO extends CrudRepository<Consulta, Integer> {

    Optional<Consulta> findById(Integer id);
    List<Consulta> findAll();
    Consulta save(Consulta consulta);
    void deleteById(Integer id);

    List<Consulta> findByPacienteCPF(String CPF);

    List<Consulta> findByMedicoCRM(String CRM);


    boolean existsByMedicoCRMAndDataConsultaAndHoraConsulta(String CRM, java.sql.Date dataConsulta, java.sql.Time horaConsulta);

    boolean existsByPacienteCPFAndDataConsultaAndHoraConsulta(String CPF, java.sql.Date dataConsulta, java.sql.Time horaConsulta);
    void deleteByPacienteCPF(String CPF);
    void deleteByMedicoCRM(String CRM);

}