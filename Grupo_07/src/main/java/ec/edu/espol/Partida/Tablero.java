
package ec.edu.espol.Partida;

import javafx.scene.layout.GridPane;

public class Tablero {
    /*
    tal vez deba tener dos utilidades, una del player y la otra del oponente
    sino simplemente esta clase debe ser una representacion del grid que se ponga en scene
    debe tener un numero asignado para cada celda y debe saber que figura hay en cada celda
    
    
    */
    private GridPane tablero;
    private int playerUtilidad;
    private int pcUtilidad;
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
    
    public int obtenerPlayerUtilidad(){
        // metodo que verifique las filas dado un index y un icono!!
        // metodo que verifique las columnas dado un index y un icono!!
        // metodo que verifique la diagonal principal y la secundaria , dado un icono
        
    }
    
    public int obtenerPCUtilidad(){
        
        
    }
    
    
    
    
    
}
