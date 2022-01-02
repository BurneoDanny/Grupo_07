
package ec.edu.espol.Partida;

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
    private GridPane tablero;
    private Player player;
    private PC pc;

    public Tablero(GridPane tablero, Player player, PC pc) {
        this.tablero = tablero;
        this.player = player;
        this.pc = pc;
    }
     
    public GridPane getTablero(){
        return tablero;
    }
       
    // retorna el spot/celda donde debe poner la pc su figura 
    public void bestSpot(){ 
        int bestUtilidad = -10000;
        ArrayList<ImageView> cells = emptyCells();
        ImageView bestCell = new ImageView();
        //LinkedList<Tree<GridPane>> hijos = new LinkedList<>();
        for(ImageView cell : cells){
            cell.setImage(pc.getFigure());
            if(comprobacion() == 10 || comprobacion() == -10 || comprobacion() == 0){
                bestUtilidad = comprobacion();
                cell.setImage(null);
                bestCell.setId(cell.getId());
            }
            else{
                int utilidad = miniMax(); 
                System.out.println("Impresion de todas las utilidades: " + utilidad);
                //hijos.add(new Tree(tablero));
                cell.setImage(null);
                if (utilidad > bestUtilidad) { // Elegimos la mayor utilidad.
                    bestUtilidad = utilidad;
                    bestCell.setId(cell.getId());
                }
            }      
        }
        System.out.println("Best utilidad que se uso: " + bestUtilidad);
        //if(bestUtilidad != 10 && bestUtilidad != -10 && bestUtilidad != 0){
            // Ponemos la figura en la celda mas eficiente
            ObservableList<Node> childrens = tablero.getChildren();   
            for (Node node : childrens) {
                ImageView nodo = (ImageView)node;
                if(nodo.getId().equals(bestCell.getId())) {
                    nodo.setImage(pc.getFigure());
                    System.out.println("PC PUSO FIGURA");
                    System.out.println("----------------------");
                }
            }
        //}  
        //tableros.getRoot().setChildren(hijos);
    }
    
    // retorna un integer que nos dice cual moviento deberia hacer la pc.
    private int miniMax(){
        //Tree<GridPane> tableros = new Tree(tablero);
        ArrayList<ImageView> cells = emptyCells();
        
        int bestUtilidad = 10000;
        for(ImageView cell : cells){
            cell.setImage(player.getFigure());
            if(comprobacion() == 10 || comprobacion() == -10 || comprobacion() == 0){
                bestUtilidad = comprobacion();
                cell.setImage(null);
            }
            else{
                int utilidad = getPCUtilidad();
                cell.setImage(null);
                bestUtilidad = Math.min(utilidad, bestUtilidad);
            }
        } 
        return bestUtilidad;
    }
    
    
    public int comprobacion(){
        ArrayList<ImageView> cells = emptyCells();
        ImageView result = checkWinner();
        if(result != null){
            if(util.isImageEqual(result.getImage(), player.getFigure())){
                return -10; // gana player
            }
            else if(util.isImageEqual(result.getImage(), pc.getFigure())){
                return 10; //gane pc
            }
        }
        else{
            if(cells.isEmpty()){
                return 0; // empate
            }
            return 1;
        } 
        System.out.println("se ejecuta");
        return 0; // ojoooooo    
    }
    

    // obtiene un Nodo por index de columna y fila  
    private ImageView getNodeByRowColumnIndex (final int row, final int column) {
        ImageView result = null;
        ObservableList<Node> childrens = tablero.getChildren();   
        for (Node node : childrens) {
            ImageView nodo = (ImageView)node;
            if(GridPane.getRowIndex(nodo) == row && GridPane.getColumnIndex(nodo) == column) {
                result = nodo;
                break;
            }
        }
        return result;
    }
    
    // comprueba si 3 spots/celdas/imagesView son iguales  
    private boolean isEqual(Image a, Image b, Image c) {
        return util.isImageEqual(a, b) && util.isImageEqual(b, c) && a != null;
    }
    
    //Retorna la imageView/figura de quien gano, ya se diagonalmente, horizontalmente o verticalmente. 
    //OJO retorna null si hay empate o si nadie ha ganado todavia. 
    public ImageView checkWinner(){
        ImageView winnerFigure = null;
        // horizontal
        for (int i = 0; i < 3; i++) {
            if (isEqual(getNodeByRowColumnIndex(i,0).getImage(), 
                    getNodeByRowColumnIndex(i,1).getImage(), 
                    getNodeByRowColumnIndex(i,2).getImage())){
                winnerFigure = getNodeByRowColumnIndex(i,0);
            }
        }
        
        // vertical
        for (int i = 0; i < 3; i++) {
            if (isEqual(getNodeByRowColumnIndex(0,i).getImage(), 
                    getNodeByRowColumnIndex(1,i).getImage(), 
                    getNodeByRowColumnIndex(2,i).getImage())){
                winnerFigure = getNodeByRowColumnIndex(0,i);
            }
        }    
         // Diagonal
        if (isEqual(getNodeByRowColumnIndex(0,0).getImage(), 
                    getNodeByRowColumnIndex(1,1).getImage(), 
                    getNodeByRowColumnIndex(2,2).getImage())){
            winnerFigure = getNodeByRowColumnIndex(0,0);
        }
        if (isEqual(getNodeByRowColumnIndex(2,0).getImage(), 
                    getNodeByRowColumnIndex(1,1).getImage(), 
                    getNodeByRowColumnIndex(0,2).getImage())){
            winnerFigure = getNodeByRowColumnIndex(2,0);
        }
              
        return winnerFigure;
    }
    
    // retorna los ImageView de todas las celdas vacias
    public ArrayList<ImageView> emptyCells(){ 
        ArrayList<ImageView> emptyCells = new ArrayList<>();
        for(int i = 0; i<tablero.getChildren().size(); i++){
            ImageView imageView = (ImageView)tablero.getChildren().get(i);
            if(util.isThereImage(imageView.getImage()) == false){
                emptyCells.add(imageView);
            }  
        }
        return emptyCells;
    }
    
    
    
    
    
    // metodo que obtiene la utilidad del pc   
    public int getPCUtilidad(){ 
        return obtenerValorPdePC() - obtenerValorPdePlayer();
    }
    
    // los siguientes metodos retornan los valor P del player y la pc, necesarios para calcular la utilidad.
    private int obtenerValorPdePlayer(){ 
        int valorP;
        if(util.isImageEqual(player.getFigure(), util.getCircle())){
            valorP = 8 - (comprobarFilas(util.getEquis()) 
                    + comprobarColumnas(util.getEquis()) 
                    + comprobarDiagonales(util.getEquis()));
            
        }
        else{
            valorP = 8 - (comprobarFilas(util.getCircle()) 
                    + comprobarColumnas(util.getCircle()) 
                    + comprobarDiagonales(util.getCircle()));      
        } 
        return valorP;
    }
    
    private int obtenerValorPdePC(){
        int valorP;
        if(util.isImageEqual(pc.getFigure(), util.getCircle())){
            valorP = 8 - (comprobarFilas(util.getEquis()) 
                    + comprobarColumnas(util.getEquis()) 
                    + comprobarDiagonales(util.getEquis()));
            
        }
        else{
            valorP = 8 - (comprobarFilas(util.getCircle()) 
                    + comprobarColumnas(util.getCircle()) 
                    + comprobarDiagonales(util.getCircle()));      
        } 
        return valorP;   
    }
    
    // los siguientes metodos retornan un numero que representa el numero de filas, columnas 
    // y diagonales donde se encuentra esa figura
    private int comprobarFilas(Image figure){ 
        int contador1 = 0;
        int contador2 = 0;
        int contador3 = 0;    
        for(int i = 0; i<tablero.getChildren().size(); i++){ // todas las columnas, pero mas eficiente
            ImageView imageView = (ImageView)tablero.getChildren().get(i);
            if(imageView.getId().equals("Cell1") || imageView.getId().equals("Cell2") || imageView.getId().equals("Cell3")){
                if(util.isImageEqual(imageView.getImage(), figure)){
                    contador1 += 1;
                } 
            }
            if(imageView.getId().equals("Cell4") || imageView.getId().equals("Cell5") || imageView.getId().equals("Cell6")){
                if(util.isImageEqual(imageView.getImage(), figure)){
                    contador2 += 1;
                } 
            } 
            if(imageView.getId().equals("Cell7") || imageView.getId().equals("Cell8") || imageView.getId().equals("Cell9")){
                if(util.isImageEqual(imageView.getImage(), figure)){
                    contador3 += 1;
                } 
            } 
        }
        if(contador1 > 0){
            contador1 = 1; 
        }
        if(contador2 > 0){
            contador2 = 1;
        }
        if(contador3 > 0){
            contador3 = 1;
        }
        return contador1 + contador2 + contador3;
    }
    
    private int comprobarColumnas(Image figure){
        int contador1 = 0;
        int contador2 = 0;
        int contador3 = 0;
        for(int i = 0; i<tablero.getChildren().size(); i++){ // todas las columnas, pero mas eficiente
            ImageView imageView = (ImageView)tablero.getChildren().get(i);
            if(imageView.getId().equals("Cell1") || imageView.getId().equals("Cell4") || imageView.getId().equals("Cell7")){
                if(util.isImageEqual(imageView.getImage(), figure)){
                    contador1 += 1;
                } 
            }
            if(imageView.getId().equals("Cell2") || imageView.getId().equals("Cell5") || imageView.getId().equals("Cell8")){
                if(util.isImageEqual(imageView.getImage(), figure)){
                    contador2 += 1;
                } 
            } 
            if(imageView.getId().equals("Cell3") || imageView.getId().equals("Cell6") || imageView.getId().equals("Cell9")){
                if(util.isImageEqual(imageView.getImage(), figure)){
                    contador3 += 1;
                } 
            } 
        }
        if(contador1 > 0){
            contador1 = 1; 
        }
        if(contador2 > 0){
            contador2 = 1;
        }
        if(contador3 > 0){
            contador3 = 1;
        }
        return contador1 + contador2 + contador3;
    
    
    }
    
    private int comprobarDiagonales(Image figure){
        int contador1 = 0;
        int contador2 = 0;
        for(int i = 0; i<tablero.getChildren().size(); i++){ // las dos diagonales
            ImageView imageView = (ImageView)tablero.getChildren().get(i);
            if(imageView.getId().equals("Cell1") || imageView.getId().equals("Cell5") || imageView.getId().equals("Cell9")){
                if(util.isImageEqual(imageView.getImage(), figure)){
                    contador1 += 1;
                } 
            }
            if(imageView.getId().equals("Cell3") || imageView.getId().equals("Cell5") || imageView.getId().equals("Cell7")){
                if(util.isImageEqual(imageView.getImage(), figure)){
                    contador2 += 1;
                } 
            }      
        }
        if(contador1 > 0){
            contador1 = 1; 
        }
        if(contador2 > 0){
            contador2 = 1;
        }
        return contador1 + contador2;  
    }
    
    
    
    
    
}
