package br.ufscar.dc.dsw.domain;

import java.time.LocalDateTime;

public class Consulta {
    private int id;
    private String pacienteCpf;
    private String medicoCrm;
    private LocalDateTime dataHora;

    public Consulta(int id, String pacienteCpf, String medicoCrm, LocalDateTime dataHora) {
        this.id = id;
        this.pacienteCpf = pacienteCpf;
        this.medicoCrm = medicoCrm;
        this.dataHora = dataHora;
    }

    public Consulta(String pacienteCpf, String medicoCrm, LocalDateTime dataHora) {
        this.pacienteCpf = pacienteCpf;
        this.medicoCrm = medicoCrm;
        this.dataHora = dataHora;
    }

    public Consulta(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPacienteCpf() {
        return pacienteCpf;
    }

    public void setPacienteCpf(String pacienteCpf) {
        this.pacienteCpf = pacienteCpf;
    }

    public String getMedicoCrm() {
        return medicoCrm;
    }

    public void setMedicoCrm(String medicoCrm) {
        this.medicoCrm = medicoCrm;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
