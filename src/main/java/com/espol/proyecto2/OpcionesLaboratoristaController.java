/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.proyecto2;

import com.espol.proyecto2.Modelo.Archivo;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
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
public class OpcionesLaboratoristaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox contenedorPrincipal;
    @FXML
    private Button botonGenerarConsolidadoCitas;
    @FXML
    private Button botonConsultarCitas;
    @FXML
    private Label labelBienvenida;
    
    private Label labelInformacionAlerta;
    
    private boolean condicionConsolidadoCitasGenerado = true;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        labelBienvenida.setText("Bienvenido "+App.usuarioActivo.getUsuario());
        labelInformacionAlerta = new Label();
        contenedorPrincipal.getChildren().add(labelInformacionAlerta);
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
        //----------------------------------------------------------------------
        botonGenerarConsolidadoCitas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
               
                if(condicionConsolidadoCitasGenerado){
                   labelInformacionAlerta.setText("");
                       Random r = new Random();
                       ArrayList<String> lineapaciente = Archivo.leer(App.pathFiles+"pacientes.txt");
                       int valorDado = r.nextInt(lineapaciente.size());
                       int valorDado2 = r.nextInt(lineapaciente.size());
                       String lineacompleta = lineapaciente.get(valorDado);
                       Archivo.EscribirArchivo(App.usuarioActivo.getUsuario()+".txt", lineacompleta);
                       String lineacompleta2 = lineapaciente.get(valorDado2);
                       Archivo.EscribirArchivo(App.usuarioActivo.getUsuario()+".txt", lineacompleta2);
                       condicionConsolidadoCitasGenerado = false;
                }
            }
        
        });
        //----------------------------------------------------------------------
        botonConsultarCitas.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
               //SE TIENE QUE ABRIR UNA NUEVA VENTANA
               condicionConsolidadoCitasGenerado = false;
               if(condicionConsolidadoCitasGenerado){
                   labelInformacionAlerta.setText("Debe generar el consolidado antes de consultar");
                   labelInformacionAlerta.setStyle("-fx-text-fill: #FFAAAA");
                   
                }
               else
               {
                    Stage stage = new Stage();
                    Scene scene;
                    try {
                        scene = new Scene(App.loadFXML("ConsultarCitasLaboratorista"), 590, 500);
                        System.out.println("clic a ConsultarCitasLaboratorista");
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
               }
               
            }
        
        });
        //----------------------------------------------------------------------  
    }    
    
}
