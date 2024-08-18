package br.ufscar.dc.dsw.ClinicaMedica;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.ufscar.dc.dsw.ClinicaMedica.domain.*;
import br.ufscar.dc.dsw.ClinicaMedica.dao.*;

import java.sql.Date;
import java.sql.Time;

@SpringBootApplication
public class ClinicaMedicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicaMedicaApplication.class, args);
    }


}
