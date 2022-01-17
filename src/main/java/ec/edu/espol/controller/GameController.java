/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.controller;

import ec.edu.espol.Partida.Casilla;
import ec.edu.espol.Partida.Player;
import ec.edu.espol.Partida.Tablero;
import ec.edu.espol.TDAs.NodeTree;
import ec.edu.espol.TDAs.Tree;
import ec.edu.espol.TDAs.modoJuego;
import ec.edu.espol.grupo_07.App;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.ResourceBundle;
import java.util.Stack;
import java.util.TreeMap;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ec.edu.espol.grupo_07.App;

/**
 * FXML Controller class
 *
 * @author eDannX
 */
public class GameController implements Initializable {

    @FXML
    private GridPane tableroActual;  
    
    private Player pc1;
    private Player player1;
    private Tablero tablero;
    private boolean pcStarts;
    @FXML
    private Button cell1;
    @FXML
    private Button cell6;
    @FXML
    private Button cell5;
    @FXML
    private Button cell4;
    @FXML
    private Button cell3;
    @FXML
    private Button cell2;
    @FXML
    private Button cell8;
    @FXML
    private Button cell7;
    @FXML
    private Button cell9;
    private int modo;
    @FXML
    private Label UtilidadesTableros;
    @FXML
    private Button volverJugar;
    @FXML
    private Label mostartSubArboles;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
          FXMLLoader fxmlLoader= new FXMLLoader(App.class.getResource("menu.fxml"));
                        Parent root1;
                        try {
                            root1 = fxmlLoader.load();
                            MenuController nuevo=fxmlLoader.getController();
                            pc1=nuevo.getPc();
                            player1=nuevo.getPlayer();
                            pcStarts=nuevo.getPcStar();
                            modoJuego temp = nuevo.getModoJuego();
                            modo=temp.getModo();
                           
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        if(pcStarts&&modo==0) {
                            primerMovimientoPc();
                            Tablero tb2 = new Tablero(tableroActual,pc1,player1,pcStarts);
                            tb2.actualizarTablero(tb2, tableroActual,player1.getFigure());
                            tb2.setMovimientos();
                            tb2.generarCasillas();            
                            Tree<Tablero> arbolito2 = new Tree(tb2);
                            crearArbol(arbolito2);
                            MiniMax(arbolito2,player1.getFigure(),pc1.getFigure());
                            Tablero tablerop2 = playIA(arbolito2);////revisar
                            tablerop2.generarCasillas();

                            this.imprimirOpciones(arbolito2.getRoot().getChildren(), mostartSubArboles);
                            this.mostratRecomendacion(tablerop2.getCasillas(), UtilidadesTableros,player1.getFigure());
                            pcStarts=!pcStarts;
                        }if(modo==2&&pcStarts){
                            primerMovimientoPc();
                          pcStarts=!pcStarts;

                        }
                   
    }
    @FXML
    private void clickedB(MouseEvent event) {
        if(modo==0){
           // this.mostartSubArboles.setText(" ");
             this.UtilidadesTableros.setVisible(false);
            ((Button)event.getSource()).setText(player1.getFigure());
            ((Button)event.getSource()).setTextFill(Color.BLACK);
            ((Button)event.getSource()).setDisable(true); 
            ((Button)event.getSource()).setOpacity(1);
             this.mostartSubArboles.setText(" ");
          
            
            Tablero tb1 = new Tablero(tableroActual,player1,pc1,pcStarts);
            tb1.actualizarTablero(tb1, tableroActual,player1.getFigure());
            tb1.generarCasillas();            
            Tree<Tablero> arbolito = new Tree(tb1);
            crearArbol(arbolito);
            MiniMax(arbolito,pc1.getFigure(),player1.getFigure());
            Tablero tablerop = playIA(arbolito);////revisar/////
            movimientoMejor(tablerop,tableroActual,pcStarts);
              Tablero tb2 = new Tablero(tableroActual,pc1,player1,pcStarts);
            tb2.actualizarTablero(tb2, tableroActual,player1.getFigure());
            tb2.setMovimientos();
            tb2.generarCasillas();            
            Tree<Tablero> arbolito2 = new Tree(tb2);
            crearArbol(arbolito2);
            MiniMax(arbolito2,player1.getFigure(),pc1.getFigure());
            Tablero tablerop2 = playIA(arbolito2);////revisar
            tablerop2.generarCasillas();
            
            this.imprimirOpciones(arbolito2.getRoot().getChildren(), mostartSubArboles);
            this.mostratRecomendacion(tablerop2.getCasillas(), UtilidadesTableros,player1.getFigure());
            
    }else if(modo==1){
        if(pcStarts){
             this.mostartSubArboles.setText(" ");
            ((Button)event.getSource()).setText(pc1.getFigure());
            ((Button)event.getSource()).setTextFill(Color.BLACK);
            ((Button)event.getSource()).setDisable(true);  
             ((Button)event.getSource()).setOpacity(1);
             

            Tablero tb1 = new Tablero(tableroActual,pc1,player1,pcStarts);///Tablero Padre    
            tb1.actualizarTablero(tb1, tableroActual,player1.getFigure());
            pcStarts = !pcStarts;
            tb1.generarCasillas();
            Tree<Tablero> arbolito = new Tree(tb1);
            crearArbol(arbolito);
            MiniMax(arbolito,player1.getFigure(),pc1.getFigure());
            Tablero tablerop = playIA(arbolito);////revisar/////
            tablerop.generarCasillas();
            LinkedList<Casilla> listaa = tablerop.getCasillas();
            this.mostratRecomendacion(listaa, UtilidadesTableros,player1.getFigure());
            this.imprimirOpciones(arbolito.getRoot().getChildren(), mostartSubArboles);
            this.UtilidadesTableros.setVisible(false);
                
            }
        else{
            this.mostartSubArboles.setText(" ");
            pcStarts = !pcStarts;
            ((Button)event.getSource()).setText(player1.getFigure());
            ((Button)event.getSource()).setTextFill(Color.BLACK);
            ((Button)event.getSource()).setDisable(true);     
             ((Button)event.getSource()).setOpacity(1);
            Tablero tb1 = new Tablero(tableroActual,player1,pc1,pcStarts);
            tb1.actualizarTablero(tb1, tableroActual,player1.getFigure());
            tb1.generarCasillas();
             Tree<Tablero> arbolito = new Tree(tb1);
            crearArbol(arbolito);
            MiniMax(arbolito,pc1.getFigure(),player1.getFigure());
            Tablero tablerop = playIA(arbolito);////revisar/////
            tablerop.generarCasillas();
            LinkedList<Casilla> listaa = tablerop.getCasillas();
            this.mostratRecomendacion(listaa, UtilidadesTableros,pc1.getFigure());
            this.UtilidadesTableros.setVisible(false);
            this.imprimirOpciones(arbolito.getRoot().getChildren(), mostartSubArboles);
        }
    }else if(modo==2){       
        if(!pcStarts){
        this.mostartSubArboles.setText(" ");
        Tablero tb1 = new Tablero(tableroActual,pc1,player1,pcStarts);
            tb1.actualizarTablero(tb1, tableroActual,player1.getFigure());
            tb1.generarCasillas(); 
            tb1.setMovimientos();
            Tree<Tablero> arbolito = new Tree(tb1);
            crearArbol(arbolito);
            MiniMax(arbolito,player1.getFigure(),pc1.getFigure());
            Tablero tablerop = playIA(arbolito);////revisar
            movimientoMejor(tablerop,tableroActual,pcStarts);
            pcStarts=!pcStarts;
            this.imprimirOpciones(arbolito.getRoot().getChildren(), mostartSubArboles);
        }else{
            this.mostartSubArboles.setText(" ");
            Tablero tb2 = new Tablero(tableroActual,player1,pc1,pcStarts);
            tb2.actualizarTablero(tb2, tableroActual,player1.getFigure());
            tb2.setMovimientos();
            tb2.generarCasillas();            
            Tree<Tablero> arbolito2 = new Tree(tb2);
            crearArbol(arbolito2);
            MiniMax(arbolito2,pc1.getFigure(),player1.getFigure());
            Tablero tablerop2 = playIA(arbolito2);////revisar
            movimientoMejor(tablerop2,tableroActual,pcStarts);//mejor tablero
            pcStarts=!pcStarts;
            this.imprimirOpciones(arbolito2.getRoot().getChildren(), mostartSubArboles);


            
 
       
        }   
    }
    }
    
    
   

