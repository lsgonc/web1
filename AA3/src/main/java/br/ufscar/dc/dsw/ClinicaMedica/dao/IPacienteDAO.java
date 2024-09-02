package br.ufscar.dc.dsw.ClinicaMedica.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Paciente;

@SuppressWarnings("unchecked")
public interface IPacienteDAO extends CrudRepository<Paciente, String> {
    Paciente findByCPF(String CPF);
    List<Paciente> findAll();
    Paciente save(Paciente paciente);
    void deleteByCPF(String CPF);
}

