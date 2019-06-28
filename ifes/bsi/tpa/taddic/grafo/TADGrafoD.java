/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.taddic.grafo;

import ifes.bsi.tpa.taddic.TADDicChain;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author barbosa
 */
public class TADGrafoD {
    private String label;
    
    private int qntEdges = 0;
    private int qntVertexes = 0;
    private int idEdge = 1;
    private int idVertex = 0;
    private int primVertice = 0;
    private int ultiVertice = 0;
    
    private int[][] mat;
    
    private TADDicChain dicLblEdge;
    private TADDicChain dicLblVertex;
    
    private LinkedList<Integer> lstEliminados;
    
    private int tam; 
    
    public TADGrafoD(String label){
        this.label = label;
        this.mat = new int[16][16];
        this.dicLblEdge = new TADDicChain();
        this.dicLblVertex = new TADDicChain();
        this.lstEliminados = new LinkedList<>();
        this.tam = 16;
    }
    
    public TADGrafoD(String label, int tam){
        this.label = label;
        this.mat = new int[tam][tam];
        this.dicLblEdge = new TADDicChain();
        this.dicLblVertex = new TADDicChain();
        this.lstEliminados = new LinkedList<>();
        this.tam = tam;
    }
    
    public String getNome(){
        return this.label;
    }
    
    public int numVertices(){
        return this.qntVertexes;
    }
    
    public LinkedList<Vertex> vertices(){
        LinkedList<Vertex> vertices = new LinkedList<>();
        LinkedList<Object> dicElements = this.dicLblVertex.elements();
        for(int i=0; i<dicElements.size();i++){
            vertices.add((Vertex)dicElements.get(i));
        }
        return vertices;
    }
    
    public int numEdges(){
        return this.qntEdges;
    }
    
    public LinkedList<Edge> edges(){
        LinkedList<Edge> edges = new LinkedList<>();
        LinkedList<Object> dicElements = this.dicLblEdge.elements();
        for(int i=0; i<dicElements.size();i++){
            edges.add((Edge)dicElements.get(i));
        }
        return edges;
    }
    
    public void printGrafoMat(){
        for(int i = this.primVertice; i <= this.ultiVertice; i++){
            if(!this.lstEliminados.contains(i)){
                for(int j = this.primVertice; j <= this.ultiVertice; j++){
                    System.out.print(mat[i][j]+" | ");
                }
                System.out.println();
            }
        }
    }
    
    public Vertex getVertex(String label){
        Vertex vertex = (Vertex)this.dicLblVertex.findElement(label);
        if(this.dicLblVertex.NO_SUCH_KEY()) return null;
        return vertex;
    }
    
    private int geraIdVertex(){ 
        int id = 0;
        
        if(this.lstEliminados.isEmpty()) {
            id = this.idVertex++;
        }
        else {
            id = this.lstEliminados.get(0);
            this.lstEliminados.remove();
        }
        
        if(id < this.primVertice)
            this.primVertice = id;
        
        else if(id > this.ultiVertice)
            this.ultiVertice = id;
        
        return id;
    }
    
    public Vertex insertVertex(String label, Object dado){
        Vertex vertex = getVertex(label);
        if(this.dicLblVertex.NO_SUCH_KEY()){
            int id = geraIdVertex();
            vertex = new Vertex(label, dado);
            vertex.setId(id);
            this.dicLblVertex.insertItem(label, vertex);
            this.qntVertexes++;
        }
        else{
            vertex.setDado(dado);
        }
        return vertex;
    }
    
    public Edge insertEdge(String origem, String destino, String label, Object dado){
        Vertex vOrigem = this.getVertex(origem);
        if(this.dicLblVertex.NO_SUCH_KEY()) return null;
        
        Vertex vDestino = this.getVertex(destino);
        if(this.dicLblVertex.NO_SUCH_KEY()) return null;
        
        Edge edge = (Edge)this.dicLblEdge.findElement(label);
        if(this.dicLblEdge.NO_SUCH_KEY()){
            edge = new Edge(label, dado);
            edge.setId(this.idEdge++);
            this.dicLblEdge.insertItem(label, edge);
            mat[vOrigem.getId()][vDestino.getId()] = edge.getId();
            this.qntEdges++;
        }
        else{
            edge.setDado(dado);
        }
        
        return edge;
    }
    
