/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.excepciones;

/**
 *
 * @author Administrador
 */
public class CitaException extends Exception{
     public CitaException(String message) {
        super(message);
    }

    public CitaException(String message, Throwable cause) {
        super(message, cause);
    }
}
