
package ec.edu.espol.controller;

import ec.edu.espol.grupo_07.App;
import ec.edu.espol.util.util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class MenuController implements Initializable {

    @FXML
    private Pane WhosFirstPane;
    @FXML
    private Pane FigureSelectionPane;
    @FXML
    private Pane Menu;        
    @FXML
    private Pane FirstPlayerSelectionPane;
    @FXML
    private Pane SecondPlayerSelectionPane;
    @FXML
    private ImageView definitiveSecondPlayerFigure;
    @FXML
    private ImageView startIndicador;
    
    private Image playerFigure;
    private Image pcFigure;
    private boolean pcStarts;
    private Image secondPlayerFigure;
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Menu.toFront();
    }  
    
    @FXML
    private void NormalMode(MouseEvent event) {
        FigureSelectionPane.toFront();
        
        
    }

    @FXML
    private void CircleSelection(MouseEvent event) {
        WhosFirstPane.toFront();
        playerFigure = util.getCircle();
        pcFigure = util.getEquis();
                
    }

    @FXML
    private void XSelection(MouseEvent event) {
        WhosFirstPane.toFront();
        playerFigure = util.getEquis();
        pcFigure = util.getCircle();
    }

    @FXML
    private void pcStarts(MouseEvent event) {
        pcStarts = true;
        startSinglePlayerMode();
    }

    @FXML
    private void PlayerStarts(MouseEvent event) {
        pcStarts = false;
        startSinglePlayerMode();
    }
    
    private void startSinglePlayerMode(){
        try{
            FXMLLoader fxmlloader = App.loadFXMLLoader("game");  
            App.setRoot(fxmlloader);
            GameController gc = fxmlloader.getController();  
            gc.CargarJuego(pcStarts, playerFigure, pcFigure);
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo fxml");
            a.show();
        }  
    }
    
    
    //----------------------- PLAYER VS PLAYER MODE
    
    @FXML
    private void TwoPlayersMode(MouseEvent event) {
        FirstPlayerSelectionPane.toFront();
        
    }
    

    @FXML
    private void FirstPlayerCircleSelection(MouseEvent event) {
        SecondPlayerSelectionPane.toFront();
        playerFigure = util.getCircle();
        secondPlayerFigure = util.getEquis();
        definitiveSecondPlayerFigure.setImage(secondPlayerFigure);
        startIndicador.setImage(util.getIndicador());
        
    }

    @FXML
    private void FirstPlayerXSelection(MouseEvent event) {
        SecondPlayerSelectionPane.toFront();
        playerFigure = util.getEquis();
        secondPlayerFigure = util.getCircle();
        definitiveSecondPlayerFigure.setImage(secondPlayerFigure);
        startIndicador.setImage(util.getIndicador());
    }
    
    @FXML
    private void startTwoPlayersGame(MouseEvent event) {
        try{
            FXMLLoader fxmlloader = App.loadFXMLLoader("twoPlayersMode");  
            App.setRoot(fxmlloader);
            TwoPlayersModeController tpmc = fxmlloader.getController();
            tpmc.CargarJuego(playerFigure, secondPlayerFigure);
            
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo fxml");
            a.show();
        }
    }

    
    
    //----------------------- PC VS PC MODE
    @FXML
    private void JustPcMode(MouseEvent event) {
        try{
            FXMLLoader fxmlloader = App.loadFXMLLoader("justPcMode");  
            App.setRoot(fxmlloader);
            JustPcModeController jpmc = fxmlloader.getController();
            jpmc.CargarJuego(util.getCircle(), util.getEquis());  
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo fxml");
            a.show();
        }
    }
    
    
    //----------------------------- BOTON CAMBIOS DE ESTILO
    @FXML
    private void changeStyle(MouseEvent event) {

        Menu.setId("BackgroundOscuroPane");
        //Menu.getStylesheets().add("BackgroundOscuroPane");
        //Menu.setStyle("css/modoOscuro/BackgroundOscuroPane");
    }

    
}
