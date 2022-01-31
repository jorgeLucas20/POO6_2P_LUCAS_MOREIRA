/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.proyecto2;

import com.espol.proyecto2.Modelo.Usuario;
import com.espol.proyecto2.Modelo.enumeraciones.tipoUsuario;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class InicioSesionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField campoTextoUsuario;
    @FXML
    private PasswordField campoContrasenaUsuario;
    @FXML
    private Button botonIniciarSesion;
    @FXML
    private Label labelCrearNuevaCuenta;
    
    @FXML
    private VBox contenedorPrincipal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
            //botonIniciarSesion
            //----------------------------------
            botonIniciarSesion.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    
                    String nombreUsuarioIngresado = campoTextoUsuario.getText();
                    String contrasenaUsuarioIngresada = campoContrasenaUsuario.getText();
                    Usuario provisional = new Usuario(nombreUsuarioIngresado, contrasenaUsuarioIngresada, tipoUsuario.PACIENTE);
                    
                    if(App.usuariosLista.size() > 0){
                        int indexUsuarioEncontrado = App.usuariosLista.indexOf(provisional);
                        if(indexUsuarioEncontrado == -1){
                            System.out.println("Usuario No Encontrado");
                        }
                        else{
                            
                            Usuario usuarioEncontrado = App.usuariosLista.get(indexUsuarioEncontrado);
                            App.usuarioActivo = usuarioEncontrado;
                            App.contratoPruebaActiva.setUsuarioPaciente(App.usuarioActivo.getUsuario());
                            if(usuarioEncontrado.getTipo().equals(tipoUsuario.PACIENTE)){
                                //VEMOS SI ES PACIENTE
                                System.out.println("clic a OpcionesPaciente");
                                 try {
                                    App.setRoot("OpcionesPaciente");
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            else if(usuarioEncontrado.getTipo().equals(tipoUsuario.LABORATORISTA)){
                                //VEMOS SI ES LABORATORISTA
                                System.out.println("clic a OpcionesLaboratorista");
                                try {
                                    App.setRoot("OpcionesLaboratorista");
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                            
                        }
                    }
                    
                    
                   

                }

            });
            
            //labelCrearNuevaCuenta
            //----------------------------------
            labelCrearNuevaCuenta.setOnMouseClicked(new EventHandler<MouseEvent>(){
                @Override
                public void handle(MouseEvent t) {
                    System.out.println("clic  label crear nueva cuenta");
                    //CREAR NUEVA VENTANA
                    //contenedorPrincipal.setDisable(true);
                    crearNuevaVentana();
                }
            
            });
    }    
    
    private static void crearNuevaVentana() {
        Stage stage = new Stage();
        Scene scene;
        try {
            scene = new Scene(App.loadFXML("NuevoPaciente"), 640, 480);
            
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }
}
