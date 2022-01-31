/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.proyecto2;

import com.espol.proyecto2.Modelo.Archivo;
import com.espol.proyecto2.Modelo.Prueba;
import com.espol.proyecto2.Modelo.enumeraciones.tipoprueba;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AgendarPruebaController implements Initializable {

    @FXML
    private Label numeroValorUnitarioLabel;
    @FXML
    private ComboBox comboBoxTipoPrueba;
    @FXML
    private TextField campoTextoCantidad;
    @FXML
    private ComboBox comboBoxPrueba;
    @FXML
    private Button botonAgregar;
    @FXML
    private Button botonContinuar;
    @FXML
    private VBox contenedorCompras;
    
    @FXML
    private Label numeroSubTotalLabel;
    @FXML
    private Label numeroServicioDomicilioLabel;
    @FXML
    private Label numeroTotalLabel;
    
    private ArrayList<String> listaOpcionesComboBoxPrueba;
    private ArrayList<Double> totalCompras;
    
    private boolean agregadoExitoso = false;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        totalCompras = new ArrayList<>();
        //Subtotal
        totalCompras.add(0.00);
        //total
        totalCompras.add(0.00);
        
        ArrayList<String> listaOpcionesComboBoxTipoPrueba = new ArrayList<>();
        listaOpcionesComboBoxTipoPrueba.add("Diagnóstico");
        listaOpcionesComboBoxTipoPrueba.add("Anticuerpos");
        comboBoxTipoPrueba.getItems().addAll(listaOpcionesComboBoxTipoPrueba);
        
        listaOpcionesComboBoxPrueba = new ArrayList<>();
        
        App.listaPruebasDisponibles = llenarListaPruebasDisponibles();
        
        
    }
    private ArrayList<Prueba> llenarListaPruebasDisponibles(){
        ArrayList<Prueba> listaPruebasDisponibles = new ArrayList<>();
        ArrayList<String> lineasLista = Archivo.leer(App.pathFiles+"pruebas.txt");
        //codigoPrueba,tipoPrueba,nombrePrueba,precio
        for(String linea: lineasLista){
            String porcion[] = linea.split(",");
            String codigoPrueba = porcion[0];
            String tipoPrueba = porcion[1];
            String nombrePrueba = porcion[2];
            float  precio = Float.valueOf(porcion[3]);
            
            tipoprueba tipp = tipoprueba.ANTICUERPOS;
            if(tipoPrueba.equals("Anticuerpos")){
               tipp = tipoprueba.ANTICUERPOS;
            }
            else if(tipoPrueba.equals("Diagnóstico")){
                tipp = tipoprueba.DIAGNOSTICO;
            }
            //Prueba(String nombre, tipoprueba tipo, float precio, String codigo)
            Prueba pruebaNueva = new Prueba(nombrePrueba,tipp,precio,codigoPrueba);
            listaPruebasDisponibles.add(pruebaNueva);
        }
        return listaPruebasDisponibles;
    
    
    }
    @FXML
    public void onActionComboBoxTipoPrueba(){
        numeroValorUnitarioLabel.setText("0.00");
        if(comboBoxTipoPrueba.getValue().equals("Diagnóstico")){
            listaOpcionesComboBoxPrueba.clear();
            for(Prueba p : App.listaPruebasDisponibles){
                if(p.getTipo() == tipoprueba.DIAGNOSTICO){
                    listaOpcionesComboBoxPrueba.add(p.getNombre());
                }
            }
           
            comboBoxPrueba.getItems().clear();
            comboBoxPrueba.getItems().addAll(listaOpcionesComboBoxPrueba);
        }
        else if(comboBoxTipoPrueba.getValue().equals("Anticuerpos"))
        {
            listaOpcionesComboBoxPrueba.clear();
            for(Prueba p : App.listaPruebasDisponibles){
                if(p.getTipo() == tipoprueba.ANTICUERPOS){
                    listaOpcionesComboBoxPrueba.add(p.getNombre());
                }
            }
            comboBoxPrueba.getItems().clear();
            comboBoxPrueba.getItems().addAll(listaOpcionesComboBoxPrueba);
        }
    }
    
    @FXML
    public void onActionComboBoxPrueba(){
        
            int indexPruebaSeleccionada = App.listaPruebasDisponibles.indexOf(new Prueba((String)comboBoxPrueba.getValue()));
            System.out.println("indexPruebaSeleccionada: "+String.valueOf(indexPruebaSeleccionada));
            if(indexPruebaSeleccionada != -1){
                Prueba pruebaSeleccionada = App.listaPruebasDisponibles.get(indexPruebaSeleccionada);
                System.out.println(pruebaSeleccionada);
                numeroValorUnitarioLabel.setText(String.valueOf(pruebaSeleccionada.getPrecio()));
            }
    }
    
    @FXML
    public void onActionBotonAgregar(){
        //vemos si todo va bien
        try
        {
            agregarPruebaVenta();
            String porcionCostolabel = numeroValorUnitarioLabel.getText().substring(1);
            double subtotal = totalCompras.get(0)+ Double.valueOf(porcionCostolabel)*Double.valueOf(campoTextoCantidad.getText());
            totalCompras.set(0,subtotal);
            totalCompras.set(1,subtotal+5);
            numeroSubTotalLabel.setText((String.valueOf(totalCompras.get(0))));
            numeroTotalLabel.setText((String.valueOf(totalCompras.get(1))));
        }
        catch(CamposIncompletosException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("¡Error!");
            alert.setHeaderText(e.getMessage());
            alert.setContentText("No podrá registrar la venta");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }
       
    }
    private void agregarPruebaVenta() throws CamposIncompletosException{
        boolean condicionError = (campoTextoCantidad.getText() == null) ||!isNumeric(campoTextoCantidad.getText()) || (comboBoxPrueba.getValue() == null)|| (comboBoxTipoPrueba.getValue() == null);
        if(condicionError){
            campoTextoCantidad.setText("");
            comboBoxTipoPrueba.setValue("");
            comboBoxPrueba.setValue("");
            agregadoExitoso = false;
            throw new CamposIncompletosException("Campos incompletos o mal llenados");
            
        }
        else
        {
            
            //todo va bien, continuar 
            agregadoExitoso = true;
            //agregamos el hbox con 3 labels
            HBox subContenedor = new HBox();
            //subContenedor.setPadding(new Insets(10));
            subContenedor.setSpacing(160);
            subContenedor.setAlignment(Pos.CENTER);
            Label nombreCompraN = new Label((String) comboBoxPrueba.getValue());
            Label cantidadCompraN = new Label(campoTextoCantidad.getText());
            Label precioCompraN = new Label(numeroValorUnitarioLabel.getText());
            subContenedor.getChildren().addAll(nombreCompraN,cantidadCompraN,precioCompraN);
            contenedorCompras.getChildren().add(subContenedor);   
            App.pruebassolicitadas.add(nombreCompraN.getText());
            
        }
        
    }
    
    @FXML
    public void onActionBotonContinuar(){
        if(agregadoExitoso){       
            try {
                App.contratoPruebaActiva.setTotalPagar(Double.valueOf(numeroTotalLabel.getText()));
                Stage stage = new Stage();
                Scene scene = new Scene(App.loadFXML("AgendarPruebaP2"), 1300, 700);
                System.out.println("clic a AgendarPrueba");
                stage.setScene(scene);
                Stage thisStage = (Stage) contenedorCompras.getScene().getWindow();
                thisStage.close();
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("¡Error!");
            alert.setHeaderText("¡No ha comprado nada!");
            alert.setContentText("No podrá continuar");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Pressed OK.");
                }
            });
        }
    }

    public static boolean isNumeric(String str) {
        try {
            int d = Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return false; //Error no es numerico
        }
        return true; //Es numerico
    }
     
}
