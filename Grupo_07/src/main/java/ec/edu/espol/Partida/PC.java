
package ec.edu.espol.Partida;

import javafx.scene.image.Image;



public class PC {
    /*
    debe tener los sig atributos
    su utilidad dado un tablero
    su figura que se establece al inicio
    
    */
    private Image figure;
    private int utilidad;

    public PC(Image figure) {
        this.figure = figure;
    }
    
    

    public void setFigure(Image figure) {
        this.figure = figure;
    }
       

    public Image getFigure() {
        return figure;
    }


    
    
    
    
    
    
    
}
