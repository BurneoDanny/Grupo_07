/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.Partida.PC;
import ec.edu.espol.Partida.Player;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author eDannX
 */
public class GameController implements Initializable {

    @FXML
    private GridPane tableroActual;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void CargarJuego(boolean pcStarts, String playerFigure, String pcFigure ){
        PC pc = new PC(pcFigure);
        Player player = new Player(playerFigure);        
        if(pcStarts == true){ // pc comienza
            //metodo minimax que recibe tablero actual
        }  
        
        
    }
    
    
    
}
