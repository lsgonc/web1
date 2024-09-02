package br.ufscar.dc.dsw.ClinicaMedica.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.ClinicaMedica.dao.IPacienteDAO;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Paciente;
import br.ufscar.dc.dsw.ClinicaMedica.service.spec.IPacienteService;

@Service
@Transactional(readOnly = false)
public class PacienteService implements IPacienteService {

}
