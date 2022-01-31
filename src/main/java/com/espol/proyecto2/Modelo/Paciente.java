/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyecto2.Modelo;

import com.espol.proyecto2.Modelo.enumeraciones.Generos;
import com.espol.proyecto2.Modelo.enumeraciones.tipoUsuario;

/**
 *
 * @author Lukitas
 */
public class Paciente extends Usuario{
    private String cedula;
    private String nombre;
    private String FechaNacimiento;
    private Generos genero;
    private String ciudad;
    private String email;
    private String telefono;

    
    
    public Paciente(String cedula, String nombre, String FechaNacimiento, Generos genero, String ciudad, String email, String telefono, String usuario, String password, tipoUsuario tipo) {
        super(usuario, password, tipo);
        this.cedula = cedula;
        this.nombre = nombre;
        this.FechaNacimiento = FechaNacimiento;
        this.genero = genero;
        this.ciudad = ciudad;
        this.email = email;
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(String FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public Generos getGenero() {
        return genero;
    }

    public void setGenero(Generos genero) {
        this.genero = genero;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}
