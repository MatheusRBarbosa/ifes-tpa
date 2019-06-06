/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.recursao;

import java.util.ArrayList;

/**
 *
 * @author barbosa
 */
public class Divisao {
    //Exercicio 3
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(divisaoRec(27, 3));
    }
    
    public static int divisaoRec(int a, int b){
        if(a < b){
            return 0;
        }
        else{
            return  1 + divisaoRec(a-b, b);
        }
    }
    

    
}
