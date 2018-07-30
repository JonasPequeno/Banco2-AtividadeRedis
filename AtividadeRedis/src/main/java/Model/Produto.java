package Model;

import Dao.DaoProdutos;

/**
 *
 * @author jonas
 */
public class Produto {
    private int codigo;
    private String descricao;
    private float preco;
    
    public Produto () {}

    public Produto(int codigo, String descricao, float preco) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.preco = preco;
    }
    
     public Produto(String descricao, float preço) {
        this.descricao = descricao;
        this.preco = preco;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
    
    
    public void inserir (Produto p) {
        Dao.DaoProdutos dao = new DaoProdutos();
        dao.insert(p);
    }    

    @Override
    public String toString() {
        return "Produto{" + "codigo=" + codigo + ", descricao=" + descricao + ", preco=" + preco + '}';
    }

    
   
}
