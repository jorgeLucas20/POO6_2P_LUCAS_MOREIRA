/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.espol.proyecto2;

/**
 *
 * @author DELL
 */
public class CamposIncompletosException extends RuntimeException{
    CamposIncompletosException(String mensaje){
        super(mensaje);
    }
}
