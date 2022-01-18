
package ec.edu.espol.controller;

import ec.edu.espol.Partida.Player;
import ec.edu.espol.TDAs.modoJuego;
import ec.edu.espol.grupo_07.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;


public class MenuController implements Initializable {

    @FXML
    private Pane CircleSelected;    
    @FXML
    private Pane XSelected;
    @FXML
    private Button SinglePlayerOption;
    @FXML
    private Button TwoPlayersOption;
    @FXML
    private Button JustPcOption;
    @FXML
    private Pane pcOption;
    @FXML
    private Pane playerOption;
    @FXML
    private Pane WhosFirstPane;
    @FXML
    private Pane FigureSelectionPane;
    @FXML
    private Pane Menu;
    
    private Image playerFigure;
    private Image pcFigure;
    private static boolean pcStarts;
    private static final modoJuego juego=new modoJuego(0);
        
    private static final Player pc = new Player("ss");
    private static final Player player = new Player("ss");
    @FXML
    private Button botonPC;
    @FXML
    private Button BotonPlayer;
    @FXML
    private Pane CircleSelected1;
    @FXML
    private Label selecioneSu;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Menu.toFront();
    }  
    public Player getPlayer(){
        return player;
    }
    public Player getPc(){
        return pc;
    }
    public modoJuego getModoJuego(){
        return juego;
    }



    @FXML
    private void NormalMode(MouseEvent event) {
        FigureSelectionPane.toFront();
        juego.setModo(0);
        
        
    }

    @FXML
    private void TwoPlayersMode(MouseEvent event) {
        selecioneSu.setText("Elija el simbolo del Jugador 1 ");
        botonPC.setText("Jugador 2");
        BotonPlayer.setText("Jugador 1");
        FigureSelectionPane.toFront();        
        juego.setModo(1);

        
    
    }

    @FXML
    private void JustPcMode(MouseEvent event) {
        selecioneSu.setText("Elija el simbolo de la PC1");
        botonPC.setText("PC 2");
        BotonPlayer.setText("PC 1");
        FigureSelectionPane.toFront();
        juego.setModo(2);
   
    }

    @FXML
    private void CircleSelection(MouseEvent event) {
        pc.setFigure("X");
        player.setFigure("O");
        WhosFirstPane.toFront();
       
       
                
    }

    @FXML
    private void XSelection(MouseEvent event) {
          player.setFigure("X");
        pc.setFigure("O");
        WhosFirstPane.toFront();
  
    }

    @FXML
    private void pcStarts(MouseEvent event) {
        pcStarts = true;
        start();
    }

    @FXML
    private void PlayerStarts(MouseEvent event) {
        pcStarts = false;
        start();
    }
    
    private void start(){
        try{
            FXMLLoader fxmlloader = App.loadFXMLLoader("game");  
            App.setRoot(fxmlloader);
            GameController gc = fxmlloader.getController();  
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo fxml");
            a.show();
        } 
    
    }
   
    
    public boolean getPcStar(){
        return pcStarts;
    }
  
    
}
