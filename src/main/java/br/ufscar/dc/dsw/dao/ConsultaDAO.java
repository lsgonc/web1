package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDateTime;


import br.ufscar.dc.dsw.domain.Consulta;

public class ConsultaDAO extends MainDAO {

    public void insert(Consulta consulta) {
        String sql = "INSERT INTO consultas (paciente_cpf, medico_crm, data_hora) VALUES (?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, consulta.getPacienteCpf());
            statement.setString(2, consulta.getMedicoCrm());
            statement.setTimestamp(3, Timestamp.valueOf(consulta.getDataHora()));

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Consulta get(int id) {
        Consulta consulta = null;
        String sql = "SELECT * FROM consultas WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String paciente_cpf = resultSet.getString("paciente_cpf");
                String medico_crm = resultSet.getString("medico_crm");
                Timestamp dataHora = resultSet.getTimestamp("data_hora");
                LocalDateTime data_hora = dataHora.toLocalDateTime();

                consulta = new Consulta(id, paciente_cpf, medico_crm, data_hora);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return consulta;
    }

    public void update(Consulta consulta) {
        String sql = "UPDATE consultas SET paciente_cpf = ?, medico_crm = ?, data_hora = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, consulta.getPacienteCpf());
            statement.setString(2, consulta.getMedicoCrm());
            statement.setTimestamp(3, Timestamp.valueOf(consulta.getDataHora()));

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Consulta consulta) {
        String sql = "DELETE FROM consultas WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, consulta.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Consulta> getAll() {
        List<Consulta> listaConsultas = new ArrayList<>();
        String sql = "SELECT * FROM consultas";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String paciente_cpf = resultSet.getString("paciente_cpf");
                String medico_crm = resultSet.getString("medico_crm");
                Timestamp dataHora = resultSet.getTimestamp("data_hora");
                LocalDateTime data_hora = dataHora.toLocalDateTime();

                Consulta consulta = new Consulta(id, paciente_cpf, medico_crm, data_hora);

                listaConsultas.add(consulta);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaConsultas;
    }



}