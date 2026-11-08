/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class Sala {
    private String numero;
    private String tipo;
    private Departamento departamento;
    private List<Cita> citas;

    // Constructor sin public: solo puede utilizarse desde el paquete.
    Sala(String numero, String tipo, Departamento departamento) {

        this.numero = Persona.validarTexto(numero, "número de sala");
        this.tipo = Persona.validarTexto(tipo, "tipo de sala");

        if (departamento == null) {
            throw new IllegalArgumentException(
                    "El departamento no puede ser nulo");
        }

        this.departamento = departamento;
        this.citas = new ArrayList<>();
    }

    public String getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void addCita(Cita cita) {
        if (cita != null && !citas.contains(cita)) {
            citas.add(cita);
        }
    }

    public List<Cita> getCitas() {
        // Evita que otras clases modifiquen directamente la lista.
        return Collections.unmodifiableList(citas);
    }
}
