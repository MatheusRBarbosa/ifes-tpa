/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.grafo;

/**
 *
 * @author Matheus Barbosa
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("OLA MUNDO");
        
        Grafo g = new Grafo("Grapheno");
        
        g.insertVertex("A");
        g.insertVertex("B");
        g.insertVertex("C");
        g.insertVertex("D");
        
        
        g.insertEdge("A", "B", "123");
        
        
        System.out.println("=========== ARESTAS ============");
        g.printArestas();
        System.out.println("=========== VERTICES ============");
        g.printVertex();
        
        /*RESOLVER PROBLEMA DO EQUALS, ELE TA PEGANDO O MESMO ENDERECO PARA TODOS
        OS INSERTS. ISSO NAO PODE ACONTECER POIS O MESMO VERTICE NAO PODE SER CRIADO
        DEPOIS DISSO, DEVE-SE ARRUMAR O INSERTEDGES*/
        
    }
    
}
