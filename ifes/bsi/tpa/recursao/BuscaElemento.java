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
public class BuscaElemento {
    //Exercicio 5
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<Integer> lista = gerarLista();
        System.out.println(lista);
        System.out.println(buscaElementoRec(3, lista));
    }
    
    public static ArrayList<Integer> gerarLista(){
        ArrayList<Integer> lista = new ArrayList();
        for(int i=1; i<11; i++){
            lista.add(i);
        }
        return lista;
    }
    
    public static boolean buscaElementoRec(int elemento, List<Integer> lista){
        if(lista.isEmpty()){
            return false;
        }
        else{
            if(lista.get(lista.size()-1) == elemento){
                return true;
            }
            return  buscaElementoRec(elemento, lista.subList(0, lista.size()-1));
        }
    }
    

    
}
