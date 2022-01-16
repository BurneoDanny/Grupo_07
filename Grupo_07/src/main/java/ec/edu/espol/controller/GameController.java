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
import java.io.IOException;
import java.io.InputStream;
import static java.lang.System.exit;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
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

    private PC pc;
    private Player player;
    private Tablero tablero;

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
    private Text waitingForPC;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settingIndex(tableroActual);
    }

    // carga la pc y al jugador con sus figuras correspondientemente y en caso de que empieza la pc, ejecuta minimax algorithm 
    public void CargarJuego(boolean pcStarts, Image playerFigure, Image pcFigure) {
        pc = new PC(pcFigure);
        player = new Player(playerFigure);
        tablero = new Tablero(tableroActual, player, pc);
        if (pcStarts == true) {
            tablero.miniMaxUsingTrees();
        }
    }

    // este metodo se asegura de insertar la figura correspondiente del player solo si hay un espacio.
    @FXML
    private void cellClicked(MouseEvent event) {
        ArrayList<ImageView> cells = tablero.emptyCells();
        ImageView cell = (ImageView) event.getSource();
        if (!tablero.isThereWinner() && !tablero.checkEmpate()) {
            if (!(cell.getImage() == util.getCircle() || cell.getImage() == util.getEquis())) {
                cell.setImage(util.isImageEqual(player.getFigure(), util.getCircle()) ? util.getCircle() : util.getEquis());
                if(!tablero.isThereWinner() && !tablero.checkEmpate()){
                    tablero.miniMaxUsingTrees();
                }
            }
        }
        else{
            showResult();
        }
    }



    private void showResult() {
        try {
            FXMLLoader fxmlloader = App.loadFXMLLoader("resultado");
            App.setRoot(fxmlloader);
            ResultadoController rc = fxmlloader.getController();
            rc.CargarResultado(tablero,Cell1, Cell2, Cell3, Cell4, Cell5, Cell6, Cell7, Cell8, Cell9);
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el archivo fxml");
            a.show();
        }

    }

    private void settingIndex(GridPane tablero) {
        for (int i = 0; i < tablero.getChildren().size(); i++) {
            ImageView imageView = (ImageView) tablero.getChildren().get(i);
            if (imageView.getId().equals("Cell1")) {
                GridPane.setColumnIndex(imageView, 0);
                GridPane.setRowIndex(imageView, 0);
            }
            if (imageView.getId().equals("Cell2")) {
                GridPane.setColumnIndex(imageView, 1);
                GridPane.setRowIndex(imageView, 0);
            }
            if (imageView.getId().equals("Cell3")) {
                GridPane.setColumnIndex(imageView, 2);
                GridPane.setRowIndex(imageView, 0);
            }
            if (imageView.getId().equals("Cell4")) {
                GridPane.setColumnIndex(imageView, 0);
                GridPane.setRowIndex(imageView, 1);
            }
            if (imageView.getId().equals("Cell5")) {
                GridPane.setColumnIndex(imageView, 1);
                GridPane.setRowIndex(imageView, 1);
            }
            if (imageView.getId().equals("Cell6")) {
                GridPane.setColumnIndex(imageView, 2);
                GridPane.setRowIndex(imageView, 1);
            }
            if (imageView.getId().equals("Cell7")) {
                GridPane.setColumnIndex(imageView, 0);
                GridPane.setRowIndex(imageView, 2);
            }
            if (imageView.getId().equals("Cell8")) {
                GridPane.setColumnIndex(imageView, 1);
                GridPane.setRowIndex(imageView, 2);
            }
            if (imageView.getId().equals("Cell9")) {
                GridPane.setColumnIndex(imageView, 2);
                GridPane.setRowIndex(imageView, 2);
            }
        }
    }

}
