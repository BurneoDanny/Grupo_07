
package ec.edu.espol.TDAs;

import java.util.LinkedList; // la Linked list del java collection network es una lista doblemente enlazada


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
    
    
 
}


class NodeTree<E> {
    private E content; 
    // private LinkedList<NodeTree<E>> children; // sus hijos, sin embargo no aplicaremos este metodo en este curso (no es el mejor approach)
    private LinkedList<Tree<E>> children;

    public NodeTree(E content) {
        this.content = content;
        this.children = new LinkedList<>(); // no esta en null, solo esta vacia ya que es una LinkedList
    }

    public E getContent() {
        return content;
    }

    public void setContent(E content) {
        this.content = content;
    }

    public LinkedList<Tree<E>> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<Tree<E>> children) {
        this.children = children;
    }
   
}