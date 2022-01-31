/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.proyecto2;

import com.espol.proyecto2.Modelo.Archivo;
import com.espol.proyecto2.Modelo.ContratacionesPruebas;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
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
 
    @FXML
    private VBox conte;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        //---------------------------------
      
    TableView tab = new TableView();

    TableColumn nameColumn = new TableColumn("Nombres");
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombresPaciente"));

    TableColumn surnameColumn = new TableColumn("Apellidos");
    surnameColumn.setCellValueFactory(new PropertyValueFactory<>("apellidosPaciente"));
    
    TableColumn dateColumn = new TableColumn("Fecha");
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("fecha"));

    TableColumn idColumn = new TableColumn("Número Solicitud");
    surnameColumn.setCellValueFactory(new PropertyValueFactory<>("idSolicitud"));

    tab.getColumns().addAll(nameColumn, surnameColumn,dateColumn,idColumn);
  
   ArrayList<ContratacionesPruebas> lista = App.deserializarLista();
    for(ContratacionesPruebas c: lista){
        System.out.println(c);
        tab.getItems().add(c);
    }
    
    conte.getChildren().add(tab);
        //------------------------------------
        
        
         botonCerrar.setOnAction(new EventHandler<ActionEvent>(){
             @Override
             public void handle(ActionEvent t) {
                Stage thisStage = (Stage) botonCerrar.getScene().getWindow();
                thisStage.close();
             }
         
         });
    }    
    
}