    public Player getPlayer() {
        return player1;
    }

    public void setPlayer(Player player) {
        this.player1 = player;
    }
   public Player getPc() {
        return pc1;
    }

    public void setPc(Player pc) {
        this.pc1 = pc;
    }
public Tree<Tablero> MiniMax(Tree<Tablero> arbol,String Max,String Min ){// recibe una lista de hijos
 
        LinkedList<Tree<Tablero>> lista = arbol.getRoot().getChildren();
    for(Tree<Tablero> hi : lista){
                 LinkedList<Tablero> subhijos = hi.getRoot().getContent().hijosIterativo();
                 int min = Integer.MAX_VALUE;
                 for(Tablero sub:subhijos){                    
                     sub.generarCasillas();
                     if(sub.comprobrarSiGano(Min)){
                         min=Integer.MIN_VALUE;
                        hi.getRoot().getContent().setUtilidad(min);
                     }                         
                     else{
                     int valor = sub.obtenerUtilidad(Max,Min);
                     min = (valor<min) ? valor:min;
                    hi.getRoot().getContent().setUtilidad(min);
                 }
            }              
    }
                         return arbol;
}

public Tree<Tablero> crearArbol(Tree<Tablero> tb1){
    LinkedList<Tablero> prueba = tb1.getRoot().getContent().hijosIterativo();
// Tablero Generados
    if(prueba.size()!=0){
            LinkedList<Tree<Tablero>> hijos= new LinkedList<>();
            for(Tablero r : prueba){
                r.generarCasillas();
                Tree temp = new Tree(r);
                hijos.add(new Tree(r)); 
                 
            }
            
            tb1.getRoot().setChildren(hijos);
            
    
         return tb1;   
    }else{
        return null;
    }
}
public Tablero playIA(Tree<Tablero> tb1){
    int puntero = Integer.MIN_VALUE;
    Tablero tbt = null;
    LinkedList<Tree<Tablero>> temp =tb1.getRoot().getChildren();
    for(Tree<Tablero> t:temp){
        puntero=(puntero>t.getRoot().getContent().getUtilidad()) ? puntero:t.getRoot().getContent().getUtilidad();
        tbt=(puntero>t.getRoot().getContent().getUtilidad()) ? tbt:t.getRoot().getContent();
    }
    return tbt;
}
public void movimientoMejor(Tablero tp1,GridPane gp,boolean turno){
        if(tp1.getMovimientos()!=0){
            ObservableList<Node> lista = tp1.getTablero().getChildren();
        ObservableList<Node> modificar = gp.getChildren();
       for(int x=0;x<9;x++){
           if(((Button)modificar.get(x)).getText()!=((Button)lista.get(x)).getText()){
           ((Button)modificar.get(x)).setText(((Button)lista.get(x)).getText());
           ((Button)modificar.get(x)).setTextFill(Color.BLACK);
            ((Button)modificar.get(x)).setDisable(true);  
            ((Button)modificar.get(x)).setOpacity(1);  

           }
       }}
        tp1.actualizarTablero(tp1, tableroActual,player1.getFigure());      

       turno=!turno;
    
}

