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
public class Dicionario<K, V> {

    private LinkedList<Conteudo>[] dicionario;
    private HashEngine hashEngine;
    private int len = 0; //Quantidade de elementos

    public Dicionario() {
        this.hashEngine = new DefaultHashEngine();
        int tam = 200;
        this.dicionario = new LinkedList[tam];
        for (int i = 0; i < tam; i++) {
            this.dicionario[i] = new LinkedList<>();
        }
    }

    public Dicionario(int tam) {
        this.hashEngine = new DefaultHashEngine();
        double fc = 0.75;
        int len = (int) (tam / fc);
        this.dicionario = new LinkedList[len];
        for (int i = 0; i < len; i++) {
            this.dicionario[i] = new LinkedList<>();
        }
    }

    public Dicionario(int tam, HashEngine hashEngine) {
        this.hashEngine = hashEngine;
        double fc = 0.75;
        int len = (int) (tam / fc);
        this.dicionario = new LinkedList[len];
        for (int i = 0; i < len; i++) {
            this.dicionario[i] = new LinkedList<>();
        }
    }

    public int len() {
        return this.len;
    }

    private int getIndex(K key) {
        Long hash = this.hashEngine.generateHash(key);
        return (int) (hash % this.dicionario.length);
    }
    
    private int getIndex(K key, int lenght) {
        Long hash = this.hashEngine.generateHash(key);
        return (int) (hash % lenght);
    }

    private int getMaiorLista() {
        int tam = this.dicionario[0].size();
        for (int i = 1; i < this.dicionario.length; i++) {
            if (tam < this.dicionario[i].size()) {
                tam = this.dicionario[i].size();
            }
        }
        return tam;
    }

    private void redimensiona() {
        
        int newTam = this.dicionario.length * 2;
        LinkedList<Conteudo>[] newDicionario = new LinkedList[newTam];
        
        for(int i = 0; i<newTam; i++){
            newDicionario[i] = new LinkedList<>();
        }
        
        for(int j = 0; j<this.dicionario.length;j++){
            if(this.dicionario[j] != null){
                for(int k = 0; k<this.dicionario[j].size(); k++){
                    K key = (K) this.dicionario[j].get(k).getKey();
                    int index = getIndex(key, newTam);
                    newDicionario[index].add(this.dicionario[j].get(k));
                }
            }
        }
        
        this.dicionario = newDicionario;
    }

    public void insert(K key, V item) {
        int index = getIndex(key);
        this.dicionario[index].add(new Conteudo(key, item));
        this.len++;
        if (this.getMaiorLista() >= (int) (this.dicionario.length * 0.5)) {
            this.redimensiona();
        }
    }

    private Conteudo<K, V> findConteudo(K key) {
        int index = this.getIndex(key);
        if (!this.dicionario[index].isEmpty()) {
            int i = 0;
            while (i < this.dicionario[index].size()) {
                Conteudo c = this.dicionario[index].get(i);
                if (c != null && key.equals(c.getKey())) {
                    return c;
                }
                i++;
            }
        }
        return null;
    }

    public V findByKey(K key) {
        return this.findConteudo(key).getConteudo();
    }

    public void remove(K key) {
        int index = this.getIndex(key);
        System.out.println(index);
        if (!this.dicionario[index].isEmpty()) {
            this.dicionario[index].remove();
            this.len--;
        }
    }

    public int[] getColisoes() {
        int[] colisoes = new int[this.dicionario.length];
        for (int i = 0; i < this.dicionario.length; i++) {
            colisoes[i] = this.dicionario[i].size();
        }
        return colisoes;
    }

}
