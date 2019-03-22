/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciciostpa.dicionario;

/**
 *
 * @author barbosa
 */
public class Conteudo<T> {
    private String key;
    private T conteudo;
    
    public Conteudo(String key, T conteudo){
        this.conteudo = conteudo;
        this.key = key;
    }

    public T getConteudo() {
        return conteudo;
    }

    public String getKey() {
        return key;
    }

    public void setConteudo(T conteudo) {
        this.conteudo = conteudo;
    }
    
    
    
}
