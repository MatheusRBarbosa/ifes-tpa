/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.taddic.grafo;

import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author barbosa
 */
public class Main {
    public static void main(String[] args) throws IOException{
        System.out.println("===== MAIN =====");
        //String file = "E:\\BACKUP 04 - 10\\Documents\\NetBeansProjects\\TPA\\src\\ifes\\bsi\\tpa\\taddic\\grafo\\Base-Grafos\\tgfmovies10.txt";
        TADGrafoD g = new TADGrafoD("grafinho");
        
        
        g.insertVertex("A", "A");
        g.insertVertex("B", "B");
        g.insertVertex("C", "C");
        g.insertVertex("E", "E");
        g.insertVertex("F", "F");
        g.insertVertex("G", "G");
        
        g.insertEdge("A", "B", "ab", 0, 0);
        g.insertEdge("B", "C", "bc", 4, 4);
        g.insertEdge("B", "E", "be", 1, 1);
        g.insertEdge("C", "G", "cg", 6, 6);
        g.insertEdge("E", "F", "ef", 2, 2);
        g.insertEdge("F", "G", "fg", 1, 1);
        
        g.printGrafoMat();
        ProcessaGrafo pg = new ProcessaGrafo(g);
        System.out.println("===== Dijkstra Caminho =====");
        DSDijkstra dijkstra = pg.cmDijkstra("A");
        int[] custos = dijkstra.getCustos();
        String[] ant = dijkstra.getAntecessores();
        
        for(int i=0;i<ant.length;i++){
            System.out.println(ant[i]);
        }
        
        System.out.println("===== Dijkstra Custos por Edge =====");
        for(int i=0;i<custos.length;i++){
            System.out.println(custos[i]);
        }
       
        System.out.println("===== BF Caminho I/F =====");
        DSDijkstra bf = pg.cmBFord("A");
        custos = bf.getCustos();
        ant = bf.getAntecessores();
        
        for(int i=0;i<ant.length;i++){
            System.out.println(ant[i]);
        }
        
        System.out.println("===== BF Custo total =====");
        for(int i=0;i<custos.length;i++){
            System.out.println(custos[i]);
        }
        
    }
}
