/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.Partida.PC;
import ec.edu.espol.Partida.Player;
import ec.edu.espol.Partida.Tablero;
import ec.edu.espol.grupo_07.App;
import ec.edu.espol.util.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author eDannX
 */
public class GameController implements Initializable {

    @FXML
    private GridPane tableroActual;  
    @FXML
    private ImageView Cell1;
    @FXML
    private ImageView Cell2;
    @FXML
    private ImageView Cell3;
    @FXML
    private ImageView Cell4;
    @FXML
    private ImageView Cell5;
    @FXML
    private ImageView Cell6;
    @FXML
    private ImageView Cell7;
    @FXML
    private ImageView Cell8;
    @FXML
    private ImageView Cell9;
    
    private PC pc;
    private Player player;
    private Tablero tablero;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
    } 
    
    
    // carga la pc y al jugador con sus figuras correspondientemente y en caso de que empieza la pc, ejecuta minimax algorithm 
    public void CargarJuego(boolean pcStarts, Image playerFigure, Image pcFigure ){
        pc = new PC(pcFigure);
        player = new Player(playerFigure);
        tablero = new Tablero(tableroActual, player, pc);
        if(pcStarts == true){ // pc comienza
            //metodo minimax que recibe tablero actual
        }     
    }

    
    // este metodo se asegura de insertar la figura correspondiente del player solo si hay un espacio.
    @FXML
    private void cellClicked(MouseEvent event) {
        ImageView cell = (ImageView)event.getSource();
        if (!(cell.getImage() == util.getCircle() || cell.getImage() == util.getEquis())){
            cell.setImage(util.isImageEqual(player.getFigure(),util.getCircle()) ? util.getCircle() : util.getEquis());  
        }
        // inmediatamente despues de que de click se ejecuta el metodo minimax que recibe el tablero actual.
    }
    

    
    
    
    
    
    
    
    
}
