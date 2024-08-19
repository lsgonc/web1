package br.ufscar.dc.dsw.ClinicaMedica.security;

import br.ufscar.dc.dsw.ClinicaMedica.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.ClinicaMedica.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private IUsuarioDAO dao;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Usuario user = dao.findByEmail(email);


        if (user == null){
            throw new UsernameNotFoundException("Could not find user");
        }

        UsuarioDetails userDetails = new UsuarioDetails(user);

        System.out.println(userDetails.getUsername());
        System.out.println(userDetails.getPassword());
        System.out.println(userDetails.getAuthorities());

        return userDetails;
    }

}
