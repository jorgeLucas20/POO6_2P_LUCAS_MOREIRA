/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyecto2.Modelo;

/**
 *
 * @author Lukitas
 */
public class Coordenada {
    private float x;
    private float y;

    public Coordenada(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
    public Coordenada() {
        this.x = 0;
        this.y = 0;
    }
    
    public void cargarPuntos(){
        
    }
    public static double numeroAleatorio(){
        return Math.random()*10;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
    
}
