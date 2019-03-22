/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciciostpa.recursao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author barbosa
 */
public class CalcularMaior {
    //Exercicio 5
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Integer> lista = gerarLista();
        System.out.println(lista);
        System.out.println(calcMaiorRec(lista));
        
    }
    
    public static ArrayList<Integer> gerarLista(){
        Random rand = new Random();
        ArrayList<Integer> lista = new ArrayList();
        for(int i=1; i<11; i++){
            lista.add(rand.nextInt(100));
        }
        return lista;
    }
    
    public static int calcMaiorRec(ArrayList<Integer> lista){
        if(lista.size() == 1){
            return lista.get(0);
        }
        else{
            int a = lista.get(lista.size()-1);
            int b = lista.get(lista.size()-2);
            if(a > b){
                lista.set(lista.size()-2,a);
            }
            lista.remove(lista.size()-1);
            return calcMaiorRec(lista);
        }
    }
    
}
