/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.taddic.grafo;

import ifes.bsi.tpa.taddic.TADDicChain;
import java.util.LinkedList;

/**
 *
 * @author barbosa
 */
public class TADGrafo {
    private String label;
    
    private int qntEdges = 0;
    private int qntVertexes = 0;
    private int idEdge = 1;
    private int idVertex = 0;
    private int primVertex = 0;
    private int ultVertice = 0;
    
    private int[][] mat;
    
    private TADDicChain dicEdges;
    private TADDicChain dicVertexes;
    
    private LinkedList<Integer> eliminados;
    
    public TADGrafo(String label){
        this.label = label;
        this.mat = new int[16][16];
        this.dicEdges = new TADDicChain();
        this.dicVertexes = new TADDicChain();
        this.eliminados = new LinkedList<>();
    }
    
    public int numVertex(){
        return this.qntVertexes;
    }
    
    public int numEdge(){
        return this.qntEdges;
    }
    
    public void printGrafo(){
        for(int i = this.primVertex; i < this.ultVertice; i++){
            if(!this.eliminados.contains(i)){
                for(int j = this.primVertex; j < this.ultVertice; j++){
                    System.out.println(mat[i][j]);
                }
                System.out.println();
            }
        }
    }
    
    private Vertex getVertex(String label){
        Vertex vertex = (Vertex)this.dicVertexes.findElement(label);
        if(this.dicVertexes.NO_SUCH_KEY()) return null;
        return vertex;
    }
    
    private int geraIdVertex(){ 
        int id = 0;
        
        if(this.eliminados.isEmpty()) {
            this.idVertex++;
            id = this.idVertex; // Porque so aumenta a quantidade do id quando o lista eh vazia ?
        }
        else {
            id = this.eliminados.get(0);
            this.eliminados.remove();
        }
        
        if(id < this.primVertex)
            this.primVertex = id;
        
        if(id > this.ultVertice)
            this.ultVertice = id;
        
        return id;
    }
    
    public Vertex insertVertex(String label, Object dado){
        Vertex vertex = getVertex(label);
        if(vertex == null){
            int id = geraIdVertex();
            vertex = new Vertex(label, dado);
            vertex.setId(id);
            this.dicVertexes.insertItem(label, vertex);
            this.qntVertexes++;
        }
        else{
            vertex.setDado(dado);
        }
        return vertex;
    }
    
    
}
