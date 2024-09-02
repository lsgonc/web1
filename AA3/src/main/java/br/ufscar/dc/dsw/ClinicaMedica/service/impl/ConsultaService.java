package br.ufscar.dc.dsw.ClinicaMedica.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.ClinicaMedica.dao.IConsultaDAO;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Consulta;
import br.ufscar.dc.dsw.ClinicaMedica.service.spec.IConsultaService;

@Service
@Transactional(readOnly = false)
public class ConsultaService implements IConsultaService {

}
