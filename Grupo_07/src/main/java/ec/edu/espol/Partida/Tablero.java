package ec.edu.espol.Partida;

import ec.edu.espol.TDAs.NodeTree;
import ec.edu.espol.TDAs.Tree;
import ec.edu.espol.grupo_07.App;
import ec.edu.espol.util.util;
import java.util.ArrayList;
import java.util.LinkedList;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Tablero {

    private GridPane gridpane;
    private Player player;
    private PC pc;
    private int utilidad;
    private ImageView newCelda;

    public Tablero(GridPane tablero, Player player, PC pc) {
        this.gridpane = tablero;
        this.player = player;
        this.pc = pc;
    }

    public GridPane getGridpane() {
        return gridpane;
    }

    public void setGridpane(GridPane gridpane) {
        this.gridpane = gridpane;
    }

    public int getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(int mejorUtilidad) {
        this.utilidad = mejorUtilidad;
    }

    public ImageView getNewCelda() {
        return newCelda;
    }

    public void setNewCelda(ImageView bestCelda) {
        this.newCelda = bestCelda;
    }

    public Player getPlayer() {
        return player;
    }

    public PC getPc() {
        return pc;
    }
    
    

    // retorna la ImageView dado el index de fila y columna. 
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

    // retorna los ImageView de todas las celdas vacias
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

    public void miniMaxUsingTrees() {
        ImageView bestSpot = getBestSpot();
        ObservableList<Node> childrens = this.gridpane.getChildren();
        for (Node node : childrens) {
            ImageView nodo = (ImageView) node;
            if (nodo.getId().equals(bestSpot.getId())) {
                nodo.setImage(pc.getFigure());
            }
        }
    }
    
    private ImageView getBestSpot() {
        Tree<Tablero> mainTree = createTree();
        int mayorUtilidad = -10000;
        Tablero bestTablero = new Tablero(this.gridpane, this.player, this.pc);
        for (Tree<Tablero> hijo : mainTree.getRoot().getChildren()) {
            int hijoUtilidad = 0;
            if(hijo.getRoot().getChildren().isEmpty()) {
                hijoUtilidad = hijo.getRoot().getContent().getUtilidad();
                System.out.println("utilidad :" + hijoUtilidad);
                if (hijoUtilidad > mayorUtilidad) {
                    mayorUtilidad = hijoUtilidad;
                    bestTablero.setNewCelda(hijo.getRoot().getContent().getNewCelda());
                }
            }
            else{
                hijoUtilidad = getMinUtilidad(hijo);
                System.out.println("utilidad :" + hijoUtilidad);
                if (hijoUtilidad > mayorUtilidad) {
                    mayorUtilidad = hijoUtilidad;
                    bestTablero.setNewCelda(hijo.getRoot().getContent().getNewCelda());
                }
            }
        }
        System.out.println("Mayor utilidad usada: " + mayorUtilidad);
        return bestTablero.getNewCelda();
    }

    private int getMinUtilidad(Tree<Tablero> hijo) {
        int utilidadMinima = 10000;
        for (Tree<Tablero> arbolHoja : hijo.getRoot().getChildren()) {
            int utilidadTablero = arbolHoja.getRoot().getContent().getUtilidad();
            utilidadMinima = Math.min(utilidadMinima, utilidadTablero);
        }
        return utilidadMinima;
    }

    private Tree<Tablero> createTree() {
        Tree<Tablero> mainTree = new Tree(this);
        for (ImageView celda : emptyCells()) {
            celda.setImage(pc.getFigure());
            Tablero tableroHijo = new Tablero(this.gridpane, this.player, this.pc);
            tableroHijo.setNewCelda(celda);
            Tree<Tablero> hijo = new Tree(tableroHijo);
            mainTree.getRoot().getChildren().add(hijo);
            if (isThereWinner()) {
                tableroHijo.setUtilidad(10);
                celda.setImage(null);
            } 
            else {
                for (ImageView cell : emptyCells()) {
                    cell.setImage(player.getFigure());
                    Tablero tableroHoja = new Tablero(this.gridpane, this.player, this.pc);
                    Tree<Tablero> arbolHoja = new Tree(tableroHoja);
                    hijo.getRoot().getChildren().add(arbolHoja);
                    if(isThereWinner()){
                        tableroHoja.setUtilidad(-10); 
                        cell.setImage(null);
                    }
                    else{
                        int utilidadTableroHoja = getPCUtilidad();
                        tableroHoja.setUtilidad(utilidadTableroHoja);
                        cell.setImage(null);
                    }
                }
                celda.setImage(null);
            }
        }
        return mainTree;
    }

    // decide cual es la mejor celda donde podria la pc poner su figura.
    public void bestSpot() {
        int bestUtilidad = -10000;
        ArrayList<ImageView> celdasVacias = emptyCells();
        ImageView bestCell = new ImageView();
        for (ImageView celda : celdasVacias) {
            celda.setImage(pc.getFigure());
            if (isThereWinner()) {
                bestUtilidad = returnWinnerInteger();
                celda.setImage(null);
                bestCell.setId(celda.getId());
            } else {
                int MayorUtilidad = miniMax();
                celda.setImage(null);
                if (MayorUtilidad > bestUtilidad) {
                    bestUtilidad = MayorUtilidad;
                    bestCell.setId(celda.getId());
                }
            }
        }
        ObservableList<Node> childrens = gridpane.getChildren();
        for (Node node : childrens) {
            ImageView nodo = (ImageView) node;
            if (nodo.getId().equals(bestCell.getId())) {
                nodo.setImage(pc.getFigure());
            }
        }
    }

    // retorna un integer que nos dice cual movimiento deberia hacer la pc.
    private int miniMax() {
        //Tree<GridPane> tableros = new Tree(tablero);
        ArrayList<ImageView> cells = emptyCells();

        int bestUtilidad = 10000;
        for (ImageView cell : cells) {
            cell.setImage(player.getFigure());
            if (isThereWinner()) {
                bestUtilidad = returnWinnerInteger();
                cell.setImage(null);
            } else {
                int utilidad = getPCUtilidad();
                cell.setImage(null);
                bestUtilidad = Math.min(utilidad, bestUtilidad);
            }
        }
        return bestUtilidad;
    }

    // check if there is a winner or tie
    public boolean isThereWinner() {
        return returnWinnerInteger() == 10 || returnWinnerInteger() == -10;
    }

    /*
        retorna -10 si el player gana, 10 si la pc gana y 0 si hay empate. 
        Si nadie ha ganado retorna por defecto 1.
     */
    public int returnWinnerInteger() {
        ImageView result = returnWinnerFigure();
        if (result != null) {
            if (util.isImageEqual(result.getImage(), player.getFigure())) {
                return -10; // gana player
            } else if (util.isImageEqual(result.getImage(), pc.getFigure())) {
                return 10; //gane pc
            }
        }
        if (checkEmpate()) {
            return 0;
        }
        return 1;
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
    public boolean checkEmpate() {
        return returnWinnerFigure() == null && emptyCells().isEmpty();
    }

    // metodo que obtiene la utilidad del pc   
    public int getPCUtilidad() {
        return obtenerValorPdePC() - obtenerValorPdePlayer();
    }

    // metodo que obtiene el valor P de Player
    private int obtenerValorPdePlayer() {
        int valorP;
        if (util.isImageEqual(player.getFigure(), util.getCircle())) {
            valorP = 8 - (comprobarFilas(util.getEquis())
                    + comprobarColumnas(util.getEquis())
                    + comprobarDiagonales(util.getEquis()));

        } else {
            valorP = 8 - (comprobarFilas(util.getCircle())
                    + comprobarColumnas(util.getCircle())
                    + comprobarDiagonales(util.getCircle()));
        }
        return valorP;
    }
    
    

    // metodo que obtiene el valor P de PC
    private int obtenerValorPdePC() {
        int valorP;
        if (util.isImageEqual(pc.getFigure(), util.getCircle())) {
            valorP = 8 - (comprobarFilas(util.getEquis())
                    + comprobarColumnas(util.getEquis())
                    + comprobarDiagonales(util.getEquis()));

        } else {
            valorP = 8 - (comprobarFilas(util.getCircle())
                    + comprobarColumnas(util.getCircle())
                    + comprobarDiagonales(util.getCircle()));
        }
        return valorP;
    }

    /*
        los siguientes metodos retornan el numero que representa el numero de filas, columnas 
        y diagonales donde se encuentra esa figura
    */
    private int comprobarFilas(Image figure) {
        int contador1 = 0;
        int contador2 = 0;
        int contador3 = 0;
        for (int i = 0; i < gridpane.getChildren().size(); i++) { // todas las columnas, pero mas eficiente
            ImageView imageView = (ImageView) gridpane.getChildren().get(i);
            if (imageView.getId().equals("Cell1") || imageView.getId().equals("Cell2") || imageView.getId().equals("Cell3")) {
                if (util.isImageEqual(imageView.getImage(), figure)) {
                    contador1 = 1;
                }
            }
            if (imageView.getId().equals("Cell4") || imageView.getId().equals("Cell5") || imageView.getId().equals("Cell6")) {
                if (util.isImageEqual(imageView.getImage(), figure)) {
                    contador2 = 1;
                }
            }
            if (imageView.getId().equals("Cell7") || imageView.getId().equals("Cell8") || imageView.getId().equals("Cell9")) {
                if (util.isImageEqual(imageView.getImage(), figure)) {
                    contador3 = 1;
                }
            }
        }
        return contador1 + contador2 + contador3;
    }

    private int comprobarColumnas(Image figure) {
        int contador1 = 0;
        int contador2 = 0;
        int contador3 = 0;

        for (int i = 0; i < gridpane.getChildren().size(); i++) { // todas las columnas, pero mas eficiente
            ImageView imageView = (ImageView) gridpane.getChildren().get(i);
            if (imageView.getId().equals("Cell1") || imageView.getId().equals("Cell4") || imageView.getId().equals("Cell7")) {
                if (util.isImageEqual(imageView.getImage(), figure)) {
                    contador1 = 1;
                }
            }
            if (imageView.getId().equals("Cell2") || imageView.getId().equals("Cell5") || imageView.getId().equals("Cell8")) {
                if (util.isImageEqual(imageView.getImage(), figure)) {
                    contador2 = 1;
                }
            }
            if (imageView.getId().equals("Cell3") || imageView.getId().equals("Cell6") || imageView.getId().equals("Cell9")) {
                if (util.isImageEqual(imageView.getImage(), figure)) {
                    contador3 = 1;
                }
            }
        }
        return contador1 + contador2 + contador3;

    }

    private int comprobarDiagonales(Image figure) {
        int contador1 = 0;
        int contador2 = 0;
        for (int i = 0; i < gridpane.getChildren().size(); i++) { // las dos diagonales
            ImageView imageView = (ImageView) gridpane.getChildren().get(i);

            if (imageView.getId().equals("Cell1") || imageView.getId().equals("Cell5") || imageView.getId().equals("Cell9")) {
                if (util.isImageEqual(imageView.getImage(), figure)) {
                    contador1 = 1;
                }
            }
            if (imageView.getId().equals("Cell3") || imageView.getId().equals("Cell5") || imageView.getId().equals("Cell7")) {
                if (util.isImageEqual(imageView.getImage(), figure)) {
                    contador2 = 1;
                }
            }
        }
        return contador1 + contador2;
    }

}
