package br.ufscar.dc.dsw.ClinicaMedica.service.impl;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.ClinicaMedica.dao.IConsultaDAO;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Consulta;
import br.ufscar.dc.dsw.ClinicaMedica.service.spec.IConsultaService;

@Service
@Transactional(readOnly = false)
public class ConsultaService implements IConsultaService {
    IConsultaDAO dao;

    public Optional<Consulta> buscaPorId(Integer id) {
        return dao.findById(id);
    }

    public List<Consulta> buscarTodos() {
        return dao.findAll();
    }

    public List<Consulta> buscarPeloCpf(String CPF) {
        return  dao.findByPacienteCPF(CPF);
    }

    public List<Consulta> buscarPeloCrm(String CRM) {
        return dao.findByMedicoCRM(CRM);
    }

    public Consulta salvar(Consulta consulta) {
        return  dao.save(consulta);
    }

    public boolean existePeloCrmEData(String CRM, Date dataConsulta, Time horaConsulta) {
        return dao.existsByMedicoCRMAndDataConsultaAndHoraConsulta(CRM, dataConsulta, horaConsulta);
    }

    public boolean existePeloCpfEData(String CPF, Date dataConsulta, Time horaConsulta) {
        return dao.existsByPacienteCPFAndDataConsultaAndHoraConsulta(CPF, dataConsulta, horaConsulta);
    }

    public void excluir(Integer id) {
        dao.deleteById(id);
    }

    public void excluirPorCpf(String CPF) {
        dao.deleteByPacienteCPF(CPF);
    }

    public void excluirPorCrm(String CRM) {
        dao.deleteByMedicoCRM(CRM);
    }
}
