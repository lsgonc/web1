package br.ufscar.dc.dsw.DAO;

//Dependencias par aconectar e utilizar o BD
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

    protected Connection getConection() throws SQLException
    {
        //String de conexao do mysql
        String connectionString = "jdbc:mysql://localhost:3306/clinica_medica";
        
        return DriverManager.getConnection(connectionString, "root ", "123123");
        
    }

}
