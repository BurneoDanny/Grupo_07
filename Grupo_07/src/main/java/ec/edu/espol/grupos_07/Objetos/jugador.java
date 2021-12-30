/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.grupos_07.Objetos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javafx.scene.control.Button;

/**
 *
 * @author User
 */
public class Jugador implements utilidadID{
    private Character jugador;
    private Character computadora;
    public Jugador(){
        jugador = null;
        computadora = null;
    }
    public Jugador (String jg ){
        if(isOperand(jg)){
            if(jg.toLowerCase() == "x"){
            jugador = "x".charAt(0);
            computadora="o".charAt(0);
        }else{
            jugador = "o".charAt(0);
            computadora="x".charAt(0);
                    
                    }
            
        }
            
        
        
    }

    public Character getJugador() {
        return jugador;
    }

    public Character getComputadora() {
        return computadora;
    }
    public boolean isOperand(String id){
        if(id.toLowerCase() == "x" || id.toLowerCase()=="o"){
            return true;
        }else{
            return false;
            
        }
    
        
    }
    
    @Override
    public int obtenerUtilidad(Map tree) {
        int contador = 0;
        int indice =0;
        ArrayList<ArrayList<Button>> lista = new ArrayList<>();
        Object[] valores = tree.values().toArray();
        ArrayList<Button> temp = new ArrayList<>();        
        for(Object ob1 : valores){
            if(contador<3){
                temp.add((Button)ob1);
                
            }else{
                lista.add(indice, temp);
                temp.clear();
                contador =0;
                indice++;
            }
            
        }
        return horizontal(lista) + vertical(lista) + diagonal(lista);
    }

    @Override
    public int horizontal(ArrayList<ArrayList<Button>> lista) {
        int contador = 0;
        for(int i = 0 ; i<3 ; i++){
            ArrayList<Button> botones = lista.get(i);// lista de botones primera;
            if(!botones.contains(computadora)){
                contador ++;
            }
        }          return contador;  

    }

    @Override
    public int diagonal(ArrayList<ArrayList<Button>> lista) {
        ArrayList<Button> fila1 = lista.get(0);
        ArrayList<Button> fila2 = lista.get(1);
        ArrayList<Button> fila3 = lista.get(2);
        ArrayList<Button> diag1 = new ArrayList<>();
        ArrayList<Button> diag2 =  new ArrayList<>();
        diag1.add(0, fila1.get(0)); /// implementar este metodo en ArrayList TDA
        diag1.add(1, fila2.get(1));
        diag1.add(2, fila3.get(2));
        diag2.add(0, fila3.get(0));
        diag2.add(1, fila2.get(1));
        diag2.add(2, fila1.get(2));
        ArrayList<ArrayList<Button>> listaT = new ArrayList<>();
        listaT.add(0, diag1);
        listaT.add(1, diag2);
        int contador = 0;
        for(int i = 0 ; i<3 ; i++){
            ArrayList<Button> botones = lista.get(i);// lista de botones primera;
            if(!botones.contains(computadora)){
                contador ++;
            }
        }          return contador;  

        
    }

    @Override
    public int vertical(ArrayList<ArrayList<Button>> lista) {
        ArrayList<Button> fila1 = lista.get(0);
        ArrayList<Button> fila2 = lista.get(1);
        ArrayList<Button> fila3 = lista.get(2);
        ArrayList<Button> column1 = new ArrayList<>();
        ArrayList<Button> column2 =  new ArrayList<>();
        ArrayList<Button> column3 =  new ArrayList<>();
        column1.add(0, fila1.get(0)); /// implementar este metodo en ArrayList TDA
        column1.add(1, fila1.get(1));
        column1.add(2, fila1.get(2));
        column2.add(0, fila2.get(0));
        column2.add(1, fila2.get(1));
        column2.add(2, fila2.get(2));
        column3.add(0, fila3.get(0));
        column3.add(1, fila3.get(1));
        column3.add(2, fila3.get(2));
        ArrayList<ArrayList<Button>> listaT = new ArrayList<>();
        listaT.add(0, column1);
        listaT.add(1, column2);
        listaT.add(2, column3);
        int contador = 0;
        for(int i = 0 ; i<3 ; i++){
            ArrayList<Button> botones = lista.get(i);// lista de botones primera;
            if(!botones.contains(computadora)){
                contador ++;
            }
        }          return contador;  

    }
    
}
