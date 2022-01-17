
package ec.edu.espol.Partida;

import javafx.scene.image.Image;




public class Player {
    /*
    debe tener de atributos a su utilidad dado un tablero
    su figura que se establece al inicio
    
    */  
    private String figure;
    private int utilidad;
    public Player(){
        this.figure="kk";
    }
    public Player(String figure) {
        this.figure = figure;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    }
    
    public int getUtilidad(){
        return utilidad;
    }
    
    
    
    
}
