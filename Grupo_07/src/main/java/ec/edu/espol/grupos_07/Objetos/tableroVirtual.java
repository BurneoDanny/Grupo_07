/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.grupos_07.Objetos;

/**
 *
 * @author User
 */
public class tableroVirtual {
    public void IniciarJuego(String id){
        jugador jg1 = new jugador(id);
        jugador cmp = new jugador(jg1.getComputadora().toString());
    }
    
    
}
