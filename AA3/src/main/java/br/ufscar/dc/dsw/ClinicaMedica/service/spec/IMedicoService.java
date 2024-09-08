package br.ufscar.dc.dsw.ClinicaMedica.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Medico;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Paciente;

public interface IMedicoService {
    Medico buscaPorCrm(String crm);
    List<Medico> buscarTodos();
    void salvar(Medico medico);
    void excluir(String crm);
    List<Medico> buscaPorEspecialidade(String especialidade);
}   