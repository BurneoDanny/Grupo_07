
package ec.edu.espol.Partida;

import ec.edu.espol.TDAs.NodeTree;
import ec.edu.espol.TDAs.Tree;
import ec.edu.espol.grupo_07.App;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Tablero {
    private GridPane tablero;
    private  Player player;
    private  Player pc;
    private LinkedList<Casilla> casillas = new LinkedList<>();//IMPORTANTE 
    private static int movimientosDisponibles=9;
    private int utilidad;
    private boolean pcTurno;
    public Tablero(GridPane tablero, Player player, Player pc,boolean turno) {
        this.tablero = tablero;
        this.player = player;
        this.pc = pc;
        this.pcTurno=turno;
        generarCasillas();
    }
   
    
    public int movimientosDisponibles(){
        return movimientosDisponibles;
    }
 
    public GridPane getTablero(){
        return tablero;
    }
    
     public int obtenerUtilidad(String x,String y){/// permite obtener Utilidades
         int valoresMax=utilidadFilas(x)+ utilidadColumnas(x) + utilidadDiagonal(x);
         int valoresMin = utilidadFilas(y)+ utilidadColumnas(y) + utilidadDiagonal(y);    
        return valoresMax-valoresMin;
        
    }
     
      public void actualizarTablero(Tablero tb1,GridPane tableroActual,String valor,Label gan){/// actualiza el tablero
        if(tb1.comprobrarSiGano(tb1.player.getFigure())){/////////// comprueba gano el jugador              
                gan.setText("Gano La Partida : "+tb1.player.getFigure());
                ObservableList<Node> actualizar = tableroActual.getChildren();
                for(Node i : actualizar){ /// deshabilita los otros botones y termina la partida
                    if(!((Button)i).isDisable())
                    ((Button)i).setDisable(true);
                    ((Button)i).setOpacity(1);

                }
             }else {
                 if(movimientosDisponibles==0){
                 }         
        generarCasillas();////////// crea el objeto casillas mapeando toda la informacion
      }
      }
  
          
     
   ///----------Metodos Utilitarios -------------------//////////  
      
    
     public boolean casillaOcupada(int indice){/// comprueba si una casilla esta ocupada
         return ((Button)tablero.getChildren().get(indice)).getText()!="vacio";
     }
     public boolean comprobrarSiGano(String x){ // realiza la verificacion en columnas , filas y diagonales
     return verificar(obtenerFilas(),x)||verificar(obtenerColumnas(),x)||verificar(obtenerDiagonales(),x) ;}
     public boolean verificar(LinkedList<Button[]> temp,String x){/// comprueba si en una lista de [] se repite el valor 3 veces
          int ganar = 3;
         int contador=0;
         for(Button[] a : temp){
             for(Button i : a){
                 if(i.getText()==x){
                     contador++;
                 }if(contador==3){
                     return true;
                 }
             }
            contador=0;               
         }
        return false; 
         
     }

    public boolean casillaValida(String x , Button btn){/// comprueba si una casilla es valida para contar las utilidades
        String valor = (x=="X")? "O":"X";
        return btn.getText()!=valor || btn.getText()==x;   
    }
   
    public void generarCasillas(){////////// actualiza el objeto casillas con base en el gridpane recibido
     casillas.clear();
     ObservableList<Node> v1=tablero.getChildren();
     casillas.add(new Casilla((Button)v1.get(0),0,0));
     casillas.add(new Casilla((Button)v1.get(1),1,0));
     casillas.add(new Casilla((Button)v1.get(2),2,0));
     casillas.add(new Casilla((Button)v1.get(3),0,1));
     casillas.add(new Casilla((Button)v1.get(4),1,1));
     casillas.add(new Casilla((Button)v1.get(5),2,1));
     casillas.add(new Casilla((Button)v1.get(6),0,2));
     casillas.add(new Casilla((Button)v1.get(7),1,2));
     casillas.add(new Casilla((Button)v1.get(8),2,2));
    }
    
   
    
    
    
    
    
    
    
    
    
    
    
    /////////////////------------Metodos Iterativo Obtener Hijos-------------------////////////
    public LinkedList<Tablero> hijosIterativo(){
        ////////////llamada iterativa ////////////
        LinkedList<Tablero> hijos = new LinkedList<>();// llama al metodo ObtenerTablerosFuturos y realiza el reocrrido del getChildren
        for(int filas=0;filas<9;filas++){ ////////////// 
                if(obtenerTableroFuturos(filas)!=null){///////////// si el tablero es nulo , no obtiene a los hijos
                    hijos.add(obtenerTableroFuturos(filas));                                
                }
            
    }
        return hijos;
    }

    public Tablero obtenerTableroFuturos(int indice){//////////// recibe un indice y añade una marca dependiendo del momento ha ser llamado
        GridPane board = new GridPane();
        this.llenarTablero(board);
        ObservableList<Node> modificar = board.getChildren();
        String min = (!pcTurno) ? pc.getFigure():player.getFigure();/////////eligue que marca usara para llenar el GridPane
        Tablero temp = new Tablero(board,this.pc,this.player,pcTurno);//////////// crea tablero temporal
        if(!((Button)modificar.get(indice)).isDisable()&&((Button)modificar.get(indice)).getText()!="X"&&((Button)modificar.get(indice)).getText()!="O"){
            modificar.set(indice, (Node)new Button(pc.getFigure()));///AÑADE EN UN ESPACIO VACIO EL CARACTER
              return temp;           
        }
        return null;////////// si la celda esta ocupada devuelve null

   
        }
    
    /////////////----------Metodos Utilitarios-----------------------//////////////
    
    public void llenarTablero(GridPane pane){/// llena el gridpane en base a las casillas del tablero 
        this.generarCasillas();
        LinkedList<Button[]> temp = new LinkedList<>();
        Button[] fila1 = new Button[3];
        fila1[0]=casillas.get(0).getBtn1();
        fila1[1]=casillas.get(1).getBtn1();
        fila1[2]=casillas.get(2).getBtn1();      
        Button[] fila2 = new Button[3];
        fila2[0]=casillas.get(3).getBtn1();
        fila2[1]=casillas.get(4).getBtn1();
        fila2[2]=casillas.get(5).getBtn1();    
        Button[] fila3 = new Button[3];
        fila3[0]=casillas.get(6).getBtn1();
        fila3[1]=casillas.get(7).getBtn1();
        fila3[2]=casillas.get(8).getBtn1();
        temp.add(fila1);temp.add(fila2);temp.add(fila3);   
        for(int filas=0;filas<3;filas++){
            for(int col =0;col<3;col++){
                pane.add((Node)new Button(temp.get(filas)[col].getText()), filas, col);
                
            }
        }
        
    }
    public LinkedList<Button[]> obtenerFilas(){
         LinkedList<Button[]> temp = new LinkedList<>();
        Button[] fila1 = new Button[3];
        fila1[0]=casillas.get(0).getBtn1();
        fila1[1]=casillas.get(1).getBtn1();
        fila1[2]=casillas.get(2).getBtn1();      
        Button[] fila2 = new Button[3];
        fila2[0]=casillas.get(3).getBtn1();
        fila2[1]=casillas.get(4).getBtn1();
        fila2[2]=casillas.get(5).getBtn1();    
        Button[] fila3 = new Button[3];
        fila3[0]=casillas.get(6).getBtn1();
        fila3[1]=casillas.get(7).getBtn1();
        fila3[2]=casillas.get(8).getBtn1();
        temp.add(fila1);temp.add(fila2);temp.add(fila3);
        return temp;
        
    }
     public LinkedList<Button[]> obtenerColumnas(){
         LinkedList<Button[]> temp = new LinkedList<>();
        Button[] fila1 = new Button[3];
        fila1[0]=casillas.get(0).getBtn1();
        fila1[1]=casillas.get(3).getBtn1();
        fila1[2]=casillas.get(6).getBtn1();      
        Button[] fila2 = new Button[3];
        fila2[0]=casillas.get(1).getBtn1();
        fila2[1]=casillas.get(4).getBtn1();
        fila2[2]=casillas.get(7).getBtn1();    
        Button[] fila3 = new Button[3];
        fila3[0]=casillas.get(2).getBtn1();
        fila3[1]=casillas.get(5).getBtn1();
        fila3[2]=casillas.get(8).getBtn1();
        temp.add(fila1);temp.add(fila2);temp.add(fila3);
        return temp;
        
    }
      public LinkedList<Button[]> obtenerDiagonales(){
            LinkedList<Button[]> temp = new LinkedList<>();
            Button[] fila1 = new Button[3];
            fila1[0]=new Button(casillas.get(0).getBtn1().getText());
            fila1[1]=new Button(casillas.get(4).getBtn1().getText());
            fila1[2]=new Button(casillas.get(8).getBtn1().getText());      
            Button[] fila2 = new Button[3];
            fila2[0]= new Button(casillas.get(2).getBtn1().getText());
            fila2[1]=new Button(casillas.get(4).getBtn1().getText());
            fila2[2]=new Button(casillas.get(6).getBtn1().getText());    
            temp.add(fila1);temp.add(fila2);
            return temp;
    }    
      
      
      public int getMovimientos(){
        return movimientosDisponibles;
    }
    public int getUtilidad(){
        return utilidad;
    }
    public void setUtilidad(int valor){
        this.utilidad=valor;
    }
    public void setMovimientos(){
        this.movimientosDisponibles--;
    }
    public LinkedList<Casilla> getCasillas(){
        return casillas;
    }
 public void imprimirCasillas(){
        for(Casilla a : casillas){
            System.out.println(a.getBtn1().getText());
            
        }
    }
    public ObservableList<Node>  obtenerCasillas(){
        ObservableList<Node> temp = tablero.getChildren();
        temp.clear();
        for(Casilla a : casillas){
            temp.add((Node)(a.getBtn1()));
        }
        
        return temp;
    }
    
         public int utilidadColumnas(String x){
        int contador =0;
        LinkedList<Button[]> temp = this.obtenerColumnas();
        for(Button[] b : temp){
            Button f1 = b[0];Button f2 =b[1];Button f3 =b[2];
            if(casillaValida(x,f1)&&casillaValida(x,f2)&&casillaValida(x,f3)){
                contador++;    
            }
        }
        return contador;
    }
    public int utilidadDiagonal(String x){
        int contador =0;
        LinkedList<Button[]> temp = obtenerDiagonales();
        for(Button[] b : temp){
            Button f1 = b[0];Button f2 =b[1];Button f3 =b[2];
            if(casillaValida(x,f1)&&casillaValida(x,f2)&&casillaValida(x,f3)){
                contador++;    
            }
        }
        return contador;
    }
     public int utilidadFilas(String x){
        int contador =0;
        LinkedList<Button[]> temp = this.obtenerFilas();
        for(Button[] b : temp){
            Button f1 = b[0];Button f2 =b[1];Button f3 =b[2];
            if(casillaValida(x,f1)&&casillaValida(x,f2)&&casillaValida(x,f3)){
                contador++;    
            }
        }
        return contador;
    }
}
