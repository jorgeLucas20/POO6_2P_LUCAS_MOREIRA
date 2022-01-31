/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyecto2.Modelo;

import com.espol.proyecto2.App;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

/**
 *
 * @author Lukitas
 */
public class Archivo {
    
    public Archivo(){
       
    }
    public static void EscribirArchivoNIO(String nombreArchivo, String linea){
        Path myText_path = Paths.get("", nombreArchivo);
        Charset charset = Charset.forName("UTF-8");
        ArrayList<String> lines = new ArrayList<>();
        lines.add(linea);
        //lines.add("\n");
        
        try {
            Files.write(myText_path, lines, charset, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println(e);
        }
        finally
        {
           lines = null;
        }
    
    }
            
    public static void EscribirArchivo(String nombreArchivo, String linea) {

        FileWriter fichero = null;
        BufferedWriter bw = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(nombreArchivo,true);
            bw = new BufferedWriter(fichero);
            bw.write(linea+"\n");
            System.out.println("ksdsdlsd");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para 
                // asegurarnos que se cierra el fichero.
                if (null != fichero) {
                    //fichero.close();
                    bw.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
    public static ArrayList<String> leer(String nombreArchivo) {
         ArrayList<String> linean = new ArrayList<>();
        
        try(BufferedReader br = new BufferedReader(new FileReader(nombreArchivo, Charset.forName("UTF-8")))) {
            String linea;
            
            while((linea = br.readLine()) != null) {
                linean.add(linea);
            }
            
            br.close();
            
        } catch(IOException e) {
            System.out.println("Error de lectura...");
        }
       return linean;
    }
}
