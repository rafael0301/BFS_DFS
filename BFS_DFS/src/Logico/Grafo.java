package Logico;

import java.util.ArrayList;

public class Grafo {
    private int cantidadVertices;
    private ArrayList<ArrayList<Integer>> listaAdyacencia;
    private ArrayList<Character> vertices;
    
    public Grafo(int[][] matrizAdyacencia) {
        this.cantidadVertices = matrizAdyacencia.length;
        this.listaAdyacencia = new ArrayList<>();
        this.vertices = new ArrayList<>();
        
        for(int i = 0; i < cantidadVertices; i++) {
            vertices.add((char)('A' + i));
            listaAdyacencia.add(new ArrayList<>());
        }
        
        for(int i = 0; i < cantidadVertices; i++) {
            for(int j = 0; j < cantidadVertices; j++) {
                if(matrizAdyacencia[i][j] == 1) {
                    listaAdyacencia.get(i).add(j);
                }
            }
        }
    }
    
    public ArrayList<Character> recorridoAnchura(char inicio) {
        ArrayList<Character> recorrido = new ArrayList<>();
        ArrayList<Boolean> visitados = new ArrayList<>();
        ArrayList<Integer> cola = new ArrayList<>();
        
        for(int i = 0; i < cantidadVertices; i++) {
            visitados.add(false);
        }
        
        int indiceInicio = vertices.indexOf(inicio);
        cola.add(indiceInicio);
        visitados.set(indiceInicio, true);
        
        while(!cola.isEmpty()) {
            int verticeActual = cola.remove(0);
            recorrido.add(vertices.get(verticeActual));
            
            for(int vecino : listaAdyacencia.get(verticeActual)) {
                if(!visitados.get(vecino)) {
                    visitados.set(vecino, true);
                    cola.add(vecino);
                }
            }
        }
        
        return recorrido;
    }
    
    public ArrayList<Character> recorridoProfundidad(char inicio) {
        ArrayList<Character> recorrido = new ArrayList<>();
        ArrayList<Boolean> visitados = new ArrayList<>();
        
        for(int i = 0; i < cantidadVertices; i++) {
            visitados.add(false);
        }
        
        dfsRecursivo(vertices.indexOf(inicio), visitados, recorrido);
        
        return recorrido;
    }
    
    private void dfsRecursivo(int vertice, ArrayList<Boolean> visitados, ArrayList<Character> recorrido) {
        visitados.set(vertice, true);
        recorrido.add(vertices.get(vertice));
        
        for(int vecino : listaAdyacencia.get(vertice)) {
            if(!visitados.get(vecino)) {
                dfsRecursivo(vecino, visitados, recorrido);
            }
        }
    }
    
    public void imprimirListaAdyacencia() {
        for(int i = 0; i < cantidadVertices; i++) {
            System.out.print(vertices.get(i) + ": ");
            for(int vecino : listaAdyacencia.get(i)) {
                System.out.print(vertices.get(vecino) + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        int[][] matriz = {
            {0, 1, 0, 0, 1, 0, 1, 0, 0, 0}, // A
            {1, 0, 1, 1, 0, 0, 0, 0, 0, 0}, // B
            {0, 1, 0, 0, 1, 0, 0, 1, 1, 0}, // C
            {0, 1, 0, 0, 0, 0, 0, 1, 1, 0}, // D
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 1}, // E
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0}, // F
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // G
            {0, 0, 1, 1, 0, 1, 0, 0, 1, 1}, // H
            {0, 0, 1, 1, 0, 1, 0, 1, 0, 0}, // I
            {0, 0, 0, 0, 1, 0, 1, 1, 0, 0}  // J
        };
        
        Grafo grafo = new Grafo(matriz);
        
        System.out.println("Lista de Adyacencia:");
        grafo.imprimirListaAdyacencia();
        
        System.out.println("\nRecorrido en Anchura (BFS) desde A:");
        ArrayList<Character> recorridoBFS = grafo.recorridoAnchura('A');
        for(int i = 0; i < recorridoBFS.size(); i++) {
            System.out.print(recorridoBFS.get(i));
            if(i < recorridoBFS.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
        
        System.out.println("\nRecorrido en Profundidad (DFS) desde A:");
        ArrayList<Character> recorridoDFS = grafo.recorridoProfundidad('A');
        for(int i = 0; i < recorridoDFS.size(); i++) {
            System.out.print(recorridoDFS.get(i));
            if(i < recorridoDFS.size() - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println();
    }
}