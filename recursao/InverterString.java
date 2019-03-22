/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciciostpa.recursao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author barbosa
 */
public class InverterString {
    //Exercicio 6
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(inverterRec("Matheus"));
    }
    
    public static String inverterRec(String s){
        if(s.isEmpty()){
            return "";
        }
        else{
            String letra = s.substring(s.length()-1);
            s = s.substring(0,s.length()-1);
            return letra.concat(inverterRec(s));
        }
    }
    

    
}
