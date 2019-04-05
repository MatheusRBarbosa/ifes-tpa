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
public class Conteudo<K,V> {
    private K key;
    private V conteudo;
    
    public Conteudo(K key, V conteudo){
        this.conteudo = conteudo;
        this.key = key;
    }

    public V getConteudo() {
        return conteudo;
    }

    public K getKey() {
        return key;
    }

    public void setConteudo(V conteudo) {
        this.conteudo = conteudo;
    }
    
    
    
}
