package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Paciente;

public class PacienteDAO extends MainDAO {

    public void insert(Paciente paciente) {
        String sql = "INSERT INTO pacientes (email, senha, CPF, nome, telefone, sexo, data_nascimento) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, paciente.getEmail());
            statement.setString(2, paciente.getSenha());
            statement.setString(3, paciente.getCpf());
            statement.setString(4, paciente.getNome());
            statement.setString(5, paciente.getTelefone());
            statement.setString(6, paciente.getSexo());
            statement.setDate(7, java.sql.Date.valueOf(paciente.getDataNascimento()));

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Paciente get(int id) {
        Paciente paciente = null;
        String sql = "SELECT * FROM pacientes WHERE id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("CPF");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                java.sql.Date dataNascimento = resultSet.getDate("data_nascimento");

                paciente = new Paciente(id, email, senha, cpf, nome, telefone, sexo, dataNascimento.toLocalDate());
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paciente;
    }

    public void update(Paciente paciente) {
        String sql = "UPDATE pacientes SET email = ?, senha = ?, CPF = ?, nome = ?, telefone = ?, sexo = ?, data_nascimento = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, paciente.getEmail());
            statement.setString(2, paciente.getSenha());
            statement.setString(3, paciente.getCpf());
            statement.setString(4, paciente.getNome());
            statement.setString(5, paciente.getTelefone());
            statement.setString(6, paciente.getSexo());
            statement.setDate(7, java.sql.Date.valueOf(paciente.getDataNascimento()));
            statement.setInt(8, paciente.getId());

            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(Paciente paciente) {
        String sql = "DELETE FROM pacientes WHERE id = ?";

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

    public List<Paciente> getAll() {
        List<Paciente> listaPacientes = new ArrayList<>();
        String sql = "SELECT * FROM pacientes";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String cpf = resultSet.getString("CPF");
                String nome = resultSet.getString("nome");
                String telefone = resultSet.getString("telefone");
                String sexo = resultSet.getString("sexo");
                java.sql.Date dataNascimento = resultSet.getDate("data_nascimento");

                Paciente paciente = new Paciente(id, email, senha, cpf, nome, telefone, sexo, dataNascimento.toLocalDate());
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
}
