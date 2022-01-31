/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.proyecto2;

import com.espol.proyecto2.Modelo.Prueba;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
import javafx.stage.Stage;
import javax.mail.Transport;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.Message;
import javax.mail.internet.InternetAddress;


/**
 * FXML Controller class
 *
 * @author DELL
 */
public class AgendarPruebaP2Controller implements Initializable {

    @FXML
    private TextField textFieldDireccion;
    @FXML
    private TextField textFieldHora;
    @FXML
    private DatePicker fechaPicker;
    @FXML
    private Pane mapaPanel;
    @FXML
    private Button botonFinalizar;
    
    private static int posicionX;
    private static int posicionY;
    private static Image im;
    private static ImageView imagv;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try(FileInputStream in = new FileInputStream(App.pathImg+"ubicacionIcono.png"))
        {
                    im = new Image(in);
                    imagv = new ImageView(im);
                    imagv.setFitWidth(50);
                    imagv.setPreserveRatio(true);
                    imagv.setVisible(false);
                    mapaPanel.getChildren().add(imagv);
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
        mapaPanel.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
               System.out.println("X:"+String.valueOf(t.getSceneX()));
               System.out.println("Y:"+String.valueOf(t.getSceneY()));
               posicionX = (int) t.getSceneX()-152;
               posicionY = (int) t.getSceneY()-60;
               imagv.setLayoutX(posicionX);
               imagv.setLayoutY(posicionY);
               imagv.setVisible(true);
            }
        
        
        });
        try ( FileInputStream input = new FileInputStream(App.pathImg + "mapa.png")) {
            System.out.println(input.toString());
            Image im = new Image(input); 
            
            //EJEMPLO PARA PONER UNA IMAGEN COMO FONDO. puede ser para  un control o un contenedor que tenga el metodo setBackground
            BackgroundImage backgroundImage = new BackgroundImage(im, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
            Background background = new Background(backgroundImage);
            mapaPanel.setBackground(background);
        } 
        catch (Exception e) {
                System.out.println("No se encuentra la imagen");
                System.out.println(e.getMessage());
                System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
    
    @FXML
    public void finalizarBotonOnClic(){
       
        try
        {
            finalizarCompra();
        }
        catch(CamposIncompletosException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Bueno...");
            alert.setHeaderText(e.getMessage());
            alert.setContentText("Clic en OK para continuar.");
            alert.showAndWait().ifPresent(rs -> {
                if (rs == ButtonType.OK) {
                    System.out.println("Ok presionado");
                }
            });
            
        }
    }
    
    private void finalizarCompra() throws CamposIncompletosException{
        boolean condicionDatosCompletadosIncorrectamente = ("".equals(textFieldDireccion.getText()))||("".equals(textFieldHora.getText())) || ("".equals(String.valueOf(fechaPicker.getValue())));
         System.out.println(condicionDatosCompletadosIncorrectamente);
        if(condicionDatosCompletadosIncorrectamente){
            System.out.println("ERROR!");
            throw new CamposIncompletosException("Campos incompletos");
        }
        else
        {
            //Todo va bien, continuar 
            
            //después de enviar el mail
            abrirVentanaAdicional();
            
        }
    
    }
    
    //ventana con hilo
    
    public void abrirVentanaAdicional(){
        Stage nuevaVentana = new Stage();
        VBox contenedorPrincipalNuevaVentana = new VBox();
        contenedorPrincipalNuevaVentana.setPadding(new Insets(20));
        Label texto1 = new Label("Se ha enviado un mail con la información de su cita\nagendada. El pago debe realizarlo en el momento en\nque se realice la prueba.");
        Label texto2 = new Label();
        
        texto1.setPadding(new Insets(5));
        texto2.setPadding(new Insets(5));
        
        final String username = "jorgelucasbenavides@gmail.com";
        final String password = "B39C34HN";
        char[] passwordchar = password.toCharArray();
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, passwordchar);
              }
          });

        try {

              // Define message
              MimeMessage message = new MimeMessage(session);
              message.setFrom(new InternetAddress(username));
              message.setSubject("asunto");
              message.addRecipient(Message.RecipientType.TO,new InternetAddress("cmedina@imptec.com.pe"));
              String lin = null;
              for(String linea:App.pruebassolicitadas){
                  lin.concat(linea);
              }
              Random random = new Random();
              int value = random.nextInt(1 + 99999) + 1;
              message.setText("Muy buenas tardes mi estimada/n Se ha enviado este mensaje para confirmar su cita la cual tiene estos siguientes datos/n"+
                      "FECHA: "+fechaPicker.getValue()+"/n"+
                      "HORA: "+textFieldHora.getText()+"/n"+
                      "PRUEBA SOLICITADA: "+lin+"/n"+
                      "Codigo: "+value+"/n"+
                      "Si esta de acuerdo siga con su transaccion, gracias");
              // Envia el mensaje
              Transport.send(message);
        } catch (Exception e) {
        }
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(int i = 1; i<= 5;i++){
                        
                        crearThreadNuevaVentana(texto2,i);
                        Thread.sleep(1000);
                    }
                    
                    
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        t1.start();
        Button cerrar = new Button("Cerrar");
        cerrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                nuevaVentana.close();
            }
        });
        contenedorPrincipalNuevaVentana.getChildren().addAll(texto1,texto2,cerrar);
        Scene scene = new Scene(contenedorPrincipalNuevaVentana, 400, 300);
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
       public void crearThreadNuevaVentana(Label l,int segundos){
           
           Platform.runLater(new Runnable() {
               @Override
               public void run() {
                  l.setText("Se cierra en "+ String.valueOf(5-segundos)+" segundos...");
                  if(segundos == 5){
                       Stage thisStage = (Stage) l.getScene().getWindow();
                       thisStage.close();
                  }
               }
           });
    }
    
}
