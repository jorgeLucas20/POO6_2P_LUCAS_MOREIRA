/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyecto2.Modelo;

/**
 *
 * @author Lukitas
 */
public class DetallesSolicitudes {
    private String idSolicitud;
    private String codigoPrueba;

    public DetallesSolicitudes(String idSolicitud, String codigoPrueba) {
        this.idSolicitud = idSolicitud;
        this.codigoPrueba = codigoPrueba;
    }

    public String getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getCodigoPrueba() {
        return codigoPrueba;
    }

    public void setCodigoPrueba(String codigoPrueba) {
        this.codigoPrueba = codigoPrueba;
    }
    
}
