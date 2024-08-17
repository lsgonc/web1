package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Consulta;
import br.ufscar.dc.dsw.domain.Medico;
import br.ufscar.dc.dsw.domain.Paciente;

public class ConsultaDAO extends MainDAO{
    
    public List<Consulta> getAllPaciente(String CPF) {
        List<Consulta> listaConsultas = new ArrayList<>();
        String sql = "SELECT * FROM consulta WHERE paciente_cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, CPF);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cpf = resultSet.getString("paciente_cpf");
                String crm = resultSet.getString("medico_crm");
                Date data_consulta = resultSet.getDate("data_consulta");
                Time hora_consulta = resultSet.getTime("hora_consulta");
                
                Paciente paciente = new PacienteDAO().get(cpf);
                Medico medico = new MedicoDAO().get(crm);

                Consulta consulta = new Consulta(id, paciente, medico, data_consulta, hora_consulta);
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

    public List<Consulta> getAllMedicos(String CRM) {
        List<Consulta> listaConsultas = new ArrayList<>();
        String sql = "SELECT * FROM consulta WHERE medico_crm = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, CRM);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cpf = resultSet.getString("paciente_cpf");
                String crm = resultSet.getString("medico_crm");
                Date data_consulta = resultSet.getDate("data_consulta");
                Time hora_consulta = resultSet.getTime("hora_consulta");


                Consulta consulta = new Consulta(id, new PacienteDAO().get(cpf), new MedicoDAO().get(crm), data_consulta, hora_consulta);
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

    public void insert(Consulta consulta) {
        String sql = "INSERT INTO consulta (paciente_cpf, medico_crm, data_consulta, hora_consulta) VALUES (?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, consulta.getPaciente().getCpf());
            statement.setString(2, consulta.getMedico().getCrm());
            statement.setDate(3, consulta.getData());
            statement.setTime(4, consulta.getHora());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean medicoTemConsulta(String medico_crm, Date dataConsulta, Time horaConsulta) {
        String sql = "SELECT COUNT(*) FROM consulta WHERE medico_crm = ? AND data_consulta = ? AND hora_consulta = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, medico_crm);
            statement.setDate(2, dataConsulta);
            statement.setTime(3, horaConsulta);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public boolean pacienteTemConsulta(String paciente_cpf, Date dataConsulta, Time horaConsulta) {
        String sql = "SELECT COUNT(*) FROM consulta WHERE paciente_cpf = ? AND data_consulta = ? AND hora_consulta = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, paciente_cpf);
            statement.setDate(2, dataConsulta);
            statement.setTime(3, horaConsulta);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
            
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    public void delete(Consulta consulta) {
        String sql = "DELETE FROM consulta WHERE id = ?";

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

    // Ainda não possui verificação de disponibilidade de horario do médico
    // Antes de atulizar a consulta, é necessário ver se não há outra marcada no mesmo horário
    public void update(Consulta consulta) {
        String sql = "UPDATE consulta SET medico_crm = ?, data_consulta = ?, hora_consulta = ? WHERE paciente_cpf = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, consulta.getMedico().getCrm());
            statement.setDate(2, consulta.getData());
            statement.setTime(3, consulta.getHora());
            statement.setString(4, consulta.getPaciente().getCpf());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
