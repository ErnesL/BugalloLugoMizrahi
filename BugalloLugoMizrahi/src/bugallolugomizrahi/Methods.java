/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bugallolugomizrahi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * @author davidmizrahi
 */

public class Methods {
    
    public static int[][] shortestPath(Graph grafo, int origen){
        
        
        
        Queue<Integer> cola = new Queue<>();
        
        boolean[] visitados = new boolean[grafo.getVertices().size()];
        
        visitados[origen] = true;
        
        int[][] distancias = new int[2][grafo.getVertices().size()];
        
        for(int i = 0; i < distancias[0].length; i++){
        
            distancias[0][i] = Integer.MAX_VALUE; //valor infinito
            distancias[1][i] = -1;
        }
        distancias[0][origen] = 0;
        
        
        cola.enqueue(origen);
        
        while(!cola.isEmpty()){
            
            int almacen = cola.dequeue();
            
            visitados[almacen] = true;
            
           Integer[] adyacentes = grafo.getEdges()[almacen];
           
            for (int i = 0; i < adyacentes.length; i++) {
                
                
                if(adyacentes[i] != null && !visitados[i]){
                    
                    
                    
                    int distancia = Math.min(distancias[0][i], distancias[0][almacen] + adyacentes[i]);
                    
                    
                    
                    int salto = distancia == distancias[0][i] ? distancias[1][i] : almacen;
                    
                    
                    distancias[0][i] = distancia;
                    
                    distancias[1][i] = salto;
                   
                    if(!cola.existe(i)){
                        
                        cola.enqueue(i);
                    
                    }                    
                }
            }
        }
        

        
        return distancias;
    
    }
    
    public static String printShortestPath(Graph graph, String origin, String destination , int[] distancias){
        
        Arrays.sort(distancias);
        
        ArrayList<Integer> path = new ArrayList<>();
        String stringify = "";
        
        
        int originIndex = graph.getVertices().buscarPosicion(origin);
        
        int destinationIndex = graph.getVertices().buscarPosicion(destination);
        

        path.add(distancias[destinationIndex]);
        
        
        int left = 0;
        
        int right = distancias.length - 1;
        
        int target = distancias[destinationIndex];
        
        
        int mid_element = 0;
       
        while(left <= right){
            
            int mid = left + (right - left)/2;
            
             mid_element = distancias[mid];
            
            if(mid_element == target){
            
                path.add(mid_element);
                
                target = distancias[target];
                
                
                left = 0;
                
                right = distancias.length - 1;
            }
            
            if(mid_element > target){
            
                right = mid - 1;
                
            }
            
            if(mid_element < target){
                left = mid + 1;
            }
            
            if(path.get(path.size() - 1) == originIndex)break;
            
        }
       
        System.out.println(path);
        ArrayList<Integer> newPath = removeDuplicates(path);
        System.out.println(newPath);
        for (int i = 0; i < newPath.size(); i++) {
            
            stringify+= graph.getVertices().buscarPorIndice(i);
            System.out.println(stringify);
//            int distancia = distancias[i];
            
            
        }
        for (int i = 0; i < distancias.length; i++) {
            System.out.println(distancias[i]);
            
        }
        return stringify;
        
    }
    
    
    public static Lista<Integer> printShortestPath1(Graph graph, String origin, String destination , int[] distancias){
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < distancias.length; i++) {
//            if(!map.containsKey(i))
            
            map.put(i, distancias[i]);
           
            
            
        }
        int originIndex = graph.getVertices().buscarPosicion(origin);
        int destIndex = graph.getVertices().buscarPosicion(destination);
        
//        ArrayList<Integer> path = new ArrayList<>();
        Lista<Integer> path = new Lista();
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(originIndex);
        boolean[] visited = new boolean[graph.getVertices().size()];
        visited[originIndex] = true;
        
