/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

/**
 *
 * @author Administrador
 */
public class Matricula {

    private String numero;

    public Matricula(String numero) {
        validarMatricula(numero);
        this.numero = numero;
    }

    private void validarMatricula(String numero) {

        if (numero == null || !numero.startsWith("MP-")) {
            throw new IllegalArgumentException(
                    "La matrícula debe comenzar con MP-");
        }

        int cantidadDigitos = numero.length() - 3;

        if (cantidadDigitos < 4 || cantidadDigitos > 6) {
            throw new IllegalArgumentException(
                    "La matrícula debe tener entre 4 y 6 dígitos");
        }

        for (int i = 3; i < numero.length(); i++) {
            if (!Character.isDigit(numero.charAt(i))) {
                throw new IllegalArgumentException(
                        "La matrícula solo puede contener números después de MP-");
            }
        }
    }

    public String getNumero() {
        return numero;
    }
}
