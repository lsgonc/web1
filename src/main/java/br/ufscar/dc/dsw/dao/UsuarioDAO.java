package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Usuario;

public class UsuarioDAO extends MainDAO {

    public Usuario get(String emailUsuario) {
        Usuario user = null;

        String sql = "SELECT * FROM usuario WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, emailUsuario);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String tipo_usuario = resultSet.getString("tipo_usuario");
                user = new Usuario(id, email, senha, nome, tipo_usuario);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public List<Usuario> getAll() {

        List<Usuario> listaUsuarios = new ArrayList<>();

        String sql = "SELECT * FROM usuario ORDER BY nome";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String nome = resultSet.getString("nome");
                String tipo_usuario = resultSet.getString("tipo_usuario");
                Usuario user = new Usuario(id, email, senha, nome, tipo_usuario);
                listaUsuarios.add(user);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public void insert(Usuario user) {

        String sql = "INSERT INTO usuario(email, senha, nome, tipo_usuario) VALUES "
                + "(?, ?, ?, ?)";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getSenha());
            statement.setString(3, user.getNome());
            statement.setString(4, user.getTipoUsuario());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Usuario user) {
        String sql = "DELETE FROM usuario WHERE email = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, user.getEmail());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Usuario user) {
        String sql = "UPDATE usuario SET email = ?, senha = ?, nome = ? WHERE id = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getSenha());
            statement.setString(3, user.getNome());
            statement.setInt(4, user.getId());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}