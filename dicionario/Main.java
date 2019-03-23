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
public class Main {
    
    public static void main(String[] args) {
        Dicionario dic = new Dicionario(256);
        System.out.println("Hello");
        System.out.println(dic.len());
        dic.insert("123", "asdasdsad");
        System.out.println(dic.len());
        System.out.println(dic.findByKey("123"));
        dic.remove("123");
        System.out.println(dic.len());
                
    }
}
