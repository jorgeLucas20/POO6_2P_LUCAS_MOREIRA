/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.proyecto2;

import com.espol.proyecto2.Modelo.Archivo;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ConsultarCitasLaboratoristaController implements Initializable {

    @FXML
    private Button botonCerrar;
    @FXML
    private TableView tablaDatos;
    @FXML
    private TableColumn<String, String> nombrecol;
    @FXML
    private TableColumn<String, String> apellocol;
    @FXML
    private TableColumn<String, String> fechacol;
    @FXML
    private TableColumn<String, String> numerocol;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ArrayList<String> Activo = Archivo.leer(App.pathFiles+App.usuarioActivo.getUsuario()+".txt");
        //for(String a:Activo){
        //    tablaDatos.getColumns().add(a);
        //}
        Random r = new Random();
        ArrayList<String> lineapaciente = Archivo.leer(App.pathFiles+"pacientes.txt");
        int valorDado = r.nextInt(lineapaciente.size());
        int valorDado2 = r.nextInt(lineapaciente.size());
        String lineacompleta = lineapaciente.get(valorDado);
        String a[] = lineacompleta.split(",");
        String lineacompleta2 = lineapaciente.get(valorDado2);
        ObservableList<String> table = FXCollections.observableArrayList(a[2],a[3],a[4],String.valueOf(valorDado));
        tablaDatos.setItems(table);
        
         botonCerrar.setOnAction(new EventHandler<ActionEvent>(){
             @Override
             public void handle(ActionEvent t) {
                Stage thisStage = (Stage) botonCerrar.getScene().getWindow();
                thisStage.close();
             }
         
         });
    }    
    
}
