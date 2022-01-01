
package ec.edu.espol.Partida;

import javafx.scene.image.Image;




public class Player {
    /*
    debe tener de atributos a su utilidad dado un tablero
    su figura que se establece al inicio
    
    */  
    private Image figure;
    private int utilidad;

    public Player(Image figure) {
        this.figure = figure;
    }

    public Image getFigure() {
        return figure;
    }
    
    public int getUtilidad(){
        return utilidad;
    }
    
    
    
    
}
