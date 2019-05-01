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
    private Object conteudo;
    
    public TDicItem(Object key, Object conteudo){
        this.conteudo = conteudo;
        this.key = key;
    }

    public Object getConteudo() {
        return conteudo;
    }

    public Object getKey() {
        return key;
    }

    public void setConteudo(Object conteudo) {
        this.conteudo = conteudo;
    }
    
    
    
}
