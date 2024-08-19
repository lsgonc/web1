package br.ufscar.dc.dsw.ClinicaMedica.security;

import br.ufscar.dc.dsw.ClinicaMedica.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioDAO dao;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Optional<Usuario> optionalUser = Optional.ofNullable(dao.findByEmail(email));

        Usuario user = optionalUser.orElseThrow(() ->
                new UsernameNotFoundException("Could not find user with email: " + email));


        return new UsuarioDetails(user);
    }


}
