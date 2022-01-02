
package ec.edu.espol.TDAs;


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
    
 
}


