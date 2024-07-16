package br.ufscar.dc.dsw.dao;

//Dependencias para conectar e utilizar o BD
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MainDAO {

    public MainDAO()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException  e) {
            throw new RuntimeException(e);
        }
    }

    protected Connection getConnection() throws SQLException
    {
        //String de conexao do mysql
        String connectionString = "jdbc:mysql://localhost:3306/clinica_medica";
        
        return DriverManager.getConnection(connectionString, "root ", "123123");
        
    }

}
