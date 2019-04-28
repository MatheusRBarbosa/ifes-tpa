/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.dic;

import java.util.LinkedList;

/**
 *
 * @author barbosa
 */
public class TADDicChain<K, V> {

    private LinkedList<TDicItem>[] dicionario;
    private Hash_engine hashEngine;
    private int len = 0; //Quantidade de elementos
    private double fator_de_carga = 0.75;
    
    public TADDicChain() {
        this.hashEngine = new DefaultHashEngine();
        int tam = 100;
        this.dicionario = new LinkedList[tam];
        for (int i = 0; i < tam; i++) {
            this.dicionario[i] = new LinkedList<>();
        }
    }

    public TADDicChain(int tam) {
        this.hashEngine = new DefaultHashEngine();
        
        int len = (int) (tam / this.fator_de_carga);
        this.dicionario = new LinkedList[len];
        for (int i = 0; i < len; i++) {
            this.dicionario[i] = new LinkedList<>();
        }
    }

    public TADDicChain( Hash_engine hashEngine) {
        this.hashEngine = hashEngine;
        int tam = 100;
        int len = (int) (tam / this.fator_de_carga);
        this.dicionario = new LinkedList[len];
        for (int i = 0; i < len; i++) {
            this.dicionario[i] = new LinkedList<>();
        }
    }

    public int size() {
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
        LinkedList<TDicItem>[] newDicionario = new LinkedList[newTam];
        
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

    public void insertItem(K key, V item) {
        int index = getIndex(key);
        this.dicionario[index].add(new TDicItem(key, item));
        this.len++;
        if (this.getMaiorLista() >= (int) (this.dicionario.length * 0.5)) {
            this.redimensiona();
        }
    }

    private TDicItem<K, V> findConteudo(K key) {
        int index = this.getIndex(key);
        if (!this.dicionario[index].isEmpty()) {
            int i = 0;
            while (i < this.dicionario[index].size()) {
                TDicItem c = this.dicionario[index].get(i);
                if (c != null && key.equals(c.getKey())) {
                    return c;
                }
                i++;
            }
        }
        return null;
    }

    public V findElement(K key) {
        return this.findConteudo(key).getConteudo();
    }

    public void removeElement(K key) {
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
