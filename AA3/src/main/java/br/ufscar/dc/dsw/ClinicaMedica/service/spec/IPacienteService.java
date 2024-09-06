package br.ufscar.dc.dsw.ClinicaMedica.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Paciente;

public interface IPacienteService {
    Paciente buscaPorCPF(String cpf);
    List<Paciente> buscarTodos();
    void salvar(Paciente paciente);
    void excluir(String id);

}