    public List<Tablero> postorder(NodeTree root) {
        List<Tablero> list = new ArrayList<>();
        if (root == null) { return list; }
        Stack<NodeTree> s = new Stack<NodeTree>();
        s.push(root);
        NodeTree pre = null;
        while (!s.isEmpty()) {
            NodeTree node = s.pop();
            list.add((Tablero)node.getContent());
            for (Tree<Tablero> n : (LinkedList<Tree<Tablero>>)node.getChildren()) {
                s.push(n.getRoot());
            }
        }
        Collections.reverse(list);
        return list;
    }
    public void primerMovimientoPc(){             
            Tablero tb1 = new Tablero(tableroActual,player1,pc1,pcStarts);
            tb1.actualizarTablero(tb1, tableroActual,player1.getFigure());
            tb1.generarCasillas();            
            Tree<Tablero> arbolito = new Tree(tb1);
            crearArbol(arbolito);
            MiniMax(arbolito,pc1.getFigure(),player1.getFigure());
            Tablero tablerop = playIA(arbolito);
            tablerop.generarCasillas();
            tablerop.imprimirCasillas();
            movimientoMejor(tablerop,tableroActual,pcStarts);
    }

    @FXML
    private void imprimirUtilidad(MouseEvent event) {
        this.UtilidadesTableros.setVisible(true);
    }      

