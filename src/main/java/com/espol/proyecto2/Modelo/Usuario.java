/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.espol.proyecto2.Modelo;

import com.espol.proyecto2.App;
import com.espol.proyecto2.Modelo.enumeraciones.tipoUsuario;
import java.util.ArrayList;
import java.util.Objects;

public class Usuario {
    private String usuario;
    private String password;
    private tipoUsuario tipo;
    public  ArrayList<Usuario> UsuariosObjetoLista;
    public static ArrayList<Usuario> rellenarUsuarios(){
        if(App.usuariosLista != null){
            App.usuariosLista.clear();
        }
        
        ArrayList<Usuario> listaLeidaUsuarios = new ArrayList<>();
        ArrayList<String> lineasTexto = Archivo.leer(App.pathFiles+"usuarios.txt");
        /*
        USUARIO:
            String usuario;
            String password;
            tipoUsuario tipo;
        PACIENTE:
            String cedula;
            String nombre;
            String FechaNacimiento;
            Generos genero;
            String ciudad;
            String email;
            String telefono;
        USUARIOS.TXT:
        usuario,password,tipo
        */
        for(String lineaTextoSola: lineasTexto){
            //mientras no me tope con una línea vacía
            if(!lineaTextoSola.equals("")){
                String partes[] = lineaTextoSola.split(",");
                
                String usuario = partes[0];
                String password = partes[1];
                tipoUsuario tipo = null;
                //tipoUsuario tipo = tipoUsuario.valueOf(partes[2]);
                if(partes[2].equals("L")){
                    tipo = tipoUsuario.LABORATORISTA;
                }
                else if(partes[2].equals("P")){
                    tipo = tipoUsuario.PACIENTE;
                }
                Usuario userNuevo = new Usuario(usuario, password, tipo);
                listaLeidaUsuarios.add(userNuevo);
            }
            
        }
       
        
        if(App.usuariosLista != null){
           App.usuariosLista = listaLeidaUsuarios;
        }
        return listaLeidaUsuarios;
    
    }
    
    public Usuario(String usuario, String password, tipoUsuario tipo) {
        this.usuario = usuario;
        this.password = password;
        this.tipo = tipo;
        UsuariosObjetoLista = new ArrayList<>();
    }
    
    public boolean comparador(String tipo){
        if(this.tipo.equals(tipo)){
            return true;
        }else{
        return false;
        }
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public tipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(tipoUsuario tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Usuario> getUsuariosObjetoLista() {
        return UsuariosObjetoLista;
    }

    public void setUsuariosObjetoLista(ArrayList<Usuario> UsuariosObjetoLista) {
        this.UsuariosObjetoLista = UsuariosObjetoLista;
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.usuario, other.usuario)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuario=" + usuario + ", password=" + password + ", tipo=" + tipo + '}';
    }
    
    
}