        while(!queue.isEmpty()){
        
            int pos = queue.dequeue();
            
            path.agregarElemento(pos);
            
            if(distancias[destIndex] == pos){
            
                path.agregarElemento(destIndex);
                visited[destIndex] = true;
            }
            
           if((Integer)path.buscarPorIndice(path.size() - 1) == destIndex)break;
            
            
            
            
            
            for(int i = 0; i < distancias.length; i++){
              
                if(distancias[i] == pos && !visited[i]){
                
                    
                    
                    visited[i] = true;
//                    path.add(i);
                    queue.enqueue(i);
                }
    }
//            queue.enqueue(pos);
            
        }

//        Iterator<Integer> iter = path.iterator();
//while(iter.hasNext()){
//    if(iter.next()!= destIndex && !map.containsValue(iter.next()))
//        iter.remove();
//}
//        for (int key: map.keySet()) {
//            
//            
//        }
        for (int i = 0; i < path.size(); i++) {
            if((Integer)path.buscarPorIndice(i) != destIndex && !map.containsValue((Integer)path.buscarPorIndice(i))){
            
                path.eliminarElemento((Integer)path.buscarPorIndice(i));
            }
            
        }
        return path;
        
            
    }
    
    public static Lista<Integer> dfs(Graph g, int origin, int dest, int[] distancias){
        Lista<Integer> path = new Lista<>();
        boolean[] marked = new boolean[g.getVertices().size()];
        Stack<Integer> stack = new Stack<>();
        stack.push(origin);
        while(!stack.isEmpty()){
            int v = stack.pop();
            if(distancias[dest] == v && !marked[v]){
                path.agregarElemento(v);
                break;
            }
            if(!marked[v]){
                path.agregarElemento(v);
                marked[v] = true;
                
                for (int i = 0; i < distancias.length; i++) {
                    if(!marked[i] && distancias[i] != -1){
                    
                        stack.push(i);
                    }
                    
                }
            }
        }
        return path;
    }
    
    public static String printShortestPathInterface(Graph g, Lista<Integer> path){
    
        String s = "";
        Nodo aux = path.getpFirst();
        
        while(aux != null){
        
           s+= g.getVertices().buscarPorIndice((Integer)aux.getInfo()) + "->";
           aux = aux.getpNext();
        }
        
        return s;
    }
    
    
    
    public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> list) {
    
        ArrayList<Integer> newPath = new ArrayList<>();
        
        for(int index : list){
        
            if(!newPath.contains(index)){
            
                newPath.add(index);
            }
        }
        return newPath;
    }
    
    
    public static int[] bfs(Graph g, int src, int tar){
    Queue<Integer> q = new Queue<>();
    int gSize = g.getEdges().length;
    boolean[] visited = new boolean[gSize];
    visited[src] = true;
    int[] ans = new int[g.getEdges()[0].length];
    
    q.enqueue(src);
        
    while(!q.isEmpty()){
            int index = q.dequeue();
            Integer[] explore = g.getEdges()[index];
            visited[index] = true;
            
            for(int i = 0; i < explore.length; i++){
            
                if(explore[i] != null && !visited[index])q.enqueue(i);
                
                if(index == tar)ans[i] = g.getEdges()[index][i];
            }
            
        }
        return ans;
    }
    
    public static String printPath(ArrayList<Integer> path, Graph g)
{
    String s = "";
    int size = path.size();
    for(Integer v : path)
    {
        s+= g.getVertices().buscarPorIndice(v) + "->";
    }
//    System.out.println();
    JOptionPane.showMessageDialog(null, s);
    return s;
}
 
// Utility function to check if current
// vertex is already present in path
public static boolean isNotVisited(int x, ArrayList<Integer> path)
                                    
{
    int size = path.size();
    for(int i = 0; i < size; i++){
        if (path.get(i) == x)
            return false;
    }
    return true;
}
 
// Utility function for finding paths in graph
// from source to destination
public static String findpaths(Graph g,
                              int src, int dst, int v)
{
     
    // Create a queue which stores
    // the paths
    Queue<ArrayList<Integer>> queue = new Queue<>();
    String s = "";
 
    // Path vector to store the current path
    ArrayList<Integer> path = new ArrayList<>();
    path.add(src);
    queue.enqueue(path);
     
    while (!queue.isEmpty())
    {
        path = queue.dequeue();
        int last = path.get(path.size() - 1);
 
        // If last vertex is the desired destination
        // then print the path
        if (last == dst)
        {
//           String s = "";
    int size = path.size();
    for(Integer p : path)
    {
        s+= g.getVertices().buscarPorIndice(p) + "->";
    }
//    System.out.println();
    s+="\n";
//    JOptionPane.showMessageDialog(null, s);
        }
 
        // Traverse to all the nodes connected to
        // current vertex and push new path to queue
        
        Integer[] lastNode = g.getEdges()[last];
//        ArrayList<Integer> lastNode = g.get(last);
        for(int i = 0; i < lastNode.length; i++)
        {                   //lastNode[i]
            if (lastNode[i] != null && isNotVisited(i, path))
            {
                ArrayList<Integer> newpath = new ArrayList<>(path);
                
//                            lastNode[i];
                newpath.add(i);
                queue.enqueue(newpath);
            }
        }
    }
        return s;
}
}
    

