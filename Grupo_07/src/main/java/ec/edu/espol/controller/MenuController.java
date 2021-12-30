
package ec.edu.espol.controller;

import ec.edu.espol.grupo_07.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class MenuController implements Initializable {

    @FXML
    private Pane CircleSelected;
    private boolean Circle = false;
    @FXML
    private Pane XSelected;
    private boolean Equis = false;
    @FXML
    private Button SinglePlayerOption;
    @FXML
    private Button TwoPlayersOption;
    @FXML
    private Button JustPcOption;
    @FXML
    private Pane pcOption;
    private boolean pcStarts = false;
    @FXML
    private Pane playerOption;
    private boolean playerStarts = false;
    @FXML
    private Pane WhosFirstPane;
    @FXML
    private Pane FigureSelectionPane;
    @FXML
    private Pane Menu;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Menu.toFront();
    }  
    



    @FXML
    private void NormalMode(MouseEvent event) {
        FigureSelectionPane.toFront();
        
        
    }

    @FXML
    private void TwoPlayersMode(MouseEvent event) {
        
    }

    @FXML
    private void JustPcMode(MouseEvent event) {
        
    }

    @FXML
    private void CircleSelection(MouseEvent event) {
        WhosFirstPane.toFront();
        Circle = true;
    }

    @FXML
    private void XSelection(MouseEvent event) {
        WhosFirstPane.toFront();
        Equis = true;
    }

    @FXML
    private void pcStarts(MouseEvent event) {
        pcStarts = true;
        start();
    }

    @FXML
    private void PlayerStarts(MouseEvent event) {
        playerStarts = true;
        start();
    }
    
    private void start(){
        try{

            FXMLLoader fxmlloader = App.loadFXMLLoader("game");  
            App.setRoot(fxmlloader);
            GameController gc = fxmlloader.getController();  
            gc.CargarJuego(Circle, Equis, pcStarts, playerStarts);
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo fxml");
            a.show();
        } 
    
    }
}
