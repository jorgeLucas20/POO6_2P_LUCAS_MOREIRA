/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyecto2.Modelo;

import java.util.ArrayList;

/**
 *
 * @author Lukitas
 */
public class Local {
    
    private String direccion;
    
    private Coordenada coordenada;

    public Local( String direccion, Coordenada coordenada) {
       
        this.direccion = direccion;
      
        this.coordenada = coordenada;
    }

   
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }
    
}
