package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Paciente;

public class PacienteDAO extends MainDAO {

    public Paciente get(String CPF) {
        Paciente paciente = null;
        String sql = "SELECT u.id, u.email, u.senha, u.nome, u.tipo_usuario, p.CPF, p.telefone, p.sexo, p.data_nascimento FROM paciente p JOIN usuario u ON p.usuario_id = u.id WHERE p.CPF = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, CPF);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String tipoUsuario = resultSet.getString("tipo_usuario");
                String cpf = resultSet.getString("CPF");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                Date dataNascimento = resultSet.getDate("data_nascimento");

                paciente = new Paciente(id, email, senha, nome, tipoUsuario, cpf, telefone, sexo, dataNascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paciente;
    }

    public Paciente getById(int ID) {
        Paciente paciente = null;
        String sql = "SELECT u.id, u.email, u.senha, u.nome, u.tipo_usuario, p.CPF, p.telefone, p.sexo, p.data_nascimento FROM paciente p JOIN usuario u ON p.usuario_id = u.id WHERE u.id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, ID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String tipoUsuario = resultSet.getString("tipo_usuario");
                String cpf = resultSet.getString("CPF");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                Date dataNascimento = resultSet.getDate("data_nascimento");

                paciente = new Paciente(id, email, senha, nome, tipoUsuario, cpf, telefone, sexo, dataNascimento);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paciente;
    }

    public List<Paciente> getAll() {
        List<Paciente> listaPacientes = new ArrayList<>();
        String sql = "SELECT u.id, u.email, u.senha, u.nome, u.tipo_usuario, p.CPF, p.telefone, p.sexo, p.data_nascimento FROM paciente p JOIN usuario u ON p.usuario_id = u.id";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String tipoUsuario = resultSet.getString("tipo_usuario");
                String cpf = resultSet.getString("CPF");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                Date dataNascimento = resultSet.getDate("data_nascimento");

                Paciente paciente = new Paciente(id, email, senha, nome, tipoUsuario, cpf, telefone, sexo, dataNascimento);
                listaPacientes.add(paciente);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPacientes;
    }

    public void insert(Paciente paciente) {
        String sql = "INSERT INTO paciente (usuario_id, CPF, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, paciente.getId());
            statement.setString(2, paciente.getCpf());
            statement.setString(3, paciente.getTelefone());
            statement.setString(4, paciente.getSexo());
            statement.setObject(5, paciente.getDataNascimento());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Paciente paciente) {
        String sql = "DELETE FROM usuario WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, paciente.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Paciente paciente) {
        String sql = "UPDATE paciente SET CPF = ?, telefone = ?, sexo = ?, data_nascimento = ? WHERE usuario_id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, paciente.getCpf());
            statement.setString(2, paciente.getTelefone());
            statement.setString(3, paciente.getSexo());
            statement.setDate(4, paciente.getDataNascimento());
            statement.setInt(5, paciente.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
