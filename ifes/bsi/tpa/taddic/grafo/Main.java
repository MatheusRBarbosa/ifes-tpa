/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.taddic.grafo;

import java.util.LinkedList;

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
        grafo.insertEdge("D", "B", "ad", 5);
        
        
        System.out.println("============= Print in matriz =============");
        grafo.printGrafoMat();
        
        System.out.println("============= Print elements =============");
        LinkedList<Object> list = grafo.incomingEdges("B");
        for(int i=0;i<list.size();i++){
            Edge e = (Edge)list.get(i);
            System.out.println(e.getLabel());
        }
    }
}
