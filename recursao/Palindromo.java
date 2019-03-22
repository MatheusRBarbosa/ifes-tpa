/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciciostpa.recursao;

import static exerciciostpa.recursao.CalcularMenor.calcMenorRec;
import static exerciciostpa.recursao.CalcularMenor.gerarLista;
import java.util.ArrayList;

/**
 *
 * @author barbosa
 */
public class Palindromo {
    public static void main(String[] args) {
        System.out.println(palindromoRec("ovo"));        
    }
    
    public static boolean palindromoRec(String s){
        if(s.isEmpty() || s.length() == 1){
            return true;
        }
        else{
            String l1 = s.substring(0,1);
            String l2 = s.substring(s.length()-1);
            if(l1.equals(l2)){
                return palindromoRec(s.substring(1, s.length()-1));
            }
            return false;
        }
    }
}
