/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifes.bsi.tpa.taddic.grafo;

/**
 *
 * @author barbosa
 */
public class Vertex {
    private String label;
    private Object dado;
    private int id;
    
    public Vertex(String label, Object dado){
        this.label = label;
        this.dado = dado;
    }

    public Object getDado() {
        return dado;
    }

    public String getLabel() {
        return label;
    }
    
    public int getId(){
        return id;
    }

    public void setDado(Object dado) {
        this.dado = dado;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public boolean equals(Edge e){
        if(this.label != e.getLabel()) return false;
        if(!this.dado.equals(e.getDado())) return false;
        return true;
    }
}
