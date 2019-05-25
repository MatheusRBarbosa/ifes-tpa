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
public class TADDicChain {

    private LinkedList<TDicItem>[] dicionario;
    private Hash_engine hashEngine;
    private int size = 0; //Quantidade de elementos inseridos
    private int sizeVetBuckets;
    private double fator_de_carga = 0.75;
    private boolean finded = false;

    public TADDicChain() {
        this.hashEngine = new DefaultHashEngine();
        this.sizeVetBuckets = 1024;
        this.dicionario = new LinkedList[this.sizeVetBuckets];
        for (int i = 0; i < this.sizeVetBuckets; i++) {
            this.dicionario[i] = new LinkedList<>();
        }
    }

    public TADDicChain(int tam) {
        this.hashEngine = new DefaultHashEngine();

        this.sizeVetBuckets = (int) (tam / this.fator_de_carga);
        this.dicionario = new LinkedList[this.sizeVetBuckets];
        for (int i = 0; i < this.sizeVetBuckets; i++) {
            this.dicionario[i] = new LinkedList<>();
        }
    }

    public TADDicChain(Hash_engine hashEngine) {
        this.hashEngine = hashEngine;
        this.sizeVetBuckets = 1024;
        int size = (int) (this.sizeVetBuckets / this.fator_de_carga);
        this.dicionario = new LinkedList[size];
        for (int i = 0; i < size; i++) {
            this.dicionario[i] = new LinkedList<>();
        }
    }
    
    public TADDicChain(int tam, Hash_engine hashEngine){
        this.hashEngine = hashEngine;
        this.sizeVetBuckets = (int) (tam / this.fator_de_carga);
        this.dicionario = new LinkedList[this.sizeVetBuckets];
        for (int i = 0; i < this.sizeVetBuckets; i++) {
            this.dicionario[i] = new LinkedList<>();
        }
    }

    public int size() {
        return this.size;
    }

    public int getSizeVetBuckets() {
        return this.sizeVetBuckets;
    }
    
    public boolean isEmpty(){
        return this.size == 0;
    }
    
    public boolean NO_SUCH_KEY(){
        return !this.finded;
    }

    public LinkedList<Object> keys() {
        LinkedList<Object> list = new LinkedList();
        for (int i = 0; i < this.dicionario.length; i++) {
            for (int j = 0; j < this.dicionario[i].size(); j++) {
                list.add(this.dicionario[i].get(j).getKey());
            }
        }
        return list;
    }
    
    public LinkedList<Object> elements() {
        LinkedList<Object> list = new LinkedList();
        for (int i = 0; i < this.dicionario.length; i++) {
            for (int j = 0; j < this.dicionario[i].size(); j++) {
                list.add(this.dicionario[i].get(j).getDado());
            }
        }
        return list;
    }
    
    public LinkedList<Object> getItens() {
        LinkedList<Object> list = new LinkedList();
        for (int i = 0; i < this.dicionario.length; i++) {
            for (int j = 0; j < this.dicionario[i].size(); j++) {
                list.add((TDicItem)this.dicionario[i].get(j));
            }
        }
        return list;
    }

    private int getIndex(Object key) {
        Long hash = this.hashEngine.hash_func(key);
        return (int) (hash % this.dicionario.length);
    }

    private int getIndex(Object key, int lenght) {
        Long hash = this.hashEngine.hash_func(key);
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

        int newTam = (int)((int)this.dicionario.length * 1.5);
        LinkedList<TDicItem>[] newDicionario = new LinkedList[newTam];

        for (int i = 0; i < newTam; i++) {
            newDicionario[i] = new LinkedList<>();
        }

        for (int j = 0; j < this.dicionario.length; j++) {
            if (this.dicionario[j] != null) {
                for (int k = 0; k < this.dicionario[j].size(); k++) {
                    Object key = this.dicionario[j].get(k).getKey();
                    int index = getIndex(key, newTam);
                    newDicionario[index].add(this.dicionario[j].get(k));
                }
            }
        }

        this.dicionario = newDicionario;
    }

    public void insertItem(Object key, Object item) {
        if(key == null){
            return;
        }
        TDicItem itemFinded = this.findConteudo(key);
        if(finded){
            itemFinded.setConteudo(item);
        }
        else{
            int index = getIndex(key);
            this.dicionario[index].add(new TDicItem(key, item));
            this.size++;
            if (this.getMaiorLista() >= (int) (this.dicionario.length * 0.3)) {
                this.redimensiona();
            }
        }
        
    }

    private TDicItem findConteudo(Object key) {
        int index = this.getIndex(key);
        if (!this.dicionario[index].isEmpty()) {
            int i = 0;
            while (i < this.dicionario[index].size()) {
                TDicItem c = this.dicionario[index].get(i);
                if (c != null && key.equals(c.getKey())) {
                    this.finded = true;
                    return c;
                }
                i++;
            }
        }
        this.finded = false;
        return null;
    }

    public Object findElement(Object key) {
        TDicItem item = this.findConteudo(key);
        if(finded)
            return item.getDado();
        else
            return null;
    }

    public Object removeElement(Object key) {
        int index = this.getIndex(key);
        Object item = findElement(key);
        if(item != null){
            this.finded = true;
            this.dicionario[index].remove(item);
            this.size--;
            return item;
        }
        else{
            this.finded = false;
            return null;
        }
        
    }

    public int[] getColisoes() {
        int[] colisoes = new int[this.dicionario.length];
        for (int i = 0; i < this.dicionario.length; i++) {
            colisoes[i] = this.dicionario[i].size();
        }
        return colisoes;
    }
    
    public TADDicChain clone(){
        TADDicChain newDic = new TADDicChain(this.sizeVetBuckets,this.hashEngine);
        TDicItem item;
        LinkedList<Object> keys = this.keys();
        for(int i=0; i<keys.size(); i++){
            Object key = keys.get(i);
            item = this.findConteudo(key);
            newDic.insertItem(item.getKey(), item.getDado());
        }
        return newDic;
    }
    
    public boolean equals(TADDicChain dic){
        if(this.size() != dic.size())
            return false;
        
        LinkedList<Object> keys = this.keys();
        for(int i=0; i<keys.size(); i++){
            Object key = keys.get(i);
            Object value = dic.findElement(key);
            if (dic.NO_SUCH_KEY())
                return false;
            if (value != this.findElement(key)) 
                return false;
        }
        return true;
    }
}
