/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.grafo;

import java.util.ArrayList;

/**
 *
 * @author barbosa
 */
public class Grafo {
    
    private String nome;
    private int numVertices;
    private ArrayList<Vertice> vertices;
    private ArrayList<Aresta> arestas;
    
    public Grafo(String nome){
        this.nome = nome;
        this.numVertices = 0;
        vertices =  new ArrayList<>();
        arestas =  new ArrayList<>();
    }
    
    
    public int numVertices(){
        return this.numVertices;
    }
    
    public Vertice insertVertex(Object e){
        Vertice v = null;
        if(this.numVertices == 0){
            v = new Vertice(e);
            vertices.add(v);
            numVertices++;
        }
        else{
            for(int i=0;i<vertices.size();i++){
                if(vertices.get(i).equals(e)){
                    v = new Vertice(e);
                    vertices.add(v);
                    numVertices++;
                }
                else{
                    System.out.println(e+" Já existe ! tente outro objeto = "+e.toString());
                }
            }
        }
        return v;
    }
    
    public Aresta insertEdge(Object origem, Object destino, Object elemento){
        Vertice o = null, d = null;
        for(int i=0;i<vertices.size();i++){
            if(vertices.get(i).equals(origem))
                o = vertices.get(i);
            if(vertices.get(i).equals(destino))
                d = vertices.get(i);
        }
        //System.out.println(o);
        //System.out.println(d);
        /*
        Aresta a = new Aresta(o, d, elemento);
        arestas.add(a);
        return a;
        */
        return null;
    }
    
    public void printVertex(){
        System.out.println(this.vertices);
    }
    
    public void printArestas(){
        System.out.println(this.arestas);
    }
    
  
    
}
