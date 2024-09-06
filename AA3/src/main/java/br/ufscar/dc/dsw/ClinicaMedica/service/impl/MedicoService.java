package br.ufscar.dc.dsw.ClinicaMedica.service.impl;

import java.util.List;

import br.ufscar.dc.dsw.ClinicaMedica.domain.Medico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.ClinicaMedica.dao.IMedicoDAO;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Medico;
import br.ufscar.dc.dsw.ClinicaMedica.service.spec.IMedicoService;

@Service
@Transactional(readOnly = false)
public class MedicoService implements IMedicoService {
    @Autowired
    IMedicoDAO medicoDAO;


    public Medico buscaPorCrm(String crm) {
        return medicoDAO.findByCRM(crm);
    }

    public List<Medico> buscarTodos() {
        return medicoDAO.findAll();
    }

    public void salvar(Medico Medico) {
        medicoDAO.save(Medico);
    }

    public void excluir(String crm) {
        medicoDAO.deleteByCRM(crm);
    }
}
