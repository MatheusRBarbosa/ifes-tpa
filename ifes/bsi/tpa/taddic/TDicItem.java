/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.dic;

/**
 *
 * @author barbosa
 */
public class TDicItem {
    private Object key;
    private Object dado;
    private Long cach_hash;
    
    public TDicItem(Object key, Object dado){
        this.dado = dado;
        this.key = key;
    }

    public Object getDado() {
        return dado;
    }

    public Object getKey() {
        return key;
    }

     public Long getCash_Hash(){
        return this.cach_hash;
    }
        
    public void setConteudo(Object dado) {
        this.dado = dado;
    }
    
    public void setCash_Hash(Long cach_hash){
        this.cach_hash = cach_hash;
    }
    
   
    
    
}
