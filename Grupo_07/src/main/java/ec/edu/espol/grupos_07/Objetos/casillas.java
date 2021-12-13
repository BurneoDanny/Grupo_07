/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.grupos_07.Objetos;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author User
 */
public class casillas {
    private Map<Integer[],Button> casillas = new TreeMap<>() {}; /// (Ubicacion)
    public void ubicarCasillas(GridPane panel){
      Iterator it =  casillas.keySet().iterator();
      while(it.hasNext()){
          Integer[]valor = (Integer[])it.next();
          Button temp = casillas.get(valor);
          panel.add(temp,valor[0], valor[1]);
      }
        
        
    }
}