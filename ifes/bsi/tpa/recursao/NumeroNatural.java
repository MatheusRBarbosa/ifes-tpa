/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.recursao;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author barbosa
 */
public class NumeroNatural {
    //Exercicio 6
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(naturalRec("123"));
    }
    
    public static boolean naturalRec(String numero){
        if(numero.isEmpty()){
            return true;
        }
        else{
            String n = numero.substring(0,1);
            if(n.hashCode() <= "9".hashCode() && n.hashCode() >= "0".hashCode()){
                System.out.println(numero.substring(1));
                return naturalRec(numero.substring(1));
            }
            else{
                return false;
            }
        }
    }   
}