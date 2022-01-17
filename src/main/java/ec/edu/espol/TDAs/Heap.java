package ec.edu.espol.TDAs;

import java.util.Comparator;

public class Heap<E> {

    private Comparator<E> cmp;
    private E[] arreglo;
    
    // tamaño maximo del heap
    private int MAX = 100;
   
    // tamaño que tiene el heap en realidad (solo los objetos que tiene)
    private int efectivo; 
    private boolean isMax;

    // CONSTRUCTOR QUE INSTANCIA UN HEAP SIN NINGUN ELEMENTO, SOLO SU MAX Y UN COMPARADOR
    public Heap(int MAX, Comparator<E> cmp) { 
        this.cmp = cmp;
        this.MAX = MAX;
        arreglo = (E[]) new Object[MAX]; 
    }
    
    // CONSTRUCTOR QUE INSTANCIA UN HEAP CON UN BOOLEAN (PARA SABER SI ES MAXIMO O MINIMO) Y SU COMPARADOR
    public Heap(boolean isMax, Comparator<E> cmp) { 
        this.cmp = cmp;
        this.isMax = isMax;
        arreglo = (E[]) new Object[MAX]; 
        this.efectivo = 0;
    }
    
    // INSERTAMOS UN ARREGLO A NUESTRO HEAP
    public void setArreglo(E[] arreglo) { 
        this.efectivo = arreglo.length;
        for(int i = 0; i<efectivo; i++){
            this.arreglo[i] = arreglo[i];
        }
        //this.arreglo = arreglo;
        makeHeap();
    }

    public int getEfectivo() {
        return efectivo;
    }

    public void setCmp(Comparator<E> cmp) {
        this.cmp = cmp;
    }

    private void makeHeap() {
        int i;
        for (i = (this.efectivo / 2) - 1; i >= 0; i--) {
            ajustar(i);
        }
    }

    private void ajustar(int elemento) {
        int mayor = elemento;
        int izquierda = elemento * 2 + 1;
        int derecha = elemento * 2 + 2;
        if (izquierda >= 0 && izquierda < this.efectivo && isMaxorMin(this.isMax,izquierda,mayor)) {
            mayor = izquierda;
            if (derecha >= 0 && derecha < this.efectivo && isMaxorMin(this.isMax,derecha,mayor)) {
                mayor = derecha;
            }
            if (mayor != elemento) {
                intercambiar(elemento, mayor);
                ajustar(mayor);              
            }
  
        } 
        else if (derecha >= 0 && derecha < this.efectivo && isMaxorMin(this.isMax,derecha,mayor)) {
            mayor = derecha;
            if (izquierda >= 0 && izquierda < this.efectivo && isMaxorMin(this.isMax,izquierda,mayor)) {
                mayor = izquierda;    
            }
            if (mayor != elemento) {
                intercambiar(elemento, mayor);
                ajustar(mayor);              
            } 
        }
    }
    
    
    private boolean isMaxorMin(boolean isMax, int posicion, int padre){
        if(isMax == true){
            return cmp.compare(this.arreglo[posicion], this.arreglo[padre]) > 0;
        }
        else{
            return cmp.compare(this.arreglo[posicion], this.arreglo[padre]) < 0;
        }
    
    }

    private void intercambiar(int elemento, int mayor) {
        E temp = arreglo[elemento];
        arreglo[elemento] = arreglo[mayor]; 
        arreglo[mayor] = temp;
    }

    public void print() {
        for (int i = 0; i < efectivo; i++) {
            System.out.print(""+arreglo[i]+" ");
        }
        System.out.println(" ");
        for (int i = 0; i < efectivo; i++) {
            System.out.print(i+" ");
        }
        System.out.println(" ");
    }
    

    private void addCapacity(){
        E[] tmp = (E[]) new Object[MAX*2];
        for(int i = 0; i<MAX; i++){
            tmp[i] = arreglo[i];
        }
        arreglo = tmp;
        MAX = MAX * 2;  
    }
    
    public void insert(E newElement){
        if (MAX == efectivo){
            addCapacity();
        }    
        arreglo[efectivo] = newElement;
        efectivo ++;
        encolar(efectivo-1);  
    }
    
    private void encolar(int newElement){
        if(newElement == 0) ;
        else if(newElement % 2 == 0){
            int padre = (newElement/2)-1;
            if (padre >= 0 && padre < this.efectivo && isMaxorMin(this.isMax,newElement,padre)) {         
                intercambiar(newElement, padre);
                encolar(padre);               
            }
        }
        else{
            int izquierda = newElement;
            int derecha = izquierda+1;
            int padre = (derecha/2)-1;
            if (padre >= 0 && padre < this.efectivo && isMaxorMin(this.isMax,newElement,padre)) {
                intercambiar(newElement, padre);
                encolar(padre);                 
            } 
        }
    }
    
    public E remove(){
        E root = arreglo[0];
        intercambiar(efectivo-1,0);
        efectivo--;
        ajustar(0);
        return root;
    
    }   

}
