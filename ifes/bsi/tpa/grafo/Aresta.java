/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.grafo;

/**
 *
 * @author barbosa
 */
public class Aresta {
    private Object elemento;
    private boolean dirigido;
    private Vertice[] dir;
    
    public Aresta(Vertice origem, Vertice destino, Object elemento){
        this.elemento = elemento;
        this.dir[0] = origem;
        this.dir[1] = destino;
        this.dirigido = false;
    }
    
    public void setDirigido(boolean dirigido){
        this.dirigido = dirigido;
    }
}
