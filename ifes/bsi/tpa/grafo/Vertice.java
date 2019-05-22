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
public class Vertice {
    
    private Object elemento;
    /*Contadores*/

    public Vertice(Object elemento){
        this.elemento = elemento;
    }

    public Object getConteudo() {
        return this.elemento;
    }
    
    @Override
    public boolean equals(Object vertice){
        System.out.println("EQUALS: "+this.toString());
        return this.toString().equals(vertice.toString());
    }
    
}
