/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import java.time.LocalDate;
import org.jcr.enums.TipoSangre;

/**
 *
 * @author Administrador
 */
public abstract class Persona {
    private String nombre;
    private String apellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private TipoSangre tipoSangre;

    public Persona(String nombre, String apellido, String dni,
                   LocalDate fechaNacimiento, TipoSangre tipoSangre) {

        this.nombre = validarTexto(nombre, "nombre");
        this.apellido = validarTexto(apellido, "apellido");
        validarDni(dni);

        if (fechaNacimiento == null) {
            throw new IllegalArgumentException(
                    "La fecha de nacimiento no puede ser nula");
        }

        if (tipoSangre == null) {
            throw new IllegalArgumentException(
                    "El tipo de sangre no puede ser nulo");
        }

        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.tipoSangre = tipoSangre;
    }

    // Validación reutilizable para textos obligatorios
    static String validarTexto(String texto, String campo) {
        if (texto == null || texto.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El campo " + campo + " no puede estar vacío");
        }
        return texto.trim();
    }

    // Validación del documento
    private void validarDni(String dni) {
        if (dni == null ||
                (dni.length() != 7 && dni.length() != 8)) {
            throw new IllegalArgumentException(
                    "El DNI debe tener 7 u 8 dígitos");
        }

        for (int i = 0; i < dni.length(); i++) {
            if (!Character.isDigit(dni.charAt(i))) {
                throw new IllegalArgumentException(
                        "El DNI debe contener solo números");
            }
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public TipoSangre getTipoSangre() {
        return tipoSangre;
    }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public int getEdad() {
        LocalDate hoy = LocalDate.now();

        int edad = hoy.getYear() - fechaNacimiento.getYear();

        if (hoy.getMonthValue() < fechaNacimiento.getMonthValue()
                || (hoy.getMonthValue() == fechaNacimiento.getMonthValue()
                && hoy.getDayOfMonth() < fechaNacimiento.getDayOfMonth())) {
            edad--;
        }

        return edad;
    }
}
