package br.ufscar.dc.dsw.ClinicaMedica;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufscar.dc.dsw.ClinicaMedica.domain.*;
import br.ufscar.dc.dsw.ClinicaMedica.dao.*;

import java.sql.Date;
import java.sql.Time;

@SpringBootApplication
public class ClinicaMedicaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicaMedicaApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(IMedicoDAO medicoDAO, IPacienteDAO pacienteDAO, IConsultaDAO consultaDAO) {
        return (args) -> {
            // Removido BCryptPasswordEncoder e a codificação das senhas
            Medico m1 = new Medico("123456", "medico1@gmail.com", "medico", Usuario.TipoUsuario.MEDICO, "Dr. João", "Cardiologia");
            Medico m2 = new Medico("654321", "medico2@gmail.com", "medico", Usuario.TipoUsuario.MEDICO, "Dr. Maria", "Neurologia");
            medicoDAO.save(m1);
            medicoDAO.save(m2);

            Paciente p1 = new Paciente("111.222.333-44", "paciente1@gmail.com", "paciente", Usuario.TipoUsuario.PACIENTE, "Ana", "(33) 77777-7777", Paciente.Sexo.Masculino, "1990-03-03");
            Paciente p2 = new Paciente("555.666.777-88", "paciente2@gmail.com", "paciente", Usuario.TipoUsuario.PACIENTE, "Carlos", "(44) 66666-6666", Paciente.Sexo.Masculino, "1985-04-04");
            pacienteDAO.save(p1);
            pacienteDAO.save(p2);

            Consulta c1 = new Consulta(p1, m1, Date.valueOf("2024-08-01"), Time.valueOf("10:00:00"));
            Consulta c2 = new Consulta(p2, m2, Date.valueOf("2024-08-02"), Time.valueOf("11:00:00"));
            consultaDAO.save(c1);
            consultaDAO.save(c2);
        };
    }
}

