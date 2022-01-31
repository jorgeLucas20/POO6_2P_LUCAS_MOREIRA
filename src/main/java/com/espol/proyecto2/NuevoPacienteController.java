/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.proyecto2;

import static com.espol.proyecto2.App.loadFXML;
import com.espol.proyecto2.Modelo.Archivo;
import com.espol.proyecto2.Modelo.Paciente;
import com.espol.proyecto2.Modelo.enumeraciones.Generos;
import com.espol.proyecto2.Modelo.enumeraciones.tipoUsuario;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class NuevoPacienteController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField campoTextoCedula;
    @FXML
    private TextField campoTextoNombres;
    @FXML
    private TextField campoTextoApellidos;
    @FXML
    private DatePicker campoTextoFechaNacimiento;
    @FXML
    private TextField campoTextoCiudad;
    @FXML
    private TextField campoTextoEmail;
    @FXML
    private TextField campoTextoTelefono;
    @FXML
    private TextField campoTextoUsuario;
    @FXML
    private PasswordField campoContrasena;
    @FXML
    private Button botonRegistrar;
    
    @FXML 
    RadioButton radioButtonHombre;
    @FXML 
    RadioButton radioButtonMujer;
    @FXML 
    RadioButton radioButtonOtro;
    
    private ToggleGroup tg;
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        tg = new ToggleGroup();
        radioButtonHombre.setToggleGroup(tg);
        radioButtonMujer.setToggleGroup(tg);
        radioButtonOtro.setToggleGroup(tg);
    }    
    
    @FXML
    private void onClicBotonRegistrar(){
        
        String TextoCedula = campoTextoCedula.getText();
        String TextoNombres = campoTextoNombres.getText();
        String TextoApellidos = campoTextoApellidos.getText();
        String TextoFechaNacimiento = "";
        if(!(campoTextoFechaNacimiento.getValue() == null)){
            TextoFechaNacimiento = campoTextoFechaNacimiento.getValue().toString();
        }
        
        String TextoCiudad = campoTextoCiudad.getText();
        String TextoEmail = campoTextoEmail.getText();
        String TextoTelefono = campoTextoTelefono.getText();
        String TextoUsuario = campoTextoUsuario.getText();
        String textoContrasena = campoContrasena.getText();
        
        System.out.println("Cédula: "+TextoCedula);
        System.out.println("Nombres: "+TextoNombres);
        System.out.println("Apellidos: "+TextoApellidos);
        System.out.println("FechaNacimiento: "+TextoFechaNacimiento);
        System.out.println("Email: "+TextoEmail);
        System.out.println("Telefono: "+TextoTelefono);
        System.out.println("Usuario: "+TextoUsuario);
        System.out.println("Contrasena: "+textoContrasena);
        System.out.println("Hombre: "+(radioButtonHombre == (RadioButton)tg.getSelectedToggle()));
        System.out.println("Mujer: "+(radioButtonMujer == (RadioButton)tg.getSelectedToggle()));
        System.out.println("Otro: "+(radioButtonOtro == (RadioButton)tg.getSelectedToggle()));
        
        /*
        RadioButton chk = (RadioButton)t1.getToggleGroup().getSelectedToggle(); 
        */
        boolean faltaLlenar = TextoCedula.equals("")||TextoNombres.equals("")||TextoApellidos.equals("")||TextoFechaNacimiento.equals("")||TextoCiudad.equals("")||TextoEmail.equals("")||TextoTelefono.equals("")||TextoUsuario.equals("")||textoContrasena.equals("")||(tg.getSelectedToggle() == null);
      
        if(faltaLlenar){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("¡Atención!");
            alert.setHeaderText("Recuerde llenar todos los campos.");
            alert.setContentText("Caso contrario no podrá registrar el paciente.");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }
        else
        {
            //REGISTRAR NUEVO PACIENTE YA QUE LOS DATOS YA ESTÁN VALIDADOS
            String tipo = "";
            if((RadioButton)tg.getSelectedToggle() == radioButtonHombre){
                tipo = Generos.MASCULINO.name();
            }
            else if((RadioButton)tg.getSelectedToggle() == radioButtonMujer){
                tipo = Generos.FEMENINO.name();
            }
            else if((RadioButton)tg.getSelectedToggle() == radioButtonOtro){
                tipo = Generos.OTROS.name();
            }
            
            //usuario,password,tipo
            String textoArchivoNuevoUsuario = TextoUsuario+","+textoContrasena+","+"P";
            
            //Usuario,cédula,nombres,apellidos,fecha nacimiento,género,ciudad,email,teléfono
            String textoArchivoNuevoPaciente =  TextoUsuario+","+TextoCedula+","+TextoNombres+","+TextoApellidos+","+TextoFechaNacimiento+","+tipo+","+TextoCiudad+","+TextoEmail+","+TextoTelefono;
            
            Archivo.EscribirArchivoNIO(App.pathFiles+"usuarios.txt", textoArchivoNuevoUsuario);
            Archivo.EscribirArchivoNIO(App.pathFiles+"pacientes.txt", textoArchivoNuevoPaciente); 
            
            //Paciente(String cedula,String nombre,String FechaNacimiento,Generos genero,String ciudad,String email,String telefono,String usuario,String password,tipoUsuario tipo)
            Paciente nuevoPaciente = new Paciente(TextoCedula,TextoNombres,TextoFechaNacimiento,Generos.valueOf(tipo),TextoCiudad,TextoEmail,TextoTelefono,TextoUsuario,textoContrasena,tipoUsuario.PACIENTE);
            App.usuarioActivo = nuevoPaciente;
            App.usuariosLista.add(nuevoPaciente);
            //informo y cierro
            Alert alert = new Alert(AlertType.INFORMATION);
            
            alert.setTitle("Bueno...");
            alert.setHeaderText("¡Paciente registrado con exito!");
            alert.setContentText("Clic en OK para continuar.");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    try {
                        System.out.println("Pressed OK.");
                        App.setRoot("OpcionesPaciente");
                        Stage thisStage = (Stage) campoTextoCedula.getScene().getWindow();
                        thisStage.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            });
            
        }
        
    }
    
}