    public Edge insertEdge(String origem, String destino, String label, Object dado, int peso){
        Vertex vOrigem = this.getVertex(origem);
        if(this.dicLblVertex.NO_SUCH_KEY()) return null;
        
        Vertex vDestino = this.getVertex(destino);
        if(this.dicLblVertex.NO_SUCH_KEY()) return null;
        
        Edge edge = (Edge)this.dicLblEdge.findElement(label);
        if(this.dicLblEdge.NO_SUCH_KEY()){
            edge = new Edge(label, dado);
            edge.setId(this.idEdge++);
            edge.setPeso(peso);
            this.dicLblEdge.insertItem(label, edge);
            mat[vOrigem.getId()][vDestino.getId()] = edge.getId();
            this.qntEdges++;
        }
        else{
            edge.setDado(dado);
        }
        
        return edge;
    }
    
    public Edge getEdge(String edgeLabel){
        Edge edge = null;
        LinkedList<Object> list = this.dicLblEdge.elements();
        for(int i=0; i<list.size();i++){
            Edge e = (Edge)list.get(i);
            if(e.getLabel().equals(edgeLabel)) edge = e;
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
        
        LinkedList<Object> listEdges = this.dicLblEdge.elements();
        
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
        Vertex vertex = (Vertex)this.dicLblVertex.findElement(label);
        if(this.dicLblVertex.NO_SUCH_KEY()) return null;
      
        int idVertex = vertex.getId();
        int grau = 0;

        for(int i = this.primVertice; i <= this.ultiVertice; i++) {
            if((mat[i][idVertex] != 0) && !this.lstEliminados.contains(i)) {
                grau++;
            }
        }
        return grau;
    }
    
    public Integer outDegree(String label){ 
        Vertex vertex = (Vertex)this.dicLblVertex.findElement(label);
        if(this.dicLblVertex.NO_SUCH_KEY()) return null;
        
        int idVertex = vertex.getId();
        int grau = 0;

        for(int i = this.primVertice; i <= this.ultiVertice; i++) {
            if((mat[idVertex][i] != 0) && !this.lstEliminados.contains(i)) {
                grau++;
            }
        }
        return grau;
    }
    
    public Object removeEdge(String label){
        Edge edge = (Edge)this.dicLblEdge.findElement(label);
        if(this.dicLblEdge.NO_SUCH_KEY()) return null;
        
        int idEdge = edge.getId();
        
        for(int i = this.primVertice; i <= this.ultiVertice ; i++){
            if(!this.lstEliminados.contains(i)){
                for(int j = this.primVertice; j <= this.ultiVertice; j++){
                    if(this.mat[i][j] == idEdge){
                        this.qntEdges--;
                        this.mat[i][j] = 0;
                        this.dicLblEdge.removeElement(label);
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
        
        if(idVertex == this.primVertice){
            for(int i = this.primVertice+1; i <= this.ultiVertice; i++){
                if(!this.lstEliminados.contains(i)){
                    this.primVertice = i;
                    break;
                }
            }
        }
        
        else if(idVertex == this.ultiVertice){
            for(int i = this.ultiVertice-1; i > this.primVertice; i--){
                if(!this.lstEliminados.contains(i)){
                    this.ultiVertice = i;
                    break;
                }
            }
        }
        
        for(int i = this.primVertice; i <= this.ultiVertice; i++){
            if(this.mat[idVertex][i] != 0){
                this.mat[idVertex][i] = 0;
                this.qntEdges--;
            }
            if(this.mat[i][idVertex] != 0 && this.mat[idVertex][i] != this.mat[i][idVertex]){
                this.mat[i][idVertex] = 0;
                this.qntEdges--;
            }
        }
        
        this.lstEliminados.add(idVertex);
        this.qntVertexes--;
        return this.dicLblVertex.removeElement(label);
    }
    
    private Vertex intToVertex(int id){
        LinkedList<Object> list = this.dicLblVertex.elements();
        
        for(int i = 0; i < list.size(); i++){
            Vertex vertex = (Vertex)list.get(i);
            if(vertex.getId() == id) return vertex;
        }
        return null;
    }
    
    public Vertex[] endVertices(String edgeLabel){
        Edge edge = (Edge)this.dicLblEdge.findElement(edgeLabel);
        if(this.dicLblEdge.NO_SUCH_KEY()) return null;
        
        int idEdge = edge.getId();
        
        for(int i = this.primVertice; i<=this.ultiVertice; i++){
            for(int j = this.primVertice; j<=this.ultiVertice; j++){
                if(this.mat[i][j] == idEdge){
                    Vertex[] vertices = new Vertex[2];
                    vertices[0] = this.intToVertex(i);
                    vertices[1] = this.intToVertex(j);
                    return vertices;
                }
            }
        }
        return null;
    }
    
    private Edge intToEdge(int id){
        LinkedList<Object> list = this.dicLblEdge.elements();
        
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
        if(this.dicLblVertex.NO_SUCH_KEY()) return null;
        
        LinkedList<Object> list = new LinkedList<>();
        for(int i = this.primVertice; i<= this.ultiVertice; i++){
            if(this.mat[vertex.getId()][i] != 0){
                list.add(this.intToEdge(this.mat[vertex.getId()][i]));
            }
        }
        return list;
    }
    
    public LinkedList<Object> incomingEdges(String labelVertex){
        Vertex vertex = this.getVertex(labelVertex);
        if(this.dicLblVertex.NO_SUCH_KEY()) return null;
        
        LinkedList<Object> list = new LinkedList<>();
        for(int i = this.primVertice; i <= this.ultiVertice; i++){
            if(this.mat[i][vertex.getId()] != 0){
                list.add(this.intToEdge(this.mat[i][vertex.getId()]));
            }
        }
        return list;
    }
    
    
    public Vertex opposite(String vertexLabel, String edgeLabel){
        Vertex vertex = this.getVertex(vertexLabel);
        if(vertex == null) return null;
        
        Edge edge = (Edge)this.dicLblEdge.findElement(edgeLabel);
        if(this.dicLblEdge.NO_SUCH_KEY()) return null;
        
        int idVertex = vertex.getId();
        int idEdge = edge.getId();
        
        for(int i = this.primVertice; i<= this.ultiVertice; i++){
            if(!this.lstEliminados.contains(i) && this.mat[idVertex][i] == idEdge )
                return this.intToVertex(i);
        }
        return null;
    }
    
    public LinkedList<Edge>outIncidentEdges(String verticeLabel){
        Vertex vertex = this.getVertex(verticeLabel);
        if(vertex == null) return null;
        LinkedList<Edge> list = new LinkedList<>();
        int id = vertex.getId();
        
        for(int i=this.primVertice; i <= this.ultiVertice; i++){
            if(!this.lstEliminados.contains(i) && this.mat[id][i] != 0){
                list.add(this.intToEdge(this.mat[id][i]));
            }
        }
        
        return list;    
    }
    
    public LinkedList<Edge>inIncidentEdges(String verticeLabel){
        Vertex vertex = this.getVertex(verticeLabel);
        if(vertex == null) return null;
        LinkedList<Edge> list = new LinkedList<>();
        int id = vertex.getId();
        
        for(int i=this.primVertice; i <= this.ultiVertice; i++){
            if(!this.lstEliminados.contains(i) && this.mat[i][id] != 0){
                list.add(this.intToEdge(this.mat[i][id]));
            }
        }
        
        return list;    
    }
    
    public LinkedList<Edge>incidentEdges(String verticeLabel){
        LinkedList<Edge> list = this.inIncidentEdges(verticeLabel);
        list.addAll(this.outIncidentEdges(verticeLabel));
        return list;
    }
    
    public LinkedList<Vertex> outAdjacenteVertices(String verticeLabel){
        Vertex vertex = this.getVertex(verticeLabel);
        if(vertex == null) return null;
        LinkedList<Vertex> list = new LinkedList<>();
        int id = vertex.getId();
        
        for(int i=this.primVertice; i <= this.ultiVertice; i++){
            if(!this.lstEliminados.contains(i) && this.mat[id][i] != 0)
                list.add(this.intToVertex(i));
        }
        
        return list;
    }
    
    public LinkedList<Vertex>inAdjacenteVertices(String verticeLabel){
        Vertex vertex = this.getVertex(verticeLabel);
        if(vertex == null) return null;
        LinkedList<Vertex> list = new LinkedList<>();
        int id = vertex.getId();
        
        for(int i=this.primVertice; i <= this.ultiVertice; i++){
            if(!this.lstEliminados.contains(i) && this.mat[i][id] != 0)
                list.add(this.intToVertex(i));
        }
        
        return list;
    }
    
    public LinkedList<Vertex> adjacentVertices(String verticeLabel){
        LinkedList<Vertex> list = this.inAdjacenteVertices(verticeLabel);
        list.addAll(this.outAdjacenteVertices(verticeLabel));
        return list;
    }
    
    public Vertex destination(String edgeLabel){
        Vertex[] ends = this.endVertices(edgeLabel);
        return ends[1]; // 0 - Origem, 1 - Destino
    }
    
    public Vertex origin(String edgeLabel){
        Vertex[] ends = this.endVertices(edgeLabel);
        return ends[0]; // 0 - Origem, 1 - Destino
    }
    
    public boolean areAdjacent(String vOrigem, String vDestino){
        Vertex origem = this.getVertex(vOrigem);
        if(origem == null) return false;
        
        Vertex destino = this.getVertex(vDestino);
        if(destino == null) return false;
        
        if(this.mat[origem.getId()][destino.getId()] != 0) return true;
        
        return false;
    }
    
    @Override
    public TADGrafoD clone(){
        TADGrafoD gClone = new TADGrafoD(this.label, this.tam);
        
        LinkedList<Vertex>allVertex = this.vertices();
        LinkedList<Edge>allEdges = this.edges();
        
        for(int i=0;i<allVertex.size();i++){
            Vertex v = allVertex.get(i);
            gClone.insertVertex(v.getLabel(), v.getDado());
        }
        
        for(int i=0; i<allEdges.size();i++){
            Edge e = allEdges.get(i);
            Vertex[] endEdges = this.endVertices(e.getLabel());
            gClone.insertEdge(endEdges[0].getLabel(), endEdges[1].getLabel(), e.getLabel(), e.getDado(), e.getPeso());
        }
         
        return gClone;
    }
    
    public static TADGrafoD carregaTGF(String fileName, int matSize) throws IOException{
        TADGrafoD g = new TADGrafoD(fileName, matSize);
        
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader arq = new BufferedReader(fileReader);
            String linha = arq.readLine();
            boolean arestas = false;
            
            while(linha != null){
                if(linha.contains("#")){
                    arestas = true;
                    linha = arq.readLine();
                }
                
                String[] l = linha.split(" ");
                if(!arestas){    
                    String value = "";
                    for(int i=1;i<l.length;i++){
                        value += " " + l[i];
                    }
                    g.insertVertex(l[0], value);
                }
                else{
                    String edgeLabel = l[0]+'-'+l[1];
                    if(l.length < 3)
                        g.insertEdge(l[0], l[1], edgeLabel, "");
                    else // Se no arquivo TGF existir um terceiro campo com os pesos
                        g.insertEdge(l[0], l[1], edgeLabel, "", Integer.parseInt(l[2]));
                }
                linha = arq.readLine();
            }
            arq.close();
            return g;
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TADGrafoD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
    public boolean equals(TADGrafoD g){

        if(g.numEdges() != this.numEdges() || g.numVertices() != this.numVertices()) return false;
        LinkedList<Edge> localEdges = this.edges();
        LinkedList<Edge> otherEdges = g.edges();
        
        for(int i=0; i<localEdges.size();i++){
            if(!localEdges.get(i).equals(otherEdges.get(i))) return false;
        }
        
        LinkedList<Vertex> localVertex = this.vertices();
        LinkedList<Vertex> otherVertex = this.vertices();
        
        for(int i=0; i<localVertex.size();i++){
            if(!localVertex.get(i).equals(otherVertex.get(i))) return false;
        }
        
        return true;
    }
}
