package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author jonas
 */
public class Conexao {

     public Connection getConnection() throws SQLException{
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/atividadeRedis";
            String pass = "#soad1212";
            String user = "postgres";
            try{
               con = DriverManager.getConnection(url,user,pass);
            } catch(SQLException ex){    
                System.out.println("ERRO AO CONECTAR "+ ex.getMessage());
                return null;
            }            
        } catch (ClassNotFoundException ex) {
                System.out.println("ERRO AO CARREGAR O DRIVER "+ ex.getMessage());
                return null;
        }
        return con;
    } 
    
}
