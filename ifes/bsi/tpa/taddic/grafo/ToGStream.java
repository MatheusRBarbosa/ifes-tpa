/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ifes.bsi.tpa.taddic.grafo;
import java.util.LinkedList;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
/**
 *
 * @author User
 */
public class ToGStream {
    
    private Graph grafo;
    private boolean dirigido;
    private boolean vertexVisivel;
    private boolean edgeVisivel;
 
    
    public ToGStream(TADGrafoD g,boolean vertexVisivel, boolean edgeVisivel, boolean dirigido) {
		
        this.grafo = new SingleGraph(g.getNome());

        if(vertexVisivel) {
            LinkedList<Vertex> vertices = g.vertices();
            for(int i=0;i<vertices.size();i++) {
                Vertex v = vertices.get(i);
                Node no = this.grafo.addNode(v.getLabel());
                no.addAttribute("ui.label", v.getLabel());
            }
            if(edgeVisivel) {
                LinkedList<Edge> edges = g.edges();
                LinkedList<String[]> arestas = new LinkedList<String[]>();
                for (int i=0;i< edges.size();i++) {
                    Edge e = edges.get(i);
                    Vertex[] endV = g.endVertices(e.getLabel());
                    if(dirigido) {
                        org.graphstream.graph.Edge edge = this.grafo.addEdge(e.getLabel(), endV[0].getLabel(),endV[1].getLabel(),true);
                        edge.addAttribute("ui.label", e.getLabel());
                        String[] labelV = {endV[0].getLabel(),endV[1].getLabel()};
                        arestas.add(labelV);
                    }
                    else {
                        org.graphstream.graph.Edge edge = this.grafo.addEdge(e.getLabel(), endV[0].getLabel(),endV[1].getLabel());
                        edge.addAttribute("ui.label", e.getLabel());
                        String[] labelV = {endV[0].getLabel(),endV[1].getLabel()};
                        arestas.add(labelV);	
                    }
                }
            }
        }	
    }
    
    public void exibe() {	
        
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        this.grafo.setAttribute("stylesheet", "node { size: 30px; fill-color: yellow, orange; fill-mode: gradient-horizontal; text-size: 15px;} edge { z-index: 0; fill-color: #333; size: 3px; text-size: 10px; text-color: red; }");
        this.grafo.addAttribute("ui.quality");
        this.grafo.addAttribute("ui.antialias");
        this.grafo.addAttribute("ui.stylesheet", "");
        this.grafo.display();
    }
}
