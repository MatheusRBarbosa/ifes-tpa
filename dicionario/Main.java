/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciciostpa.dicionario;

import java.util.Arrays;

/**
 *
 * @author barbosa
 */
public class Main {
    
    public static void main(String[] args) {
        Dicionario dic = new Dicionario(12);
        System.out.println("Hello");
        
        dic.insert("1", "Um");
        dic.insert("2", "Dois");
        dic.insert("3", "tres");
        dic.insert("4", "quatro");
        dic.insert("5", "cinco");
        dic.insert("6", "seis");
        dic.insert("7", "sete");
        dic.insert("8", "oito");
        dic.insert("9", "nove");
        dic.insert("10", "dez");
        dic.insert("11", "onze");
        dic.insert("12", "doze");
        
        System.out.println(dic.findByKey("5"));
        
        System.out.println(Arrays.toString(dic.getColisoes()));
                
    }
}
