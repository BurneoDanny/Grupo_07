package ec.edu.espol.controller;

import ec.edu.espol.Partida.PC;
import ec.edu.espol.Partida.TableroPCVPC;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class JustPcModeController implements Initializable {

    private PC pc1;
    private PC pc2;
    private TableroPCVPC tablero;

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
    @FXML
    private Text waitingForPC;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settingIndex(tableroActual);
    }

    public void CargarJuego(Image pc1Figure, Image pc2Figure) {
        pc1 = new PC(pc1Figure);
        pc2 = new PC(pc2Figure);
        tablero = new TableroPCVPC(tableroActual, pc1, pc2);       
        Thread tt = new Thread(new pcRunnable());
        tt.setDaemon(true);
        tt.start();
    }

    class pcRunnable implements Runnable {
        @Override
        public void run() {
            try {
                while (!tablero.isThereEmpate()) {
                    Platform.runLater(() -> {
                        if(!tablero.isThereEmpate()){
                            waitingForPC.setText("PC1 pensando . . ."); 
                        }
                    });
                    Thread.sleep(2000);
                    Platform.runLater(() -> {
                        if(!tablero.emptyCells().isEmpty()){
                            tablero = new TableroPCVPC(tableroActual, pc1, pc2);
                            tablero.miniMaxUsingTrees(); 
                            waitingForPC.setText("");
                            if(!tablero.isThereEmpate()){
                                waitingForPC.setText("PC2 pensando . . ."); 
                            }
                        }
                    });
                    Thread.sleep(2000);
                    Platform.runLater(() -> {
                        if(!tablero.emptyCells().isEmpty()){
                            tablero = new TableroPCVPC(tableroActual, pc2, pc1);
                            waitingForPC.setText("");
                            tablero.miniMaxUsingTrees();
                        }
                    });
                }
                winner.setText("Empate!");
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void settingIndex(GridPane tablero) {
        for (int i = 0; i < tablero.getChildren().size(); i++) {
            ImageView imageView = (ImageView) tablero.getChildren().get(i);
            if (imageView.getId().equals("celda1")) {
                GridPane.setColumnIndex(imageView, 0);
                GridPane.setRowIndex(imageView, 0);
            }
            if (imageView.getId().equals("celda2")) {
                GridPane.setColumnIndex(imageView, 1);
                GridPane.setRowIndex(imageView, 0);
            }
            if (imageView.getId().equals("celda3")) {
                GridPane.setColumnIndex(imageView, 2);
                GridPane.setRowIndex(imageView, 0);
            }
            if (imageView.getId().equals("celda4")) {
                GridPane.setColumnIndex(imageView, 0);
                GridPane.setRowIndex(imageView, 1);
            }
            if (imageView.getId().equals("celda5")) {
                GridPane.setColumnIndex(imageView, 1);
                GridPane.setRowIndex(imageView, 1);
            }
            if (imageView.getId().equals("celda6")) {
                GridPane.setColumnIndex(imageView, 2);
                GridPane.setRowIndex(imageView, 1);
            }
            if (imageView.getId().equals("celda7")) {
                GridPane.setColumnIndex(imageView, 0);
                GridPane.setRowIndex(imageView, 2);
            }
            if (imageView.getId().equals("celda8")) {
                GridPane.setColumnIndex(imageView, 1);
                GridPane.setRowIndex(imageView, 2);
            }
            if (imageView.getId().equals("celda9")) {
                GridPane.setColumnIndex(imageView, 2);
                GridPane.setRowIndex(imageView, 2);
            }
        }
    }

}
