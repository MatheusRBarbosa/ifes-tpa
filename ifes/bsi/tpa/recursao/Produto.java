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
public class Produto {
    //Exercicio 2
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(produtoRec(9, 3));
    }
    
    public static int produtoRec(int a, int b){
        if(b == 0){
            return 0;
        }
        else{
            return a + produtoRec(a, b-1);
        }
    }
    

    
}
