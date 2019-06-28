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
    
    private int menor(int[] pesos, LinkedList<Integer> vizinhos) {
        int menor = Integer.MAX_VALUE;
        int id = 0;
        if(vizinhos.size() == 1) return vizinhos.get(0);
        for(int i = 0; i < pesos.length; i++) {
            if( pesos[i] < menor && vizinhos.contains(i)) {
                menor = pesos[i];
                id = i;
            }
        }
        return id;
    }
    
    public DSDijkstra cmDijkstra(String origem){
        
        Vertex vertex = this.grafo.getVertex(origem);
        if(vertex == null) return null;
        
        LinkedList<Vertex> vertices = this.grafo.vertices();
        
        int tam = vertices.size();
        int[] pesos = new int[tam];
        String[] caminho = new String[tam];
        
        LinkedList<Integer> vizinhos = new LinkedList<>();
        for (int i = 0; i < tam; i++) {
            pesos[i] = Integer.MAX_VALUE;
            vizinhos.add(i);
        }
        
        pesos[vertex.getId()] = 0;
        int vId = vertex.getId();
        caminho[vId] = vertex.getLabel();

        while(!vizinhos.isEmpty()) {					
            vizinhos.remove(new Integer(vId));
            int[] resp = pesos.clone();
            String[] atualLocal = caminho.clone();
            
            LinkedList<Vertex> adjacents = this.grafo.adjacentVertices(vertices.get(vId).getLabel());					
            
            for (int i=0;i<adjacents.size();i++) {
                Vertex v = adjacents.get(i);
                Edge edge = this.grafo.getEdge(vertices.get(vId).getLabel(), v.getLabel());
                
                if(edge != null && vizinhos.contains(v.getId())) {				
                    int novoPeso = edge.getPeso()+ pesos[vId];					
                    int pesoAtual = pesos[(v.getId())];				
                    if(pesoAtual > novoPeso) {
                        resp[v.getId()] = novoPeso;
                        atualLocal[v.getId()] = caminho[vId] + '-' + v.getLabel();					
                    }
                }
            }
            
            caminho = atualLocal;
            //pesos = resp;
            if(!vizinhos.isEmpty()) vId = this.menor(resp,vizinhos);
        }
        return this.createDSDijkstra(caminho[caminho.length-1]);
        
    }
    
    public DSDijkstra createDSDijkstra (String antecessores){
        String ant[] = antecessores.split("-");
        int custos[] = new int[ant.length-1];
        for(int i=0;i<ant.length-1;i++){
            custos[i] = this.grafo.getEdge(ant[i], ant[i+1]).getPeso();
        }
        return new DSDijkstra(custos,ant);
    }
    
    public DSDijkstra cmBFord(String origem){
        return null;
    }
    
    public DSFloydW cmFWarshall(){
        return null;
    }
}
