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
        String file = "E:\\BACKUP 04 - 10\\Documents\\NetBeansProjects\\TPA\\src\\ifes\\bsi\\tpa\\taddic\\grafo\\Base-Grafos\\tgfmovies10.txt";
        TADGrafoD g = new TADGrafoD("grafinho");
        
        g.insertVertex("A", 11);
        g.insertVertex("B", 12);
        g.insertVertex("C", 13);
        
        g.insertEdge("A", "B", "ab", "###");
        g.insertEdge("B", "B", "bb", "!!!");
        g.insertEdge("B", "C", "bc", "%%%");
        
        
        TADGrafoD gclone = g.clone();
        gclone.insertEdge("C", "A", "ac", 123);
     
        if(g.equals(gclone)) System.out.println(" === Igual ===");
        else System.out.println(" === Diferente === ");
    }
}
