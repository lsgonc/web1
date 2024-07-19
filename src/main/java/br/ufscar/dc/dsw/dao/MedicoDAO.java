package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Medico;

public class MedicoDAO extends MainDAO {

    public Medico get(String crm) {
        Medico med = null;

        String sql = "SELECT u.id, u.email, u.senha, u.nome, u.tipo_usuario, m.CRM, m.especialidade FROM medico m JOIN usuario u ON m.usuario_id = u.id WHERE m.CRM = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, crm);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String tipoUsuario = resultSet.getString("tipo_usuario");
                String CRM = resultSet.getString("CRM");
                String especialidade = resultSet.getString("especialidade");
                med = new Medico(id, email, senha, nome, tipoUsuario, CRM, especialidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return med;
    }

    public List<Medico> getAll() {

        List<Medico> listaMedicos = new ArrayList<>();

        String sql = "SELECT u.id, u.email, u.senha, u.nome, u.tipo_usuario, m.CRM, m.especialidade FROM medico m JOIN usuario u ON m.usuario_id = u.id";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String tipoUsuario = resultSet.getString("tipo_usuario");
                String CRM = resultSet.getString("CRM");
                String especialidade = resultSet.getString("especialidade");
                Medico med = new Medico(id, email, senha, nome, tipoUsuario, CRM, especialidade);
                listaMedicos.add(med);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaMedicos;
    }

    public void insert(Medico med) {

        String sql = "INSERT INTO medico (usuario_id, CRM, especialidade) VALUES (?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, med.getId());
            statement.setString(2, med.getCrm());
            statement.setString(3, med.getEspecialidade());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Medico med) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, med.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Medico med) {
        String sql = "UPDATE medico SET CRM = ?, especialidade = ? WHERE usuario_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, med.getCrm());
            statement.setString(2, med.getEspecialidade());
            statement.setInt(3, med.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}