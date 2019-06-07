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
        
        grafo.insertEdge("A", "B", "ab", "A");
        grafo.insertEdge("B", "C", "bc", "BA");
        grafo.insertEdge("C", "A", "ca", "CA");
        grafo.insertEdge("D", "A", "dd", "TE");
        
        
        System.out.println("============= Print in matriz =============");
        grafo.printGrafoMat();
        //System.out.println("============= Print in grafo=============");
        //grafo.printGrafo();
    }
}
