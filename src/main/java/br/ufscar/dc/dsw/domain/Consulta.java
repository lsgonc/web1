package br.ufscar.dc.dsw.domain;

import java.sql.Date;
import java.sql.Time;

public class Consulta {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private Date data_consulta;
    private Time hora_consulta;

    // Construtor
    public Consulta(int id, Paciente paciente, Medico medico, Date data_consulta, Time hora_consulta) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.data_consulta = data_consulta;
        this.hora_consulta = hora_consulta;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getData() {
        return data_consulta;
    }

    public void setData(Date data_consulta) {
        this.data_consulta = data_consulta;
    }

    public Time getHora() {
        return hora_consulta;
    }

    public void setHora(Time hora_consulta) {
        this.hora_consulta = hora_consulta;
    }
}
