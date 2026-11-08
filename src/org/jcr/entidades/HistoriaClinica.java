/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.jcr.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Administrador
 */
public class HistoriaClinica {

    private String numeroHistoria;
    private Paciente paciente;

    private List<String> diagnosticos;
    private List<String> tratamientos;
    private List<String> alergias;

    // Constructor sin public: solo accesible desde el paquete
    HistoriaClinica(Paciente paciente) {

        if (paciente == null) {
            throw new IllegalArgumentException(
                    "El paciente no puede ser nulo");
        }

        this.paciente = paciente;

        this.numeroHistoria = generarNumeroHistoria();

        this.diagnosticos = new ArrayList<>();
        this.tratamientos = new ArrayList<>();
        this.alergias = new ArrayList<>();
    }

    private String generarNumeroHistoria() {
        return "HC-" + paciente.getDni()
                + "-" + LocalDate.now().getYear();
    }

    public String getNumeroHistoria() {
        return numeroHistoria;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void agregarDiagnostico(String diagnostico) {
        if (diagnostico != null && !diagnostico.trim().isEmpty()) {
            diagnosticos.add(diagnostico);
        }
    }

    public void agregarTratamiento(String tratamiento) {
        if (tratamiento != null && !tratamiento.trim().isEmpty()) {
            tratamientos.add(tratamiento);
        }
    }

    public void agregarAlergia(String alergia) {
        if (alergia != null && !alergia.trim().isEmpty()) {
            alergias.add(alergia);
        }
    }

    public List<String> getDiagnosticos() {
        //devuelve una vista que no se puede modificar.
        return Collections.unmodifiableList(diagnosticos);
    }

    public List<String> getTratamientos() {
        return Collections.unmodifiableList(tratamientos);
    }

    public List<String> getAlergias() {
        return Collections.unmodifiableList(alergias);
    }
}
