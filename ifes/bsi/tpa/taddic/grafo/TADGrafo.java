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
    
    public void printGrafoMat(){
        for(int i = this.primVertex; i <= this.ultVertice; i++){
            if(!this.eliminados.contains(i)){
                for(int j = this.primVertex; j < this.ultVertice; j++){
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
        
        if(this.eliminados.isEmpty()) {
            //this.idVertex++;
            id = this.idVertex++;
        }
        else {
            id = this.eliminados.get(0);
            this.eliminados.remove();
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
            this.dicEdges.insertItem(label, dado);
            mat[vOrigem.getId()][vDestino.getId()] = edge.getId();
            this.qntEdges++;
        }
        else{
            edge.setDado(dado);
        }
        
        return edge;
    }
    
    public void printGrafo() {
        ArrayList<String> al = new ArrayList<String>();
        String s, labelOrigem = "", labelDestino = "", labelEdge = "";
        
        Vertex v;
        Edge e;
        
        LinkedList<Object> lstVs = this.dicVertexes.keys();
        LinkedList<Object> lstEs = this.dicEdges.keys();
        
        for(int i = this.primVertex; i <= this.ultVertice; i++) {
            s = "";
            
            if(!this.eliminados.contains(i)) {
                for(int j = 0; j < lstVs.size(); j++) {
                    v = (Vertex)this.dicVertexes.findElement(lstVs.get(j));
                    if(v.getId() == i) {
                        labelOrigem = v.getLabel();
                        break;
                    }
                }
                
                for(int k = this.primVertex; k <= this.ultVertice; k++) {
                    if(!this.eliminados.contains(k)) {
                        for(int m = 0; m < lstVs.size(); m++) {
                            v = (Vertex)this.dicVertexes.findElement(lstVs.get(m));
                            if(v.getId() == k) {
                                labelDestino = v.getLabel();
                                break;
                            }
                        }
                        
                        int idEdge = mat[i][k];
                        
                        if(idEdge != 0) {
                            for(int m = 0; m < lstEs.size(); m++) {
                                e = (Edge)this.dicEdges.findElement(lstEs.get(m));
                                if(e.getId() == idEdge) {
                                    labelEdge = e.getLabel();
                                    break;
                                }
                            }
                            
                            s = labelOrigem + "--" + labelEdge + "-->" + labelDestino;
                            al.add(s);
                        }
                    }
                }
            }
        } //for int i...
        
        //Island vertex treatment
        for(int i = 0; i < lstVs.size(); i++) {
            String lbl = (String)lstVs.get(i);
            if(this.degree(lbl) == 0) {
                al.add(lbl);
            }
        }
        
        Collections.sort(al);
        
        for(int n = 0; n < al.size(); n ++) {
            System.out.println(al.get(n));
        }
    }
    
    public Integer degree(String label) { //REFAZER
        Integer in = this.inDegree(label);
        Integer out = this.outDegree(label);
        
        if((in == null) || (out == null)) {
            return null;
        }
        else {
            return in + out;
        }
    }
    
    public Integer inDegree(String label) { // Refazer
        Vertex v = (Vertex)this.dicVertexes.findElement(label);
        if(this.dicVertexes.NO_SUCH_KEY()) {
            return null;
        }
        else {
            int line = v.getId();
            int grade = 0;
            
            for(int i = this.primVertex; i <= this.ultVertice; i++) {
                if((mat[i][line] != 0) && !this.eliminados.contains(i)) {
                    grade++;
                }
            }
            
            return grade;
        }
    }
    
    public Integer outDegree(String label){ // REFAZER
        Vertex v = (Vertex)this.dicVertexes.findElement(label);
        if(this.dicVertexes.NO_SUCH_KEY()) {
            return null;
        }
        else {
            int line = v.getId();
            int grade = 0;
            
            for(int i = this.primVertex; i <= this.ultVertice; i++) {
                if((mat[line][i] != 0) && !this.eliminados.contains(i)) {
                    grade++;
                }
            }
            
            return grade;
        }
    }
    
}
