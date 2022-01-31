/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.proyecto2.Modelo;

import com.espol.proyecto2.Modelo.enumeraciones.tipoprueba;
import java.util.Objects;

/**
 *
 * @author DELL
 */
public class Prueba {
    private String nombre;
    private tipoprueba tipo;
    private float precio;
    private String codigo;

    public Prueba(String nombre, tipoprueba tipo, float precio, String codigo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precio = precio;
        this.codigo = codigo;
    }
    public Prueba(String nombre) {
        this.nombre = nombre; 
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public tipoprueba getTipo() {
        return tipo;
    }

    public void setTipo(tipoprueba tipo) {
        this.tipo = tipo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return "Prueba{" + "nombre=" + nombre + ", tipo=" + tipo + ", precio=" + precio + ", codigo=" + codigo + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prueba other = (Prueba) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    
    
}
