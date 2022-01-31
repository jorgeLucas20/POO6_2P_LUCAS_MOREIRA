package com.espol.proyecto2;

import com.espol.proyecto2.Modelo.Archivo;
import com.espol.proyecto2.Modelo.ContratacionesPruebas;
import com.espol.proyecto2.Modelo.Local;
import com.espol.proyecto2.Modelo.Prueba;
import com.espol.proyecto2.Modelo.Usuario;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application  implements Serializable{

    private static Scene scene;
    private static Stage stagePrincipal;
    public static ArrayList<Local> listaLocales;
    public static ArrayList<Prueba> listaPruebasDisponibles;
    
    //serializar esto
    public static ArrayList<ContratacionesPruebas> listaPruebasCompradas;
    
    
    public static String pathImg="src/main/resources/images/";
    public static String pathFiles="src/main/resources/textFiles/";
    public static ArrayList<Usuario> usuariosLista;
    public static ArrayList<String> pruebassolicitadas;
    public static Usuario usuarioActivo;
    
    public void serializarLista(){
        FileOutputStream fout;  
        try {
            fout = new FileOutputStream(App.pathFiles+"listaVentas.dat");
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(listaPruebasCompradas);  
            out.flush();  
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
         catch (IOException ex) {
            ex.printStackTrace();
        }
   
    }
    
    public void deserializarLista(){
          ObjectInputStream in;  
        try {
            in = new ObjectInputStream(new FileInputStream(App.pathFiles+"listaVentas.dat"));
            listaPruebasCompradas =(ArrayList<ContratacionesPruebas>)in.readObject();  
            in.close(); 
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
            
    }
    
    @Override
    public void start(Stage stage) throws IOException {
        usuariosLista = new ArrayList<>();
        listaPruebasDisponibles = new ArrayList<>();
        listaPruebasCompradas = new ArrayList<>();
        
        Usuario.rellenarUsuarios();
        
        scene = new Scene(loadFXML("InicioSesion"), 640, 480);
        stage.setScene(scene);
        stagePrincipal = stage;
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}