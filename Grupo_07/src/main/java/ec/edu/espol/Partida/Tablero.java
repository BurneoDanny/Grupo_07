
package ec.edu.espol.Partida;

import ec.edu.espol.grupo_07.App;
import ec.edu.espol.util.util;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Tablero {
    private GridPane tablero;
    private int playerUtilidad;
    private int pcUtilidad;
    private Player player;
    private PC pc;
    private final Image circle = new Image(App.class.getResourceAsStream("images/circulo.png"));
    private final Image equis = new Image(App.class.getResourceAsStream("images/x.png"));

    public Tablero(GridPane tablero, Player player, PC pc) {
        this.tablero = tablero;
        this.player = player;
        this.pc = pc;
    }
     
    public GridPane getTablero(){
        return tablero;
    }
    
    public int obtenerValorPdePlayer(){ 
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
    
    public int obtenerValorPdePC(){
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
    
    private int comprobarFilas(Image figure){ 
        int contador1 = 0;
        int contador2 = 0;
        int contador3 = 0;    
        // los for podrian unirse. Es funcional pero es opcional
        for(int i = 0; i<3; i++){ // primera fila
            ImageView imageView = (ImageView)tablero.getChildren().get(i);
            if(util.isImageEqual(imageView.getImage(), figure)){
                contador1 += 1;
            }       
        }
        for(int j = 3; j< 6;j++){ // segunda fila
            ImageView imageView = (ImageView)tablero.getChildren().get(j);
            if(util.isImageEqual(imageView.getImage(), figure)){
                contador2 += 1;
            } 
        }
        for(int k = 6; k<9; k++){ // tercera fila
            ImageView imageView = (ImageView)tablero.getChildren().get(k);
            if(util.isImageEqual(imageView.getImage(), figure)){
                contador3 += 1;
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
    
    public int comprobarColumnas(Image figure){
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
