/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.grafo;

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
    
    TADDicChain dicEdges;
    TADDicChain dicVertexes;
    
    LinkedList<Integer> eliminados;
    
    public TADGrafo(String label){
        this.label = label;
        this.mat = new int[16][16];
        this.dicEdges = new TADDicChain();
        this.dicVertexes = new TADDicChain();
    }
    
    public int numVertex(){
        return this.qntVertexes;
    }
    
    public int numEdge(){
        return this.qntEdges;
    }
    
    
}
