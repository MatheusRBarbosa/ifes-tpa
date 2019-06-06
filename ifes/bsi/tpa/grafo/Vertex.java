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
public class Vertex {
    private String label;
    private Object dado;
    
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

    public void setDado(Object dado) {
        this.dado = dado;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
