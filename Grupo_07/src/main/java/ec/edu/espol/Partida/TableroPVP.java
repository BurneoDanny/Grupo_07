
package ec.edu.espol.Partida;

import ec.edu.espol.util.util;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class TableroPVP {
    private Player player1;
    private Player player2;
    private GridPane gridpane;

    public TableroPVP(Player player1, Player player2, GridPane gridpane) {
        this.player1 = player1;
        this.player2 = player2;
        this.gridpane = gridpane;
    }
    
    
    public ArrayList<ImageView> emptyCells() {
        ArrayList<ImageView> emptyCells = new ArrayList<>();
        for (int i = 0; i < gridpane.getChildren().size(); i++) {
            ImageView imageView = (ImageView) gridpane.getChildren().get(i);
            if (imageView.getImage() == null) {
                emptyCells.add(imageView);
            }
        }
        return emptyCells;
    }
    
    public boolean isFirstPlayerTurn(){
        ArrayList<ImageView> fullCells = new ArrayList<>();
        for (int i = 0; i < gridpane.getChildren().size(); i++) {
            ImageView imageView = (ImageView) gridpane.getChildren().get(i);
            if (imageView.getImage() != null) {
                fullCells.add(imageView);
            }
        }
        return fullCells.size() % 2 == 0;  
    }
    
    
    private ImageView getNodeByRowColumnIndex(final int row, final int column) {
        ImageView result = null;
        ObservableList<Node> childrens = gridpane.getChildren();
        for (Node node : childrens) {
            ImageView nodo = (ImageView) node;
            if (GridPane.getRowIndex(nodo) == row && GridPane.getColumnIndex(nodo) == column) {
                result = nodo;
                break;
            }
        }
        return result;
    }
    
    
    public boolean isThereWinner(){
        return returnWinnerFigure() != null;    
    }
    
    /*
        Retorna la imageView/figura de quien gano, ya se diagonalmente, horizontalmente o verticalmente. 
        y retorna null si hay empate o si nadie ha ganado todavia. 
    */
    public ImageView returnWinnerFigure() {
        ImageView winnerFigure = null;
        // horizontal
        for (int i = 0; i < 3; i++) {
            if (util.isEqual(getNodeByRowColumnIndex(i, 0).getImage(),
                    getNodeByRowColumnIndex(i, 1).getImage(),
                    getNodeByRowColumnIndex(i, 2).getImage())) {
                winnerFigure = getNodeByRowColumnIndex(i, 0);
            }
        }
        // vertical
        for (int i = 0; i < 3; i++) {
            if (util.isEqual(getNodeByRowColumnIndex(0, i).getImage(),
                    getNodeByRowColumnIndex(1, i).getImage(),
                    getNodeByRowColumnIndex(2, i).getImage())) {
                winnerFigure = getNodeByRowColumnIndex(0, i);
            }
        }
        // Diagonal
        if (util.isEqual(getNodeByRowColumnIndex(0, 0).getImage(),
                getNodeByRowColumnIndex(1, 1).getImage(),
                getNodeByRowColumnIndex(2, 2).getImage())) {
            winnerFigure = getNodeByRowColumnIndex(0, 0);
        }
        if (util.isEqual(getNodeByRowColumnIndex(2, 0).getImage(),
                getNodeByRowColumnIndex(1, 1).getImage(),
                getNodeByRowColumnIndex(0, 2).getImage())) {
            winnerFigure = getNodeByRowColumnIndex(2, 0);
        }
        return winnerFigure;
    }

    // comprueba si hay empate
    public boolean isThereEmpate() {
        return returnWinnerFigure() == null && emptyCells().isEmpty();
    }
    
    
    
}
