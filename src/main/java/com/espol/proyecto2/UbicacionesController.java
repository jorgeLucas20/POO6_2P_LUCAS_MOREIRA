/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.proyecto2;

import com.espol.proyecto2.Modelo.Archivo;
import com.espol.proyecto2.Modelo.Coordenada;
import com.espol.proyecto2.Modelo.Local;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class UbicacionesController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    
    @FXML
    Pane paneMapa;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        App.listaLocales = new ArrayList<>();
        
        try ( FileInputStream input = new FileInputStream(App.pathImg + "mapa.png")) {
            System.out.println(input.toString());
            Image im = new Image(input); 
            
            //EJEMPLO PARA PONER UNA IMAGEN COMO FONDO. puede ser para  un control o un contenedor que tenga el metodo setBackground
            BackgroundImage backgroundImage = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            Background background = new Background(backgroundImage);
            paneMapa.setBackground(background);
        } 
        catch (Exception e) {
                System.out.println("No se encuentra la imagen");
                System.out.println(e.getMessage());
                System.out.println(Arrays.toString(e.getStackTrace()));
        }
        
        leerSucursales();
        //CADA LOCAL SE LO PONE EN EL MAPA Y SE LO CONFIGURA
        for(Local sucursal: App.listaLocales){
                Thread t1 = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            int aleatorio =(int)Coordenada.numeroAleatorio();
                            Thread.sleep(aleatorio*1000);
                            System.out.println("Aleatorio: "+Integer.valueOf(aleatorio));
                            ponerPinesUbicacionProgresivamenteThread(sucursal);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                t1.start();
                
        }
        
    }    
    private void ponerPin(Local sucursal){
        try(FileInputStream input = new FileInputStream(App.pathImg+"laboratorioIcono.png")){
            Image im = new Image(input);
            ImageView imgv = new ImageView(im);
            imgv.setFitHeight(50);
            imgv.setPreserveRatio(true);
            imgv.setLayoutX(sucursal.getCoordenada().getX());
            imgv.setLayoutY(sucursal.getCoordenada().getY());
            imgv.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    abrirVentanaAdicional(sucursal);
                }
            });
            paneMapa.getChildren().add(imgv);
                       
        }
        catch(IOException e){
            System.out.println("No se encuentra la imagen");
        }
    }
    
    public void abrirVentanaAdicional(Local sucursal){
        Stage nuevaVentana = new Stage();
     
        
        VBox contenedorPrincipalNuevaVentana = new VBox();
        contenedorPrincipalNuevaVentana.setPadding(new Insets(20));
        contenedorPrincipalNuevaVentana.setStyle("-fx-background-color: #7777FF;");
        
        Label texto1 = new Label("Vithas Sucursal Ubicación:");
        Label texto2 = new Label(sucursal.getDireccion());
        Label texto3 = new Label();
        
        texto1.setStyle("-fx-text-fill: WHITE;-fx-font-weight: BOLD");
        texto2.setStyle("-fx-text-fill: WHITE;");
        texto3.setStyle("-fx-text-fill: WHITE;");
       
        
        texto1.setFont(new Font(20));
        texto2.setFont(new Font(20));
        texto3.setFont(new Font(20));
      
        
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 0; i<= 5;i++){
                        
                        crearThreadNuevaVentana(texto3,i,nuevaVentana);
                        Thread.sleep(1000);
                    }
                    
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        t1.start();
        Button cerrar = new Button("Cerrar");
        
        cerrar.setStyle("-fx-text-fill: WHITE;-fx-font-weight: BOLD;-fx-background-color: #4444FF;");
        cerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                nuevaVentana.close();
            }
        });
        contenedorPrincipalNuevaVentana.getChildren().addAll(texto1,texto2,texto3,cerrar);
        Scene scene = new Scene(contenedorPrincipalNuevaVentana, 340, 300);
        nuevaVentana.setScene(scene);
        
        nuevaVentana.setResizable(false);
        nuevaVentana.show();
     
     }
     
     /***
      * Este metodo crea y ejecuta el thread que hara la representacion de los segundos transcurridos desde que 
      * se abre la ventana de datos adicionales de un pais. Ademas actualiza el label con la cantidad de 
      * segundos transcurridos.
      * @param l 
      */
       public void crearThreadNuevaVentana(Label l,int segundos,Stage stage){
           
           Platform.runLater(new Runnable() {
               @Override
               public void run() {
                   if(segundos==5)
                   {
                       stage.close();
                   }
                   else{
                        l.setText("Mostrando: "+ String.valueOf(segundos)+" segundos");
                   }
               }
           });
    }
    private void ponerPinesUbicacionProgresivamenteThread(Local sucursal){
         Platform.runLater(new Runnable() {
               @Override
               public void run() {
                   ponerPin(sucursal);
                   
               }
           });
    
    }
    private void leerSucursales(){
        ArrayList<String> lineasTextoSucursales = Archivo.leer(App.pathFiles+"sucursales.txt");
        
        for(String lineaN: lineasTextoSucursales){
            //X,Y,ubicación
            String porcion[] = lineaN.split("-");
            String posXSTR = porcion[0];
            String posYSTR = porcion[1];
            
            double pdoubleX = Double.valueOf(posXSTR);
            double pdoubleY = Double.valueOf(posYSTR);
            
            int posX = (int)pdoubleX;
            int posY = (int)pdoubleY;
            String ubicacion = porcion[2];
            Local localLeido = new Local(ubicacion, new Coordenada(posX, posY));
            App.listaLocales.add(localLeido);
        }
        System.out.println(lineasTextoSucursales);
        
    }
    
}
