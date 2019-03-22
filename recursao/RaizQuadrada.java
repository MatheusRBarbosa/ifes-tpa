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
public class RaizQuadrada {
    //Exercicio 4
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(raizQuadradaRec(4));
    }
    
    public static double raizQuadradaRec(double x){
        return raizQuadradaRec(0.0,x,x);
    }
    public static double raizQuadradaRec(double a, double b, double x){
        double y = (a+b)/2.0;
        if (Math.abs(y*y-x)<= 1.0e-15 || Math.abs(a-b) <= 1.0e-15)
            return y;
        else if (y*y > x)
            return raizQuadradaRec(a,y,x);
        else return raizQuadradaRec(y,b,x);
    }
    

    
}
