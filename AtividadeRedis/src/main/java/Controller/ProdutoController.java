package Controller;

import Dao.DaoProdutos;
import Model.Produto;
import com.google.gson.Gson;
import java.util.List;
import redis.clients.jedis.Jedis;


/**
 *
 * @author jonas
 */
public class ProdutoController {
    
    Jedis jedis = new Jedis("localhost", 6379);
    Gson gson = new Gson();
    DaoProdutos dao = new DaoProdutos();

    public ProdutoController() {
    }
    
    
    public void criaProduto (String descricao, float preco) {
       Produto p = new Produto();       
       p.setDescricao(descricao);
       p.setPreco(preco);
       dao.insert(p);
       
    }
    
    public String getProduto ( int id ) {   
        
        if(getCache(id) != null ){
            return getCache(id).getDescricao();
        }
        else {
            for(Produto p : dao.getProdutos()) {
                if(p.getCodigo() == id) {
                    guardaNoCache(p);
                    return p.getDescricao();
                }
            }
            return null;
        }
    }
        
    
    public void guardaNoCache(Produto p) {
        String key = p.getCodigo()+"";
        String produto = gson.toJson(p);
        System.out.println("Guardado no cache" + produto); 
        jedis.set(key,produto);
    }
    
    
    public Produto getCache (int id) {
       String key = id+"";
        System.out.println("chave " + key);
       String produto = jedis.get(key);
       System.out.println("Produto no getCache " + produto);
       Produto p = gson.fromJson(produto, Produto.class);
        
       if(p != null ){
          return p;
       }
       else return null;
    }
   
}
