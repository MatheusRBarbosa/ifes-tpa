/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.taddic.grafo;

import ifes.bsi.tpa.taddic.TADDicChain;
import java.util.ArrayList;
import java.util.Collections;
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
    
    private LinkedList<Integer> vertexEliminados;
    
    public TADGrafo(String label){
        this.label = label;
        this.mat = new int[16][16];
        this.dicEdges = new TADDicChain();
        this.dicVertexes = new TADDicChain();
        this.vertexEliminados = new LinkedList<>();
    }
    
    public int numVertex(){
        return this.qntVertexes;
    }
    
    public LinkedList<Object> vertices(){
        return this.dicVertexes.elements();
    }
    
    public int numEdge(){
        return this.qntEdges;
    }
    
    public LinkedList<Object> edges(){
        return this.dicEdges.elements();
    }
    
    public void printGrafoMat(){
        for(int i = this.primVertex; i <= this.ultVertice; i++){
            if(!this.vertexEliminados.contains(i)){
                for(int j = this.primVertex; j <= this.ultVertice; j++){
                    System.out.print(mat[i][j]+" | ");
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
        
        if(this.vertexEliminados.isEmpty()) {
            id = this.idVertex++;
        }
        else {
            id = this.vertexEliminados.get(0);
            this.vertexEliminados.remove();
        }
        
        if(id < this.primVertex)
            this.primVertex = id;
        
        else if(id > this.ultVertice)
            this.ultVertice = id;
        
        return id;
    }
    
    public Vertex insertVertex(String label, Object dado){
        Vertex vertex = getVertex(label);
        if(this.dicVertexes.NO_SUCH_KEY()){
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
    
    public Edge insertEdge(String origem, String destino, String label, Object dado){
        Vertex vOrigem = this.getVertex(origem);
        if(this.dicVertexes.NO_SUCH_KEY()) return null;
        
        Vertex vDestino = this.getVertex(destino);
        if(this.dicVertexes.NO_SUCH_KEY()) return null;
        
        Edge edge = (Edge)this.dicEdges.findElement(label);
        if(this.dicEdges.NO_SUCH_KEY()){
            edge = new Edge(label, dado);
            //this.idEdge++;
            edge.setId(this.idEdge++);
            this.dicEdges.insertItem(label, edge);
            mat[vOrigem.getId()][vDestino.getId()] = edge.getId();
            this.qntEdges++;
        }
        else{
            edge.setDado(dado);
        }
        
        return edge;
    }
    
    public Edge getEdge(String origem, String destino){
        Vertex vOrigem = getVertex(origem);
        if(vOrigem == null) return null;
        
        Vertex vDestino = getVertex(destino);
        if(vDestino == null) return null;
        
        int ide = mat[vOrigem.getId()][vDestino.getId()];
        if (ide == 0) return null;
        
        LinkedList<Object> listEdges = this.dicEdges.elements();
        
        for(int i=0;i<listEdges.size();i++){
            Edge e = (Edge)listEdges.get(i);
            if(e.getId() == ide) return e;
        }
        return null;
    }
    
    public Integer degree(String label) {
        Integer in = this.inDegree(label);
        Integer out = this.outDegree(label);
        
        if((in == null) || (out == null)) return null;
        
        return in + out;
    }
    
    public Integer inDegree(String label) {
        Vertex vertex = (Vertex)this.dicVertexes.findElement(label);
        if(this.dicVertexes.NO_SUCH_KEY()) return null;
      
        int idVertex = vertex.getId();
        int grau = 0;

        for(int i = this.primVertex; i <= this.ultVertice; i++) {
            if((mat[i][idVertex] != 0) && !this.vertexEliminados.contains(i)) {
                grau++;
            }
        }
        return grau;
    }
    
    public Integer outDegree(String label){ 
        Vertex vertex = (Vertex)this.dicVertexes.findElement(label);
        if(this.dicVertexes.NO_SUCH_KEY()) return null;
        
        int idVertex = vertex.getId();
        int grau = 0;

        for(int i = this.primVertex; i <= this.ultVertice; i++) {
            if((mat[idVertex][i] != 0) && !this.vertexEliminados.contains(i)) {
                grau++;
            }
        }
        return grau;
    }
    
    public Object removeEdge(String label){
        Edge edge = (Edge)this.dicEdges.findElement(label);
        if(this.dicEdges.NO_SUCH_KEY()) return null;
        
        int idEdge = edge.getId();
        
        for(int i = this.primVertex; i <= this.ultVertice ; i++){
            if(!this.vertexEliminados.contains(i)){
                for(int j = this.primVertex; j <= this.ultVertice; j++){
                    if(this.mat[i][j] == idEdge){
                        this.qntEdges--;
                        this.mat[i][j] = 0;
                        this.dicEdges.removeElement(label);
                        return edge.getDado();
                    }
                }
            }
        }
        return null;
    }
    
    public Object removeVertex(String label){
        Vertex vertex = this.getVertex(label);
        if(vertex == null) return null;
        
        int idVertex = vertex.getId();
        
        if(idVertex == this.primVertex){
            for(int i = this.primVertex+1; i <= this.ultVertice; i++){
                if(!this.vertexEliminados.contains(i)){
                    this.primVertex = i;
                    break;
                }
            }
        }
        
        else if(idVertex == this.ultVertice){
            for(int i = this.ultVertice-1; i > this.primVertex; i--){
                if(!this.vertexEliminados.contains(i)){
                    this.ultVertice = i;
                    break;
                }
            }
        }
        
        for(int i = this.primVertex; i <= this.ultVertice; i++){
            if(this.mat[idVertex][i] != 0){
                this.mat[idVertex][i] = 0;
                this.qntEdges--;
            }
            if(this.mat[i][idVertex] != 0 && this.mat[idVertex][i] != this.mat[i][idVertex]){
                this.mat[i][idVertex] = 0;
                this.qntEdges--;
            }
        }
        
        this.vertexEliminados.add(idVertex);
        this.qntVertexes--;
        return this.dicVertexes.removeElement(label);
    }
    
    private Vertex inToVertex(int id){
        LinkedList<Object> list = this.dicVertexes.elements();
        
        for(int i = 0; i < list.size(); i++){
            Vertex vertex = (Vertex)list.get(i);
            if(vertex.getId() == id) return vertex;
        }
        return null;
    }
    
    public Vertex[] endVertices(String edgeLabel){
        Edge edge = (Edge)this.dicEdges.findElement(edgeLabel);
        if(this.dicEdges.NO_SUCH_KEY()) return null;
        
        int idEdge = edge.getId();
        
        for(int i = this.primVertex; i<=this.ultVertice; i++){
            for(int j = this.primVertex; j<=this.ultVertice; j++){
                if(this.mat[i][j] == idEdge){
                    Vertex[] vertices = new Vertex[2];
                    vertices[0] = this.inToVertex(i);
                    vertices[1] = this.inToVertex(j);
                    return vertices;
                }
            }
        }
        return null;
    }
    
    private Edge inToEdge(int id){
        LinkedList<Object> list = this.dicEdges.elements();
        
        for(int i=0; i < list.size(); i++){
            Edge edge = (Edge)list.get(i);
            if(edge.getId() == id){
                return edge;
            }
        }
        return null;
    }
    
    public LinkedList<Object> outgoingEdges(String labelVertex){
        Vertex vertex = this.getVertex(labelVertex);
        if(this.dicVertexes.NO_SUCH_KEY()) return null;
        
        LinkedList<Object> list = new LinkedList<>();
        for(int i = this.primVertex; i<= this.ultVertice; i++){
            if(this.mat[vertex.getId()][i] != 0){
                list.add(this.inToEdge(this.mat[vertex.getId()][i]));
            }
        }
        return list;
    }
    
    public LinkedList<Object> incomingEdges(String labelVertex){
        Vertex vertex = this.getVertex(labelVertex);
        if(this.dicVertexes.NO_SUCH_KEY()) return null;
        
        LinkedList<Object> list = new LinkedList<>();
        for(int i = this.primVertex; i <= this.ultVertice; i++){
            if(this.mat[i][vertex.getId()] != 0){
                list.add(this.inToEdge(this.mat[i][vertex.getId()]));
            }
        }
        return list;
    }
}
