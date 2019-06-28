/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.taddic.grafo;

import java.util.Arrays;
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
            if(!vizinhos.isEmpty()) vId = this.menor(resp,vizinhos);
        }
        return this.createDSDijkstra(caminho[caminho.length-1], null);
        
    }
    
    public DSDijkstra createDSDijkstra (String antecessores, int pesos[]){
        String ant[] = antecessores.split("-");
        int custos[] = new int[ant.length-1];
        
        if(pesos == null){
            for(int i=0;i<ant.length-1;i++){
                custos[i] = this.grafo.getEdge(ant[i], ant[i+1]).getPeso();
            }
            return new DSDijkstra(custos,ant);
        }
        else{
            int i[] = new int[1];
            i[0] = pesos[pesos.length-1];
            return new DSDijkstra(i,ant);
        }
        
        
    }
    
    public DSDijkstra cmBFord(String origem){
        Vertex vertex = this.grafo.getVertex(origem);
        if(vertex == null) return null;
        
        LinkedList<Vertex> vertices = this.grafo.vertices();
        
        int tam = vertices.size();
        int[] pesos = new int[tam];
        
        String[] caminho = new String[tam];
        
        for(int i = 0; i < pesos.length; i++) {
            pesos[i] = Integer.MAX_VALUE;
        }
        
        int vId = vertex.getId();
        caminho[vId] = vertex.getLabel();
        pesos[vId] = 0;
        
        for(int i = 0; i < tam-1; i++) {
            String[] atualLocal = caminho.clone();
            int[] pesoAtual = pesos.clone();
            for(int j = 0; j < pesoAtual.length; j++) {
                if(pesoAtual[j] != Integer.MAX_VALUE) {
                    Vertex v = vertices.get(j);
                    int posVertex = v.getId();
                    
                    LinkedList<Vertex> adjacents = this.grafo.adjacentVertices(v.getLabel());
                    for(int k=0;k<adjacents.size();k++) {
                        Vertex vTemp = adjacents.get(k);
                        Edge edge = this.grafo.getEdge(v.getLabel(), vTemp.getLabel());
                        if (edge != null) {
                            int pesoTotal = pesoAtual[posVertex] + edge.getPeso();
                            if(pesoAtual[(vTemp.getId())] > pesoTotal){
                                pesoAtual[vTemp.getId()] = pesoTotal;
                                atualLocal[vTemp.getId()] = caminho[vId] + '-' + vTemp.getLabel();
                            }
                        }
                    }
                }
            }
            if(Arrays.equals(pesoAtual, pesos)) {
                pesos = pesoAtual;
                caminho = atualLocal;
                break;
            }
            else {
                pesos = pesoAtual;
                caminho = atualLocal;
            }
        }
        for(int i=0;i<caminho.length;i++) System.out.println(caminho[i]);
        return this.createDSDijkstra(caminho[caminho.length-1], pesos);
    }
    
    
    public DSFloydW cmFWarshall(){ //incompleto
        int numVertices = this.grafo.numVertices();
        double [][] dist = new double [numVertices][numVertices];
        for (double[] row : dist)
            Arrays.fill(row, Double.POSITIVE_INFINITY);
        
        int linha = 0;
        LinkedList<Vertex> oVertices = this.grafo.vertices();
        
        for(int i=0;i<oVertices.size();i++) {
            Vertex vOrigem = oVertices.get(i);
            int coluna = 0;
            
            LinkedList<Vertex> dVertices = this.grafo.vertices();
            for(int j=0;j<dVertices.size();j++) {
                Vertex vDestino = dVertices.get(j);
                
                if(vOrigem.getId() != vDestino.getId()) {
                    Edge edge = this.grafo.getEdge(vOrigem.getLabel(), vDestino.getLabel());
                    if( edge != null) {	
                        dist[linha][coluna] = edge.getPeso();	
                    }
                    else {
                        dist[linha][coluna] = Integer.MAX_VALUE;
                    }		
                }
                else {
                    dist[linha][coluna] =  0;
                }	
        	coluna++;
            }
            linha++;
        }
        
        int[][] next = new int[numVertices][numVertices];
        for (int i = 0; i < next.length; i++) {
            for (int j = 0; j < next.length; j++)
                if (i != j)
                    next[i][j] = j + 1;
        }
 
        for (int k = 0; k < numVertices; k++)
            for (int i = 0; i < numVertices; i++)
                for (int j = 0; j < numVertices; j++)
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        next[i][j] = next[i][k];
                    }
 
        //printResult(dist, next);
        return null;
    }
    
}
