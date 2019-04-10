/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciciostpa.dicionario;

import java.util.Arrays;

class Pessoa {
    private int nome;
    private int idade;
    
    public Pessoa(int nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }
    
    public int getNome(){
        return this.nome;
    }
    
    public int getIdade(){
        return this.idade;
    }
    
}
/**
 *
 * @author barbosa
 */
public class Main {
    
    public static void main(String[] args) {
        Dicionario<Integer, Pessoa> dic = new Dicionario<>(4);
        System.out.println("Hello");
        
        for(int i=1;i<100;i++){
            dic.insert(i, new Pessoa(i,i));
        }
        //System.out.println(dic.findByKey(5).getNome());
        
        System.out.println(Arrays.toString(dic.getColisoes()));
    }
}
