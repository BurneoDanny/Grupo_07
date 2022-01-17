
package ec.edu.espol.TDAs;

import ec.edu.espol.Partida.Tablero;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class Tree<E> { // ARBOL MULTICAMINO
    private NodeTree<E> root; // el arbol solo conoce a su raiz

    public NodeTree<E> getRoot() {
        return root;
    }

    public void setRoot(NodeTree<E> root) {
        this.root = root;
    }
    
    public Tree(){ // si inicializan el arbol sin nada
        this.root = null; 
    }

    public Tree(NodeTree<E> root) { // construye un arbol con el nodo raiz
        this.root = root;
    }
    
    public Tree(E content){
        NodeTree<E> nodeTree = new NodeTree(content);
        this.root = nodeTree;
    }
    
    public boolean isEmpty(){
        return this.root == null;
    }
  public void mostrarRamas() {
        // lista para guardar los valores de los de la rama
        List<E> rama = new ArrayList<>();
        // agregar el valor de la raiz
        rama.add(this.root.getContent());

        mostrarRamas(this.root, rama);
    }

    private void mostrarRamas(NodeTree<E> nodo, List<E> rama) {
        // si el nodo no tiene hijos imprimir la colección de valores
        if (nodo.getChildren().isEmpty()) {
            for(E r:rama){
                ((Tablero)r).imprimirCasillas();
                 System.out.println("---------");       
            }
        }
        // recorrer los hijos del nodo
        LinkedList<Tree<E>> hijos = nodo.getChildren();
        for (Tree<E> n : nodo.getChildren()) {
            // agregar el valor  del nodo a la lista
            rama.add(n.getRoot().getContent());
            // llamada recursiva
            mostrarRamas(n.getRoot(), rama);
            // retirar el ultimo valor insertado
            rama.remove(rama.size() - 1);
        }
        System.out.println("---------------------------------------------------------------------------------------------");       
    }
    public void recorrerNiveles() {
        // lista para guardar los valores de los nodos
        List<E> valores = new ArrayList<>();
        // agregar el valor de la raíz
        valores.add(root.getContent());

        // iterar desde 1 hasta altura - 1
        // altura - 1 porque si el arbol tiene altura 4 solo se 
        // necesitan 3 niveles porque la raiz ya está incluida
        for (int i = 1; i < 8; i++) {
            // llamda recursiva para bajar un nivel
            recorrerNiveles(root, i - 1, valores);
        }
        
        System.out.println(valores);
        
        

    }

    private void recorrerNiveles(NodeTree<E> nodo, int nivel, List<E> valores) {
       // iterar los hijos del nodo
       LinkedList<Tree<E>> hijos = nodo.getChildren();
        for (Tree<E> n : hijos) {
            // si ya se llegó al nivel 0 agregar los valores a la lista
            if (nivel == 0)
                valores.add(n.getRoot().getContent());
            else // seguir bajando de nivel hasta llegar al nivel cero
                recorrerNiveles(n.getRoot(), nivel - 1, valores);
        }


    }
    
 
}


