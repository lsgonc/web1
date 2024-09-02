package br.ufscar.dc.dsw.ClinicaMedica.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.ClinicaMedica.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Usuario;
import br.ufscar.dc.dsw.ClinicaMedica.service.spec.IUsuarioService;

@Service
@Transactional(readOnly = false)
public class UsuarioService implements IUsuarioService {

}
