
package ec.edu.espol.Partida;

import ec.edu.espol.TDAs.Tree;
import ec.edu.espol.util.util;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;


public class TableroPCVPC {
    private GridPane gridpane;
    private PC pc;
    private PC pcOponente;
    private int utilidad;
    private ImageView newCelda;

    public TableroPCVPC(GridPane gridpane, PC pc, PC pcOponente) {
        this.gridpane = gridpane;
        this.pc = pc;
        this.pcOponente = pcOponente;
    }
    

    public int getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    }
     
    public ImageView getNewCelda() {
        return newCelda;
    }

    public void setNewCelda(ImageView newCelda) {
        this.newCelda = newCelda;
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
        Tree<TableroPCVPC> mainTree = createTree();
        int mayorUtilidad = -10000;
        TableroPCVPC bestTablero = new TableroPCVPC(this.gridpane, this.pc, this.pcOponente);
        for (Tree<TableroPCVPC> hijo : mainTree.getRoot().getChildren()) {
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

    private int getMinUtilidad(Tree<TableroPCVPC> hijo) {
        int utilidadMinima = 10000;
        for (Tree<TableroPCVPC> arbolHoja : hijo.getRoot().getChildren()) {
            int utilidadTablero = arbolHoja.getRoot().getContent().getUtilidad();
            utilidadMinima = Math.min(utilidadMinima, utilidadTablero);
        }
        return utilidadMinima;
    }

    private Tree<TableroPCVPC> createTree() {
        Tree<TableroPCVPC> mainTree = new Tree(this);
        for (ImageView celda : emptyCells()) {
            celda.setImage(pc.getFigure());
            TableroPCVPC tableroHijo = new TableroPCVPC(this.gridpane, this.pc, this.pcOponente);
            tableroHijo.setNewCelda(celda);
            Tree<TableroPCVPC> hijo = new Tree(tableroHijo);
            mainTree.getRoot().getChildren().add(hijo);
            if (isThereWinner()) {
                tableroHijo.setUtilidad(10);
                celda.setImage(null);
            } 
            else {
                for (ImageView cell : emptyCells()) {
                    cell.setImage(pcOponente.getFigure());
                    TableroPCVPC tableroHoja = new TableroPCVPC(this.gridpane, this.pc, this.pcOponente);
                    Tree<TableroPCVPC> arbolHoja = new Tree(tableroHoja);
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


    public boolean isThereWinner() {
        return returnWinnerInteger() == 10 || returnWinnerInteger() == -10;
    }

   
    public int returnWinnerInteger() {
        ImageView result = returnWinnerFigure();
        if (result != null) {
            if (util.isImageEqual(result.getImage(), pcOponente.getFigure())) {
                return -10; // gana player
            } else if (util.isImageEqual(result.getImage(), pc.getFigure())) {
                return 10; //gane pc
            }
        }
        if (isThereEmpate()) {
            return 0;
        }
        return 1;
    }

   
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

   
    public boolean isThereEmpate() {
        return returnWinnerFigure() == null && emptyCells().isEmpty();
    }

    
    public int getPCUtilidad() {
        return obtenerValorPdePC() - obtenerValorPdePCOponente();
    }


  
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
   
    private int obtenerValorPdePCOponente() {
        int valorP;
        if (util.isImageEqual(pcOponente.getFigure(), util.getCircle())) {
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

   
    private int comprobarFilas(Image figure) {
        int contador1 = 0;
        int contador2 = 0;
        int contador3 = 0;
        for (int i = 0; i < gridpane.getChildren().size(); i++) { // todas las columnas, pero mas eficiente
            ImageView imageView = (ImageView) gridpane.getChildren().get(i);
            if (imageView.getId().equals("celda1") || imageView.getId().equals("celda2") || imageView.getId().equals("celda3")) {
                if (util.isImageEqual(imageView.getImage(), figure)) {
                    contador1 = 1;
                }
            }
            if (imageView.getId().equals("celda4") || imageView.getId().equals("celda5") || imageView.getId().equals("celda6")) {
                if (util.isImageEqual(imageView.getImage(), figure)) {
                    contador2 = 1;
                }
            }
            if (imageView.getId().equals("celda7") || imageView.getId().equals("celda8") || imageView.getId().equals("celda9")) {
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
            if (imageView.getId().equals("celda1") || imageView.getId().equals("celda4") || imageView.getId().equals("celda7")) {
                if (util.isImageEqual(imageView.getImage(), figure)) {
                    contador1 = 1;
                }
            }
            if (imageView.getId().equals("celda2") || imageView.getId().equals("celda5") || imageView.getId().equals("celda8")) {
                if (util.isImageEqual(imageView.getImage(), figure)) {
                    contador2 = 1;
                }
            }
            if (imageView.getId().equals("celda3") || imageView.getId().equals("celda6") || imageView.getId().equals("celda9")) {
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

            if (imageView.getId().equals("celda1") || imageView.getId().equals("celda5") || imageView.getId().equals("celda9")) {
                if (util.isImageEqual(imageView.getImage(), figure)) {
                    contador1 = 1;
                }
            }
            if (imageView.getId().equals("celda3") || imageView.getId().equals("celda5") || imageView.getId().equals("celda7")) {
                if (util.isImageEqual(imageView.getImage(), figure)) {
                    contador2 = 1;
                }
            }
        }
        return contador1 + contador2;
    }
     
 
    
    
    
}
