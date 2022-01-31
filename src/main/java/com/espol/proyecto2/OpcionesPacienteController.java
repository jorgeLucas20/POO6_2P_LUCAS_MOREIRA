/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.proyecto2;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class OpcionesPacienteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox contenedorPrincipal;
    @FXML
    private Button botonConocerUbicaciones;
    @FXML
    private Button botonSolicitarPruebasDomicilio;
    @FXML
    private Label labelBienvenida;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelBienvenida.setText("Bienvenido "+App.usuarioActivo.getUsuario());
        //ESTABLECEMOS IMAGEN DE FONDO
        //-------------------------------------------------------
        try ( FileInputStream input = new FileInputStream(App.pathImg + "fondoPantalla.jpg")) {
            System.out.println(input.toString());
            Image im = new Image(input); 
            
            //EJEMPLO PARA PONER UNA IMAGEN COMO FONDO. puede ser para  un control o un contenedor que tenga el metodo setBackground
            BackgroundImage backgroundImage = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            Background background = new Background(backgroundImage);
            contenedorPrincipal.setBackground(background);
        } 
        catch (Exception e) {
                System.out.println("No se encuentra la imagen");
                System.out.println(e.getMessage());
                System.out.println(Arrays.toString(e.getStackTrace()));
        }
        //----------------------------------------------------------------------
        botonSolicitarPruebasDomicilio.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                 //SE TIENE QUE ABRIR UNA NUEVA VENTANA
                 crearNuevaVentanaAgendarCita();
            }
        
        });
        //----------------------------------------------------------------------
        botonConocerUbicaciones.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
               //SE TIENE QUE ABRIR UNA NUEVA VENTANA
               crearNuevaVentanaUbicaciones();
            }
        
        });
         
        //----------------------------------------------------------------------   
        
        
    }
    
   
    private static void crearNuevaVentanaAgendarCita() {
        Stage stage = new Stage();
       
        Scene scene;
        try {
            scene = new Scene(App.loadFXML("AgendarPrueba"), 500, 700);
            System.out.println("clic a AgendarPrueba");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    private static void crearNuevaVentanaUbicaciones() {
        Stage stage = new Stage();
        Scene scene;
        try {
            scene = new Scene(App.loadFXML("Ubicaciones"), 1309, 718);
            System.out.println("clic a ubicaciones");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
