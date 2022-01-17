/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.Partida;

import javafx.scene.control.Button;

/**
 *
 * @author User
 */
public class Casilla {
    private Button btn1;
    private int x;
    private int y;
    private boolean estado;  
    public Casilla(Button b , int fila , int columna){
        this.btn1=b;
        this.x=fila;
        this.y=columna;
    }
   

    public Button getBtn1() {
        return btn1;
    }

    public void setBtn1(Button btn1) {
        this.btn1 = btn1;
    }
    
}