    @FXML
    private void volverAJugar(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Stage temp = new Stage();
        App var = new App();
        var.start(temp);
        
    }
    public void mostratRecomendacion( LinkedList<Casilla> var,Label label, String ayuda){
        String c1 = var.get(0).getBtn1().getText();
        String c2 = var.get(1).getBtn1().getText();
        String c3 = var.get(2).getBtn1().getText();
        String c4 = var.get(3).getBtn1().getText();
        String c5 = var.get(4).getBtn1().getText();
        String c6 = var.get(5).getBtn1().getText();
        String c7 = var.get(6).getBtn1().getText();
        String c8 = var.get(7).getBtn1().getText();
        String c9 = var.get(8).getBtn1().getText();
        String cadena = "Recomendacion para "+ayuda+":\n"
                + "[ "+ c1 + " | "+ c2+" | "+c3 +" \n"
                + c4 + " | "+ c5+" | "+c6 +" \n"
                + c7+ " | "+ c8+" | "+c9 +"]"
                ;
        label.setText(cadena);

        
        
    }

    @FXML
    private void imprimirArboles(MouseEvent event) {
        this.mostartSubArboles.setVisible(true);
    }
    private void imprimirOpciones(LinkedList<Tree<Tablero>> lista,Label l){
        int turno = 0;
    for(Tree<Tablero> t:lista){
        Tablero tab = t.getRoot().getContent();
        tab.generarCasillas();
        this.mostratRecomendacion(tab.getCasillas(),l,turno,tab.getUtilidad());
        turno++;
                        
                    }

    }
      public void mostratRecomendacion( LinkedList<Casilla> var,Label label, int ayuda,int utilidad){
        String c1 = var.get(0).getBtn1().getText();
        String c2 = var.get(1).getBtn1().getText();
        String c3 = var.get(2).getBtn1().getText();
        String c4 = var.get(3).getBtn1().getText();
        String c5 = var.get(4).getBtn1().getText();
        String c6 = var.get(5).getBtn1().getText();
        String c7 = var.get(6).getBtn1().getText();
        String c8 = var.get(7).getBtn1().getText();
        String c9 = var.get(8).getBtn1().getText();
        String cadena = "# tablero"+ayuda+":\n"
                + "[ "+ c1 + " | "+ c2+" | "+c3 +" \n"
                + c4 + " | "+ c5+" | "+c6 +" \n"
                + c7+ " | "+ c8+" | "+c9 +"]"+" \n"
                +"Utilidad-> "+utilidad;
                ;
        label.setText(label.getText()+" \n"+cadena);

        
        
    }
}







