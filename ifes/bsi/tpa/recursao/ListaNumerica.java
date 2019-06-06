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
public class ListaNumerica {
    //Exercicio 1
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Integer> lista = gerarLista();
        System.out.println(lista);
        System.out.println(somaRec(lista));
    }
    
    public static ArrayList<Integer> gerarLista(){
        ArrayList<Integer> lista = new ArrayList();
        for(int i=1; i<10; i++){
            lista.add(i);
        }
        return lista;
    }
    
    public static int somaRec(List<Integer> lista){
        if(lista.isEmpty()){
            return 0;
        }
        else{
            return lista.get(lista.size()-1) + somaRec(lista.subList(0, lista.size()-1));
        }
    }
    
}
