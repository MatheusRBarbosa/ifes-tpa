/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciciostpa.dicionario;

import java.util.LinkedList;

/**
 *
 * @author barbosa
 */
public class Dicionario<T> {
    private LinkedList<Conteudo>[] dicionario;
    private int len = 0;
    
    public Dicionario(int tam){
        double fc = 0.75;
        int len = (int)(tam/fc);
        this.dicionario = new LinkedList[len];
        for(int i=0;i<len;i++){
            this.dicionario[i] = new LinkedList<>();
        }
    }
    
    public int len(){
        return this.len;
    }
    
    public int getIndex(String key){
        long hash = 0;
        for(int i=0; i<key.length();i++){
            hash += (int)key.charAt(i);
        }
        return (int)(hash % this.dicionario.length);
    }
    
    public void insert(String key, T item){
       int index = getIndex(key);
       this.dicionario[index].add(new Conteudo(key, item));
       this.len++;
    }
    
    private Conteudo<T> findConteudo(String key){
        int index = this.getIndex(key);
        if(!this.dicionario[index].isEmpty()){
            int i=0;
            while(i < this.dicionario[index].size()){
                Conteudo c = this.dicionario[index].get(i);
                if(c != null && key.equals(c.getKey())){
                    return c;
                }
                i++;
            }
        }
        return null;
    }
    
    public T findByKey(String key){
        return this.findConteudo(key).getConteudo();
    }
    
    public void remove(String key){
        int index = this.getIndex(key);
        System.out.println(index);
        if(!this.dicionario[index].isEmpty()){
            this.dicionario[index].remove();
            this.len--;
        }
    }
    
}
