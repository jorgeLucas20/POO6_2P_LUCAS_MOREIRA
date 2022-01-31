/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyecto2.Modelo;

import java.io.Serializable;

/**
 *
 * @author Lukitas
 */
public class ContratacionesPruebas  implements Serializable{
    private String idSolicitud;
    private String usuarioPaciente;
    private String direccion;
    private String fecha;
    private String hora;
    private float ubicacionX;
    private float ubicacionY;
    private double TotalPagar;

    public ContratacionesPruebas(String idSolicitud, String usuarioPaciente, String direccion, String fecha, String hora, float ubicacionX, float ubicacionY, double TotalPagar) {
        this.idSolicitud = idSolicitud;
        this.usuarioPaciente = usuarioPaciente;
        this.direccion = direccion;
        this.fecha = fecha;
        this.hora = hora;
        this.ubicacionX = ubicacionX;
        this.ubicacionY = ubicacionY;
        this.TotalPagar = TotalPagar;
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getUsuarioPaciente() {
        return usuarioPaciente;
    }

    public void setUsuarioPaciente(String usuarioPaciente) {
        this.usuarioPaciente = usuarioPaciente;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public float getUbicacionX() {
        return ubicacionX;
    }

    public void setUbicacionX(float ubicacionX) {
        this.ubicacionX = ubicacionX;
    }

    public float getUbicacionY() {
        return ubicacionY;
    }

    public void setUbicacionY(float ubicacionY) {
        this.ubicacionY = ubicacionY;
    }

    public double getTotalPagar() {
        return TotalPagar;
    }

    public void setTotalPagar(double TotalPagar) {
        this.TotalPagar = TotalPagar;
    }
    
}
