package Dao;

import Model.Produto;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoProdutos {

    public DaoProdutos() {
    }
    
    public boolean insert (Produto p) {
        
        Connection con;
        try {
            con = new Conexao().getConnection();
            String sql = "INSERT INTO PRODUTOS(descricao, preco) VALUES (?, ?)";
            
            PreparedStatement state = con.prepareStatement(sql);
            state.setString(1, p.getDescricao());
            state.setFloat(2, p.getPreco());
            
            state.execute();
            
            return true;
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir produto : " + ex.getMessage());
        }               
        return false;
        
    }
    
    public List<Produto> getProdutos () {
        try {
            List<Produto> produtos = new ArrayList<>();
            Connection con = new Conexao().getConnection();
            String sql = "SELECT * FROM PRODUTOS";
            Statement state = con.createStatement(
                    ResultSet.CONCUR_UPDATABLE,
                    ResultSet.HOLD_CURSORS_OVER_COMMIT,
                    ResultSet.TYPE_SCROLL_SENSITIVE
            );
            
            ResultSet result = state.executeQuery(sql);
            
            while(result.next()) {
                Produto p = new Produto();
                p.setCodigo(result.getInt("id"));
                p.setDescricao(result.getString("descricao"));
                p.setPreco(result.getFloat("preco"));            
                produtos.add(p);
            }        
            return produtos;
        } catch (SQLException ex) {
            return null;
        }       
        
    }
    
   
}
