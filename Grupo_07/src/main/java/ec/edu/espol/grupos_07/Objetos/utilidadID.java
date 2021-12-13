/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.grupos_07.Objetos;

import java.util.ArrayList;
import java.util.Map;
import javafx.scene.control.Button;

/**
 *
 * @author User
 */
public interface utilidadID {
    int obtenerUtilidad(Map tree );
    int horizontal(ArrayList<ArrayList<Button>> lista);
    int diagonal(ArrayList<ArrayList<Button>> lista);
    int vertical(ArrayList<ArrayList<Button>> lista);   
}
