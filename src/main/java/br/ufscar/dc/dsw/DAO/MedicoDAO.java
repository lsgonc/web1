package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Medico

public class MedicoDAO extends MainDAO{

	public void insert(Medico med) {

      String sql = "INSERT INTO medicos(email, senha, CRM, nome, especialidade) VALUES "
                		+ "(?, ?, ?, ?, ?)";

      try {
          Connection conn = this.getConnection();
          PreparedStatement statement = conn.prepareStatement(sql);

          statement.setString(1, med.getEmail());
          statement.setString(2, med.getSenha());
          statement.setString(3, med.getCRM());
          statement.setString(4, med.getNome());
          statement.setString(5, med.getEspecialidade());
          statement.executeUpdate();

          statement.close();
          conn.close();
      } catch (SQLException e) {
          throw new RuntimeException(e);
      }
  }
  
    public List<Medico> getAll() {
      
      List<Medico> listaMedicos = new ArrayList<>();
      
      String sql = "SELECT * FROM medicos ORDER BY especialidade";
      
      try {
        Connection conn = this.getConnection();
        Statement statement = conn.createStatement();
  
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
          String email = resultSet.getString("email");
          String nome = resultSet.getString("nome");
          String crm = resultSet.getString("CRM");
          String especialidade = resultSet.getString("especialidade");
          Medico med = new Medico(email, nome, crm, especialidade);
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


    public void delete(Medico med) {
        String sql = "DELETE FROM medicos WHERE CRM = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, med.getCrm());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public void update(Medico med) {
        String sql = "UPDATE medicos SET nome = ?, especialidade = ? WHERE CRM = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, med.getNome());
            statement.setString(2, med.getEspecialidade());
            statement.setString(3, med.getCrm());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Medico get(Long id) {
        Medico med = null;

        String sql = "SELECT * FROM medicos WHERE CRM = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setString(1, crm);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
              String nome = resultSet.getString("nome");
              String especialidade = resultSet.getString("especialidade");
              Medico med = new Medico(nome, crm, especialidade);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return med;
    }