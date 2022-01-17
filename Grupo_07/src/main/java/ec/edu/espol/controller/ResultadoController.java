/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.Partida.Tablero;
import ec.edu.espol.grupo_07.App;
import ec.edu.espol.util.util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import static javafx.stage.StageStyle.TRANSPARENT;

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
    @FXML
    private Pane pane;
    
    private Image playerFigure;
    private Image pcFigure;
    private boolean pcStarts;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
    public void CargarResultado(Tablero tablero,ImageView im1, ImageView im2, ImageView im3, ImageView im4,
    ImageView im5, ImageView im6, ImageView im7, ImageView im8, ImageView im9, Image playerFigure, Image pcFigure, boolean pcStarts){ 
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
        this.tableroActual = tablero.getGridpane();
        this.playerFigure = playerFigure;
        this.pcFigure = pcFigure;
        this.pcStarts = pcStarts;
        if(tablero.isThereWinner()){
            winner.setText(util.isImageEqual(tablero.returnWinnerFigure().getImage(), tablero.getPlayer().getFigure()) ? 
                    "P\nL\nA\nY\nE\nR\n\nW\nI\nN\nS" : "P\nC\n\nW\nI\nN\nS");
            
        }
        else{
            winner.setText("E\nM\nP\nA\nT\nE");
        }
        
        ImageView winner = this.tablero.returnWinnerFigure();
        //Horizontales
        if (util.isImageEqual(Cell1.getImage(), winner.getImage())&& util.isImageEqual(Cell2.getImage(), winner.getImage())
                && util.isImageEqual(Cell3.getImage(), winner.getImage())){
            Line linea = new Line(28,117,556,117);
            linea.setLayoutX(60);
            linea.setLayoutY(20);
            linea.setStrokeWidth(10);
            linea.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(linea);
            
        }
        if (util.isImageEqual(Cell4.getImage(), winner.getImage())&& util.isImageEqual(Cell5.getImage(), winner.getImage())
                && util.isImageEqual(Cell6.getImage(), winner.getImage())){
            Line linea = new Line(28,320,556,320);
            linea.setLayoutX(60);
            linea.setLayoutY(20);
            linea.setStrokeWidth(10);
            linea.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(linea);
        }
        if (util.isImageEqual(Cell7.getImage(), winner.getImage())&& util.isImageEqual(Cell8.getImage(), winner.getImage())
                && util.isImageEqual(Cell9.getImage(), winner.getImage())){
            Line linea = new Line(28,546,556,546);
            linea.setLayoutX(60);
            linea.setLayoutY(20);
            linea.setStrokeWidth(10);
            linea.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(linea);
        }
        //Verticales
        if (util.isImageEqual(Cell1.getImage(), winner.getImage())&& util.isImageEqual(Cell4.getImage(), winner.getImage())
                && util.isImageEqual(Cell7.getImage(), winner.getImage())){
            Line linea = new Line(115,28,115,624);
            linea.setLayoutX(60);
            linea.setLayoutY(20);
            linea.setStrokeWidth(10);
            linea.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(linea);
        }
        if (util.isImageEqual(Cell2.getImage(), winner.getImage())&& util.isImageEqual(Cell5.getImage(), winner.getImage())
                && util.isImageEqual(Cell8.getImage(), winner.getImage())){
            Line linea = new Line(287,28,287,624);
            linea.setLayoutX(60);
            linea.setLayoutY(20);
            linea.setStrokeWidth(10);
            linea.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(linea);
        }
        if (util.isImageEqual(Cell3.getImage(), winner.getImage())&& util.isImageEqual(Cell6.getImage(), winner.getImage())
                && util.isImageEqual(Cell9.getImage(), winner.getImage())){
            Line linea = new Line(462,28,462,624);
            linea.setLayoutX(60);
            linea.setLayoutY(20);
            linea.setStrokeWidth(10);
            linea.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(linea);
        }
        //Diagonales
        if (util.isImageEqual(Cell1.getImage(), winner.getImage())&& util.isImageEqual(Cell5.getImage(), winner.getImage())
                && util.isImageEqual(Cell9.getImage(), winner.getImage())){
            Line linea = new Line(36,28,551,636);
            linea.setLayoutX(60);
            linea.setLayoutY(20);
            linea.setStrokeWidth(10);
            linea.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(linea);
        }
        if (util.isImageEqual(Cell3.getImage(), winner.getImage())&& util.isImageEqual(Cell5.getImage(), winner.getImage())
                && util.isImageEqual(Cell7.getImage(), winner.getImage())){
            Line linea = new Line(551,28,36,636);
            linea.setLayoutX(60);
            linea.setLayoutY(20);
            linea.setStrokeWidth(10);
            linea.setStrokeLineCap(StrokeLineCap.ROUND);
            pane.getChildren().add(linea);
        }
    } 

    @FXML
    private void volverAjugar(MouseEvent event) {
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

    @FXML
    private void volverAlMenu(MouseEvent event) {
        try{
            FXMLLoader fxmlloader = App.loadFXMLLoader("menu");  
            App.setRoot(fxmlloader);
        }
        catch(IOException ex){
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo fxml");
            a.show();
        }
    }

    
}
