/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciciostpa.dicionario;

import java.util.Arrays;

class Pessoa {
    private String nome;
    private int idade;
    
    public Pessoa(String nome, int idade){
        this.nome = nome;
        this.idade = idade;
    }
    
    public String getNome(){
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
        Dicionario<Integer, Pessoa> dic = new Dicionario<>(12);
        System.out.println("Hello");
        
        dic.insert(1231, new Pessoa("1", 123123));
        dic.insert(123123, new Pessoa("Dois", 222));
        dic.insert(55555, new Pessoa("tres", 55));
        dic.insert(777, new Pessoa("quatro", 4));
        dic.insert(9999, new Pessoa("cinco", 5));
        dic.insert(1010, new Pessoa("seis", 6));
        dic.insert(1111, new Pessoa("sete", 7));
        dic.insert(5, new Pessoa("oito", 8));
        dic.insert(92928, new Pessoa("nove", 9));
        dic.insert(12341, new Pessoa("dez",10));
        dic.insert(1092832109, new Pessoa("onze",11));
        dic.insert(10923, new Pessoa("doze",12));
        
        System.out.println(dic.findByKey(5).getNome());
        
        System.out.println(Arrays.toString(dic.getColisoes()));   
    }
}
