package br.ufscar.dc.dsw.ClinicaMedica.service.spec;

import java.util.List;
import java.util.Optional;

import br.ufscar.dc.dsw.ClinicaMedica.domain.Consulta;

public interface IConsultaService {

    Optional<Consulta> buscaPorId(Integer id);
    List<Consulta> buscarTodos();
    List<Consulta> buscarPeloCpf(String CPF);
    List<Consulta> buscarPeloCrm(String CRM);

    Consulta salvar(Consulta consulta);


    boolean existePeloCrmEData(String CRM, java.sql.Date dataConsulta, java.sql.Time horaConsulta);
    boolean existePeloCpfEData(String CPF, java.sql.Date dataConsulta, java.sql.Time horaConsulta);

    void excluir(Integer id);
    void excluirPorCpf(String CPF);
    void excluirPorCrm(String CRM);
}
