/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bugallolugomizrahi;

import javax.swing.JOptionPane;

/**
 *
 * @author davidmizrahi
 */
public class Graph {
   
    private Lista<String> vertices;
    
    private Integer[][] edges;// Lista<Lista<Integer>>
    
    public Graph(){
        
        this.vertices = new Lista<>();
        
        this.edges = new Integer[this.vertices.size()][this.vertices.size()];
        
    }

    public Lista<String> getVertices() {
        return vertices;
    }

    public void setVertices(Lista<String> vertices) {
        this.vertices = vertices;
    }

    public Integer[][] getEdges() {
        return edges;
    }

    public void setEdges(Integer[][] edges) {
        this.edges = edges;
    }
    
    
    public void insertVertix(String s){
        
        if(!vertices.Buscar(s))vertices.agregarElemento(s);
        
        
        
        Integer[][] newEdge = new Integer[vertices.size()][vertices.size()];
        
        if(this.edges != null)
       for (int i = 0; i < this.edges.length; i++) {
            for (int j = 0; j < this.edges.length; j++) {
                newEdge[i][j] = this.edges[i][j];
                
            }
            
       }
        this.edges = newEdge;

        
        
        
        
    }
    
    public void insertEdge(String origin, String destination, int weight){
         
        int originPos = this.vertices.buscarPosicion(origin);
        int destinationPos = this.vertices.buscarPosicion(destination);
        
        this.edges[originPos][destinationPos] = weight;
    }
    
    
    
}
