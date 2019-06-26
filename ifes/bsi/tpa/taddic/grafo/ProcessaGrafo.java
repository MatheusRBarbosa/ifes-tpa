/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.taddic.grafo;

import java.util.LinkedList;

/**
 *
 * @author User
 */
public class ProcessaGrafo {
    private TADGrafoD grafo;
    
    public ProcessaGrafo(TADGrafoD g){
        this.grafo = g;
    }
    
    public LinkedList<Vertex> bfs(String labelVertex){
        Vertex vertex = grafo.getVertex(labelVertex);
        if(vertex == null) return null;
        
        LinkedList<Vertex> saida = new LinkedList<>();
        LinkedList<Vertex> pilha = new LinkedList<>();
        LinkedList<Vertex> vizinhos = null;
        
        pilha.add(vertex);

        while(!pilha.isEmpty()){ 
            vertex = pilha.remove();
            vizinhos = grafo.adjacentVertices(vertex.getLabel());
            
            if(!saida.contains(vertex))
                saida.add(vertex);
            
            if (!vizinhos.isEmpty()){ 
                for (int i=0; i<vizinhos.size();i++){
                    Vertex v = vizinhos.get(i);
                    if(!saida.contains(v))  
                        pilha.add(v); 
                }
            }
        }
        
        return saida;
    }
    
    public LinkedList<Vertex> dfs(String labelVertex){
        
        Vertex vertex = grafo.getVertex(labelVertex);
        if(vertex == null) return null;
        
        LinkedList<Vertex> saida = new LinkedList<>();
        LinkedList<Vertex> pilha = new LinkedList<>();
        LinkedList<Vertex> vizinhos = null;
        
        pilha.add(vertex);

        while(!pilha.isEmpty()){ 
            vertex = pilha.pop();
            vizinhos = grafo.adjacentVertices(vertex.getLabel());
            
            if(!saida.contains(vertex))
                saida.add(vertex);
            
            if (!vizinhos.isEmpty()){ 
                for (int i=0; i<vizinhos.size();i++){
                    Vertex v = vizinhos.get(i);
                    if(!saida.contains(v))  
                        pilha.addFirst(v); 
                }
            }
        }
        
        return saida;
    }
    
    
    public DSDijkstra cmBFord(String origem){
        return null;
    }
    
    public DSDijkstra cmDijkstra(String origem){
        return null;
    }
    
    public DSFloydW cmFWarshall(){
        return null;
    }
}
