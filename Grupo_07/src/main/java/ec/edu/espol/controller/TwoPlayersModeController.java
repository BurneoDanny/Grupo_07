
package ec.edu.espol.controller;

import ec.edu.espol.Partida.Player;
import ec.edu.espol.Partida.TableroPVP;
import ec.edu.espol.util.util;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class TwoPlayersModeController implements Initializable {
    
    private Player player1;
    private Player player2;
    private TableroPVP tablero;

    @FXML
    private GridPane tableroActual;
    @FXML
    private ImageView celda1;
    @FXML
    private ImageView celda2;
    @FXML
    private ImageView celda3;
    @FXML
    private ImageView celda4;
    @FXML
    private ImageView celda5;
    @FXML
    private ImageView celda6;
    @FXML
    private ImageView celda7;
    @FXML
    private ImageView celda8;
    @FXML
    private ImageView celda9;
    @FXML
    private Text winner;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settingIndex(tableroActual);
    }    
    
    public void CargarJuego(Image player1Figure, Image player2Figure ){
        player1 = new Player(player1Figure);
        player2 = new Player(player2Figure);
        tablero = new TableroPVP(player1, player2, tableroActual);  
    }
    
    @FXML
    private void clickedCelda(MouseEvent event) {
        ArrayList<ImageView> cells = tablero.emptyCells();
        ImageView cell = (ImageView)event.getSource();
        if (!(cell.getImage() == util.getCircle() || cell.getImage() == util.getEquis())){
            if(tablero.isFirstPlayerTurn()){
                if(!tablero.isThereWinner() && !tablero.isThereEmpate()){
                    cell.setImage(util.isImageEqual(player1.getFigure(),util.getCircle()) ? util.getCircle() : util.getEquis());
                }
                else{
                    winner.setText(util.isImageEqual(player1.getFigure(),tablero.returnWinnerFigure().getImage()) ? "Gana player 1" : "Gana player 2");
                }
            }
            else{
                if(!tablero.isThereWinner() && !tablero.isThereEmpate() ){
                    cell.setImage(util.isImageEqual(player2.getFigure(),util.getCircle()) ? util.getCircle() : util.getEquis());
                }
                else{
                    winner.setText(util.isImageEqual(player1.getFigure(),tablero.returnWinnerFigure().getImage()) ? "Gana player 1" : "Gana player 2");
                }
            }       
        } 
    }
    
    private void settingIndex(GridPane tablero){  
        for(int i = 0; i<tablero.getChildren().size(); i++){
            ImageView imageView = (ImageView)tablero.getChildren().get(i);
            if(imageView.getId().equals("celda1")) {
                GridPane.setColumnIndex(imageView, 0);
                GridPane.setRowIndex(imageView, 0);
            }
            if(imageView.getId().equals("celda2")) {
                GridPane.setColumnIndex(imageView, 1);
                GridPane.setRowIndex(imageView, 0);         
            }
            if(imageView.getId().equals("celda3")) {
                GridPane.setColumnIndex(imageView, 2);
                GridPane.setRowIndex(imageView, 0);         
            }
            if(imageView.getId().equals("celda4")) {
                GridPane.setColumnIndex(imageView, 0);
                GridPane.setRowIndex(imageView, 1);      
            }
            if(imageView.getId().equals("celda5")) {
                GridPane.setColumnIndex(imageView, 1);
                GridPane.setRowIndex(imageView, 1);          
            }
            if(imageView.getId().equals("celda6")) {
                GridPane.setColumnIndex(imageView, 2);
                GridPane.setRowIndex(imageView, 1);           
            }
            if(imageView.getId().equals("celda7")) {
                GridPane.setColumnIndex(imageView, 0);
                GridPane.setRowIndex(imageView, 2);            
            }
            if(imageView.getId().equals("celda8")) {
                GridPane.setColumnIndex(imageView, 1);
                GridPane.setRowIndex(imageView, 2);                 
            }
            if(imageView.getId().equals("celda9")) {
                GridPane.setColumnIndex(imageView, 2);
                GridPane.setRowIndex(imageView, 2);           
            }         
        }               
    }
    
    
    
    
}
