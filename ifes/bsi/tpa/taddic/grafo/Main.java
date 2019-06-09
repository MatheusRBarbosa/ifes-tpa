/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.taddic.grafo;

/**
 *
 * @author barbosa
 */
public class Main {
    public static void main(String[] args){
        System.out.println("Hello World");
        TADGrafo grafo = new TADGrafo("Grafinho");
        grafo.insertVertex("A", 10);
        grafo.insertVertex("B", 11);
        grafo.insertVertex("C", 12);
        grafo.insertVertex("D", 13);
        
        grafo.insertEdge("A", "B", "ab", 1);
        grafo.insertEdge("B", "C", "bc", 2);
        grafo.insertEdge("C", "A", "ca", 3);
        grafo.insertEdge("D", "A", "dd", 4);
        
        
        System.out.println("============= Print in matriz =============");
        grafo.printGrafoMat();
        //System.out.println("============= Print in grafo=============");
        //grafo.printGrafo();
        System.out.println("============= Print in matriz =============");
        grafo.removeVertex("D");
        grafo.printGrafoMat();
    }
}
