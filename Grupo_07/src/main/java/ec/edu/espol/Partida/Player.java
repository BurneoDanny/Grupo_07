
package ec.edu.espol.Partida;


public class Player {
    /*
    debe tener de atributos a su utilidad dado un tablero
    su figura que se establece al inicio
    
    */
    String figure;
    int utilidad;

    public Player(String figure) {
        this.figure = figure;
    }

    public String getFigure() {
        return figure;
    }
    
    public int getUtilidad(){
        return utilidad;
    }
    
    
}
