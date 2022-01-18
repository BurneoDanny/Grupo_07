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
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.concurrent.Task;

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
    private static boolean pcStarts;
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
    @FXML
    private Label NombreGanador;
    private static Tablero tb1 ;
    private static Tablero tb2;
    private static modoJuego temp;
    private  int numeroTurnos=9;
    @FXML
    private Button pcvs;
    @FXML
    private Button cambioPc;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        this.volverJugar.setVisible(true);
        this.volverJugar.setDisable(false);
        this.cambioPc.setDisable(false);
        this.cambioPc.setVisible(true);
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
                            tb2.actualizarTablero(tb2, tableroActual,player1.getFigure(),this.NombreGanador);
                            tb2.setMovimientos();
                            tb2.setMovimientos();
                            tb2.generarCasillas();            
                            Tree<Tablero> arbolito2 = new Tree(tb2);
                            crearArbol(arbolito2);
                            MiniMax(arbolito2,player1.getFigure(),pc1.getFigure());
                            Tablero tablerop2 = playIA(arbolito2);////revisar
                            tablerop2.generarCasillas();
                            Tablero tb1 = new Tablero(tableroActual,player1,pc1,pcStarts);/// crea el tablero de la IA
                            setTurno();

                            this.imprimirOpciones(arbolito2.getRoot().getChildren(), mostartSubArboles);
                            this.mostratRecomendacion(tablerop2.getCasillas(), UtilidadesTableros,player1.getFigure());
                            pcStarts=!pcStarts;
                        }if(modo==2){
                            this.pcvs.setDisable(false);
                            this.pcvs.setVisible(true);
                            this.volverJugar.setVisible(false);
                            this.volverJugar.setDisable(true);
                            this.cambioPc.setDisable(true);
                            this.cambioPc.setVisible(false);
                        }
                            if(modo==2&&pcStarts){
                            this.pcvs.setDisable(false); 
                            this.pcvs.setVisible(true);
                            this.volverJugar.setVisible(false);
                            this.volverJugar.setDisable(true);
                            primerMovimientoPc();
                            pcStarts=!pcStarts;
                            this.setTurno();
                            this.cambioPc.setDisable(true);
                            this.cambioPc.setVisible(false);
                            
                            
               
              }
  }

                           
  

                               
                                   
                   
    
    
    
    @FXML
    private void clickedB(MouseEvent event) throws IOException {
        if(modo==0){
           // this.mostartSubArboles.setText(" ");
             this.UtilidadesTableros.setVisible(false);
            ((Button)event.getSource()).setText(player1.getFigure());
            ((Button)event.getSource()).setTextFill(Color.BLACK);
            ((Button)event.getSource()).setDisable(true); 
            ((Button)event.getSource()).setOpacity(1);
             this.mostartSubArboles.setText(" ");
             ///////////////////IA//////////////////////////////////////////
            /////////////////// Creacion de tablero//////////////////////////
            Tablero tb1 = new Tablero(tableroActual,player1,pc1,pcStarts);/// crea el tablero de la IA
              if(!tb1.comprobrarSiGano(player1.getFigure())){
             setTurno();
             setTurno();
            empate();
            tb1.actualizarTablero(tb1, tableroActual,player1.getFigure(),this.NombreGanador);
            tb1.generarCasillas();  
            /////////////////////////// Creacion de arbol 2 niveles////////////
            Tree<Tablero> arbolito = new Tree(tb1);  
            crearArbol(arbolito);
            MiniMax(arbolito,pc1.getFigure(),player1.getFigure());/// creacion de subhijos y cambio de utilidad
            Tablero tablerop = playIA(arbolito);////Seleccion de mejor Tablero
            movimientoMejor(tablerop,tableroActual,pcStarts);/// modificar el gridpane con base en el tablero elegido
            //////////////////////////////////////Jugador/////////////////////////////////////////
            Tablero tb2 = new Tablero(tableroActual,pc1,player1,pcStarts);//// creo el tablero para el Jugador 
            tb2.actualizarTablero(tb2, tableroActual,player1.getFigure(),this.NombreGanador);
            tb2.setMovimientos();
            tb2.generarCasillas();  
            Tree<Tablero> arbolito2 = new Tree(tb2);
            crearArbol(arbolito2);
            MiniMax(arbolito2,player1.getFigure(),pc1.getFigure());//Minimax para el jugador 
            Tablero tablerop2 = playIA(arbolito2);
            tablerop2.generarCasillas();
            this.imprimirOpciones(arbolito2.getRoot().getChildren(), mostartSubArboles);/// imprime en pantalla el mejor Tablero
            
            this.mostratRecomendacion(tablerop2.getCasillas(), UtilidadesTableros,player1.getFigure());////////////// muestra todos los tableros y sus utilidades
              } else{
                              tb1.actualizarTablero(tb1, tableroActual,player1.getFigure(),this.NombreGanador);
                  //this.NombreGanador.setText("Gano La Partida :  " +player1.getFigure() );
                  
                  
              }
    }else if(modo==1){
        if(pcStarts){
            this.mostartSubArboles.setText(" ");
            ((Button)event.getSource()).setText(pc1.getFigure());
            ((Button)event.getSource()).setTextFill(Color.BLACK);
            ((Button)event.getSource()).setDisable(true);  
             ((Button)event.getSource()).setOpacity(1);
            setTurno();
            empate();
            Tablero tb1 = new Tablero(tableroActual,pc1,player1,pcStarts);///Crea el Tablero para el jugador player1 
            tb1.actualizarTablero(tb1, tableroActual,player1.getFigure(),this.NombreGanador);
            pcStarts = !pcStarts;
            tb1.generarCasillas();
            Tree<Tablero> arbolito = new Tree(tb1);
            crearArbol(arbolito);
            MiniMax(arbolito,player1.getFigure(),pc1.getFigure());// minimax al jugador 1
            Tablero tablerop = playIA(arbolito);////revisar/////
            tablerop.generarCasillas();
            LinkedList<Casilla> listaa = tablerop.getCasillas();
            this.mostratRecomendacion(listaa, UtilidadesTableros,player1.getFigure());///////// recomendacion
            this.imprimirOpciones(arbolito.getRoot().getChildren(), mostartSubArboles);
            this.UtilidadesTableros.setVisible(false);
            

            
            }  
            
        else{
             ((Button)event.getSource()).setText(player1.getFigure());
            ((Button)event.getSource()).setTextFill(Color.BLACK);
            ((Button)event.getSource()).setDisable(true);     
             ((Button)event.getSource()).setOpacity(1);
            this.mostartSubArboles.setText(" ");
            pcStarts = !pcStarts;
            Tablero tb1 = new Tablero(tableroActual,player1,pc1,pcStarts);///Crea el Tablero para el jugador player2
            tb1.setMovimientos();
            setTurno();
            empate();
            tb1.actualizarTablero(tb1, tableroActual,player1.getFigure(),this.NombreGanador);
            tb1.generarCasillas();
             Tree<Tablero> arbolito = new Tree(tb1);
            crearArbol(arbolito);
            MiniMax(arbolito,pc1.getFigure(),player1.getFigure());//////minimax al jugador 2
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
         setTurno();
            empate();
            tb1.actualizarTablero(tb1, tableroActual,player1.getFigure(),this.NombreGanador);
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
             setTurno();
            empate();
            tb2.actualizarTablero(tb2, tableroActual,player1.getFigure(),this.NombreGanador);
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
    
    
/////////////MiniMax recibe un arbol y el simbolo maximizar y a minimizar //////////////////////////////////
public Tree<Tablero> MiniMax(Tree<Tablero> arbol,String Max,String Min ){// recibe al tablero principal asi como el caracter a maximizar y minimizar
     LinkedList<Tree<Tablero>> lista = arbol.getRoot().getChildren();/// obtiene hijos de la raiz
    for(Tree<Tablero> hi : lista){////////////recorre a los hijos

            LinkedList<Tablero> subhijos = hi.getRoot().getContent().hijosIterativo();
        ////////obtiene a los hijos de los hijos
                 int min = Integer.MAX_VALUE;/////////// puntero para minimizar
                 for(Tablero sub:subhijos){         ////////////recorre a los hijos de los hijos           
                     sub.generarCasillas(); ///////////// genera casillas para el mapeo del tablero
                     if(sub.comprobrarSiGano(Max)){//////////////comprueba si se gana 
                        hi.getRoot().getContent().setUtilidad(Integer.MAX_VALUE);
                     }
                     else if (sub.comprobrarSiGano(Min)){
                         min=Integer.MIN_VALUE;
                        hi.getRoot().getContent().setUtilidad(min);//////////// si gana el adversario se le asigna 
                         
                     }
                     else if (!sub.comprobrarSiGano(Min) && !sub.comprobrarSiGano(Max)){
                     int valor = sub.obtenerUtilidad(Max,Min);/////////// obtengo utilidad del hijo del hijo
                     min = (valor<min) ? valor:min;/////////// comparo los valores y guardo al menor
                    hi.getRoot().getContent().setUtilidad(min);///////////// cambio valor de utilidad
                 }
            }              
    
    
    
}
    return arbol;/// retorno el arbol

}
////////////------- Metodos Utilitarios---------------////////////////////////
public Tree<Tablero> crearArbol(Tree<Tablero> tb1){//// lleno a los hijos del arbol
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
        return tb1;
    }
}

public Tablero playIA(Tree<Tablero> tb1){//////////// recibe un tablero y escoge al hijo con el maximo valor
    if(tb1.getRoot().getChildren().size()!=0){
    int puntero = Integer.MIN_VALUE;
    Tablero tbt = null;///////// tablero a elegir
    LinkedList<Tree<Tablero>> temp =tb1.getRoot().getChildren();// obitne hijos
    for(Tree<Tablero> t:temp){/// recorre a los hijos
        puntero=(puntero>t.getRoot().getContent().getUtilidad()) ? puntero:t.getRoot().getContent().getUtilidad();/// actualiza el puntero
        tbt=(puntero>t.getRoot().getContent().getUtilidad()) ? tbt:t.getRoot().getContent();/// escoge al hijo con la maxima utilidad
    }
    return tbt;/// retorna el tablero con la utilidad maxima 
} return tb1.getRoot().getContent();
}
///////////// --------------- Modifica el GridPane -------------------- /////////////////
public void movimientoMejor(Tablero tp1,GridPane gp,boolean turno){
        if(tp1!=null){/// verifica que el tablero tenga movimientos /// que haya una celda vacia
        ObservableList<Node> lista = tp1.getTablero().getChildren();/// obtiene los nodos del GridPane del hijo;
        ObservableList<Node> modificar = gp.getChildren();//////////////// obtiene  los nodos del gridpane del padre
       for(int x=0;x<9;x++){//////////// rellena el gridpane con los nodos del hijo
           if(((Button)modificar.get(x)).getText()!=((Button)lista.get(x)).getText()){
           ((Button)modificar.get(x)).setText(((Button)lista.get(x)).getText());
           ((Button)modificar.get(x)).setTextFill(Color.BLACK);
            ((Button)modificar.get(x)).setDisable(true);  
            ((Button)modificar.get(x)).setOpacity(1);  

           }
       }        tp1.actualizarTablero(tp1, tableroActual,player1.getFigure(),this.NombreGanador);   
              turno=!turno;


        }

    
}
////////////// Realiza el primer Movimiento de la maquina ///////////////
    public void primerMovimientoPc(){             
            Tablero tb1 = new Tablero(tableroActual,player1,pc1,pcStarts);//// se crea tablero
            tb1.actualizarTablero(tb1, tableroActual,player1.getFigure(),this.NombreGanador);
            tb1.generarCasillas();            
            Tree<Tablero> arbolito = new Tree(tb1);
            crearArbol(arbolito);
            MiniMax(arbolito,pc1.getFigure(),player1.getFigure());/// se maximiza la utilidad para la pc
            Tablero tablerop = playIA(arbolito);
            tablerop.generarCasillas();
            tablerop.imprimirCasillas();
            movimientoMejor(tablerop,tableroActual,pcStarts);
    }

    
    
    
    
    
    /////////////////////Metodos de la interfaz //////////////
    @FXML
    private void imprimirUtilidad(MouseEvent event) {
        this.UtilidadesTableros.setVisible(true);
    }      

    @FXML
    private void volverAJugar(MouseEvent event) throws IOException {
        numeroTurnos=9;
          this.pcvs.setDisable(true);
         this.pcvs.setVisible(false);
        ObservableList<Node> celdas = this.tableroActual.getChildren();
        for(Node e:celdas){
            ((Button)e).setText("vacio");
             ((Button)e).setTextFill(Color.WHITE);            
             ((Button)e).setDisable(false);
             this.NombreGanador.setText(" ");

             
            
        }
        
    }
    
    /////////funcionalidad extra/////////////////
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

    /*
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
    }*/

    @FXML
    private void pantaInicio(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        Stage temp = new Stage();
        App var = new App();
        var.start(temp);
    }
    public void empate(){
       System.out.println(numeroTurnos);
        if(numeroTurnos==0||numeroTurnos==-1){
            this.NombreGanador.setText("EMPATARON!!!!!");
        }
        
    }
    public void setTurno(){
        numeroTurnos--;
    }
  

    @FXML
    private void pcvspc(MouseEvent event) {
            if(!pcStarts){
        this.mostartSubArboles.setText(" ");
        Tablero tb1 = new Tablero(tableroActual,pc1,player1,pcStarts);
         setTurno();
            empate();
            tb1.actualizarTablero(tb1, tableroActual,player1.getFigure(),this.NombreGanador);
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
             setTurno();
            empate();
            tb2.actualizarTablero(tb2, tableroActual,player1.getFigure(),this.NombreGanador);
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
    

    
  
           









