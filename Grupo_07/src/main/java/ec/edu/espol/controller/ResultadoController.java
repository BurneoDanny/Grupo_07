/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.Partida.Tablero;
import ec.edu.espol.util.util;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Dell
 */
public class ResultadoController implements Initializable {

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
    @FXML
    private Label winner;
    
    private Tablero tablero;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void CargarResultado(Tablero tablero,ImageView im1, ImageView im2, ImageView im3, ImageView im4,
    ImageView im5, ImageView im6, ImageView im7, ImageView im8, ImageView im9){ 
        Cell1.setImage(im1.getImage());
        Cell2.setImage(im2.getImage());
        Cell3.setImage(im3.getImage());
        Cell4.setImage(im4.getImage());
        Cell5.setImage(im5.getImage());
        Cell6.setImage(im6.getImage());
        Cell7.setImage(im7.getImage());
        Cell8.setImage(im8.getImage());
        Cell9.setImage(im9.getImage());
        this.tablero = tablero;
        if(tablero.isThereWinner()){
            winner.setText(util.isImageEqual(tablero.returnWinnerFigure().getImage(), tablero.getPlayer().getFigure()) ? "Gana Player" : "Gana PC");
        }
        else{
            winner.setText("Empate");
        }
        
        
    } 

    
}
