package br.ufscar.dc.dsw.ClinicaMedica.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "Consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "paciente_cpf", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "medico_crm", nullable = false)
    private Medico medico;

    @Column(name = "data_consulta", nullable = false)
    private java.sql.Date dataConsulta;

    @Column(name = "hora_consulta", nullable = false)
    private java.sql.Time horaConsulta;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public java.sql.Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(java.sql.Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public java.sql.Time getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(java.sql.Time horaConsulta) {
        this.horaConsulta = horaConsulta;
    }
}

