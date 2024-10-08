package br.ufscar.dc.dsw.ClinicaMedica.config;

import br.ufscar.dc.dsw.ClinicaMedica.security.UsuarioDetailsServiceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UsuarioDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeHttpRequests(authz -> authz
            // Permissões para controladores REST
                .requestMatchers("/pacientes", "/medicos", "/consultas").permitAll()
                .requestMatchers("/pacientes/*", "/medicos/*").permitAll()
                .requestMatchers("/consultas/*").permitAll()
                .requestMatchers("/medicos/especialidades/*").permitAll()
                .requestMatchers("/consultas/pacientes/*").permitAll()
                .requestMatchers("/consultas/medicos/*").permitAll()
            // Permissões do trabalho 2
                .requestMatchers("/", "/index", "/error", "/medico").permitAll()
                .requestMatchers("/login/**", "/js/**", "/css/**", "/image/**", "/webjars/**").permitAll()
                .requestMatchers("/admin/**", "/adm/**").hasRole("ADMIN")
                .requestMatchers("/medico/**", "/med/**").hasRole("ADMIN")
                .requestMatchers("/paciente/remocao").hasRole("ADMIN")
                .requestMatchers("/consultas/medico/").hasRole("MEDICO")
                .requestMatchers("/consultas/paciente/").hasRole("PACIENTE")
                .anyRequest().authenticated())
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
                .usernameParameter("email")
                .defaultSuccessUrl("/"))
            .logout(logout -> logout
                .logoutSuccessUrl("/")
                .permitAll());

        return http.build();
    }
}
