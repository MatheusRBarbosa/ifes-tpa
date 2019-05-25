/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.dic.aplicacao.matriz;

/**
 *
 * @author User
 */
public class main {
    public static void main(String[] args) {
        System.out.println("Ola mundo");
        
        TADMatriz matriz = new TADMatriz(2, 2);
        matriz.setElem(1, 1, 5f);
        matriz.setElem(1, 2, 4f);
        matriz.setElem(2, 1, 0f);
        matriz.setElem(2, 2, 2f);
        
        TADMatriz matriz2 = new TADMatriz(2, 2);
        matriz2.setElem(1, 1, 0f);
        matriz2.setElem(1, 2, -2f);
        matriz2.setElem(2, 1, 5f);
        matriz2.setElem(2, 2, -3f);
        
        TADMatriz matriz3 = matriz.soma(matriz2);
        
        matriz.imprimeMatriz();
        System.out.println("======================");
        matriz2.imprimeMatriz();
        System.out.println("======================");
        matriz3.imprimeMatriz();
        System.out.println("======================");
        
    }
}

